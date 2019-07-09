package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.RuleBoardAttachVO;
import org.community.domain.RuleBoardVO;
import org.community.mapper.RuleBoardAttachMapper;
import org.community.mapper.RuleBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuleBoardServiceImpl implements RuleBoardService{

	private RuleBoardMapper mapper;
	
	@Autowired
	private void setRuleBoardMapper(RuleBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	private RuleBoardAttachMapper attachMapper;
	
	@Autowired
	private void setRuleBoardAttachMapper(RuleBoardAttachMapper attachMapper) {
		this.attachMapper = attachMapper;
	}
	@Override
	public RuleBoardVO get(Long bno) {
		System.out.println("bno : " + bno);
		return mapper.read(bno);
	}

	@Override
	public List<RuleBoardVO> getAll(Criteria cri) {
			return mapper.list(cri);
	}

	@Transactional
	@Override
	public void register(RuleBoardVO vo) {
			mapper.insert(vo);
			
			if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
				return;
			}
			
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
	}

	@Override
	@Transactional
	public boolean modify(RuleBoardVO vo) {
		attachMapper.deleteAll(vo.getBno());
		
		boolean modifyResult = mapper.update(vo) == 1;
		
		if(modifyResult && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach ->{
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}

	@Override
	public List<RuleBoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}
	
	
}

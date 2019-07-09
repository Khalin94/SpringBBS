package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevBoardAttachVO;
import org.community.domain.DevBoardVO;
import org.community.mapper.DevBoardAttachMapper;
import org.community.mapper.DevBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DevBoardServiceImpl implements DevBoardService{
	
	private DevBoardMapper mapper;
	
	@Autowired
	private void setDevBoardMapper(DevBoardMapper mapper) {
		this.mapper = mapper;
	}

	private DevBoardAttachMapper attachMapper;
	
	@Autowired
	private void setDevBoardAttachMapper(DevBoardAttachMapper attachMapper) {
		this.attachMapper = attachMapper;
	}

	@Override
	public DevBoardVO get(Long bno) {
		
		return mapper.read(bno);

	}

	@Override
	public List<DevBoardVO> getAll(Criteria cri) {
		return mapper.list(cri);
	}

	@Transactional
	@Override
	public void register(DevBoardVO vo) {
		mapper.insert(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		
		vo.getAttachList().forEach(attach ->{
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		
	}

	@Transactional
	@Override
	public boolean modify(DevBoardVO vo) {
		attachMapper.deleteAll(vo.getBno());
		
		boolean modifyResult =  mapper.update(vo) == 1;
		
		if(modifyResult && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
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
	public List<DevBoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}

	
}

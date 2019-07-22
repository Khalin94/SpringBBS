package org.community.service;

import java.util.List;

import org.community.domain.AttachBoardVO;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.community.mapper.JobsBoardAttachMapper;
import org.community.mapper.JobsBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("jobsBoardService")
public class JobsBoardServiceImpl implements BoardService{
	Logger log = LoggerFactory.getLogger(JobsBoardServiceImpl.class);
	private JobsBoardMapper mapper;
	
	@Autowired
	private void JobsBoardMapper(JobsBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	private JobsBoardAttachMapper attachMapper;
	
	@Autowired
	private void setJobsBoardAttachMapper(JobsBoardAttachMapper attachMapper) {
		this.attachMapper = attachMapper;
	}

	@Override
	public BoardVO get(Long bno) {
		mapper.updateHits(bno);
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getAll(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	@Transactional
	public void register(BoardVO vo) {
		mapper.insert(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Transactional
	@Override
	public boolean modify(BoardVO vo) {
		log.info("vo : " + vo.getAttachList());
		attachMapper.deleteAll(vo.getBno());
		boolean modifyResult = mapper.update(vo) == 1;
		
		if(modifyResult && vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> {
				System.out.println("attach : " + attach);
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
//		return mapper.update(vo) == 1;
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
	public List<AttachBoardVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}

	
}

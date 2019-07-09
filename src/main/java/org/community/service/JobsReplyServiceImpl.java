package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.JobsReplyPageDTO;
import org.community.domain.JobsReplyVO;
import org.community.mapper.JobsBoardMapper;
import org.community.mapper.JobsReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobsReplyServiceImpl implements JobsReplyService{
	
	Logger log = LoggerFactory.getLogger(JobsReplyServiceImpl.class);
	private JobsReplyMapper mapper;
	private JobsBoardMapper boardMapper;
	
	public JobsReplyServiceImpl(JobsReplyMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
	public void setJobsBoardMapper(JobsBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Transactional
	@Override
	public int register(JobsReplyVO vo) {
		log.info("register : " + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		
		return mapper.insert(vo);
	}

	@Override
	public JobsReplyVO get(Long rno) {
		log.info("rno : " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(JobsReplyVO vo) {
		log.info("modify : " + vo);
		
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("rno : " + rno);
		JobsReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public JobsReplyPageDTO getList(Criteria cri, Long bno) {
		log.info("bno : " + bno);
		return new JobsReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}
	

}

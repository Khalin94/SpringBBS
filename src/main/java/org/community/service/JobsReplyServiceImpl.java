package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.ReplyPageDTO;
import org.community.domain.ReplyVO;
import org.community.mapper.JobsBoardMapper;
import org.community.mapper.JobsReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jobsReplyService")
public class JobsReplyServiceImpl implements ReplyService{
	
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
	public int register(ReplyVO vo) {
		log.info("register : " + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("rno : " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify : " + vo);
		
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("rno : " + rno);
		ReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, Long bno) {
		log.info("bno : " + bno);
		return new ReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}
	

}

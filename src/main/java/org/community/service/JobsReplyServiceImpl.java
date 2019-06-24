package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.JobsReplyPageDTO;
import org.community.domain.JobsReplyVO;
import org.community.mapper.JobsReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JobsReplyServiceImpl implements JobsReplyService{
	
	Logger log = LoggerFactory.getLogger(JobsReplyServiceImpl.class);
	private JobsReplyMapper mapper;
	
	public JobsReplyServiceImpl(JobsReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int register(JobsReplyVO vo) {
		log.info("register : " + vo);
		
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

	@Override
	public int remove(Long rno) {
		log.info("rno : " + rno);
		
		return mapper.delete(rno);
	}

	@Override
	public JobsReplyPageDTO getList(Criteria cri, Long bno) {
		log.info("bno : " + bno);
		return new JobsReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}
	

}

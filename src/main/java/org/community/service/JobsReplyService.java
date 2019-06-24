package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.JobsReplyPageDTO;
import org.community.domain.JobsReplyVO;

public interface JobsReplyService {
	
	public int register(JobsReplyVO vo);
	
	public JobsReplyVO get(Long rno);
	
	public int modify(JobsReplyVO vo);
	
	public int remove(Long rno);
	
	public JobsReplyPageDTO getList(Criteria cri, Long bno);
	

}

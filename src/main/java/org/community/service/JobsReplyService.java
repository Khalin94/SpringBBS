package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.JobsReplyVO;

public interface JobsReplyService {
	
	public int register(JobsReplyVO vo);
	
	public JobsReplyVO get(Long rno);
	
	public int modify(JobsReplyVO vo);
	
	public int remove(Long rno);
	
	public List<JobsReplyVO> getList(Criteria cri, Long bno);
	

}

package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.DevReplyPageDTO;
import org.community.domain.DevReplyVO;

public interface DevReplyService {
	
	public int register(DevReplyVO vo);
	
	public DevReplyVO get(Long rno);
	
	public int modify(DevReplyVO vo);
	
	public int remove(Long rno);
	
	public DevReplyPageDTO getList(Criteria cri, Long bno);

}

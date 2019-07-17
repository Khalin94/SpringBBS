package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.ReplyPageDTO;
import org.community.domain.ReplyVO;

public interface ReplyService {

public int register(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long rno);
	
	public ReplyPageDTO getList(Criteria cri, Long bno);

}

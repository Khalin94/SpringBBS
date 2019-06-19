package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.FreeReplyVO;

public interface FreeReplyService {
	
	public int register(FreeReplyVO vo);
	
	public FreeReplyVO get(Long rno);
	
	public int modify(FreeReplyVO vo);
	
	public int remove(Long rno);
	
	public List<FreeReplyVO> getList(Criteria cri, Long bno);

}

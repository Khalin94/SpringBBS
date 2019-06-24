package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.RuleReplyPageDTO;
import org.community.domain.RuleReplyVO;

public interface RuleReplyService {
	
	public int register(RuleReplyVO vo);
	
	public RuleReplyVO get(Long rno);
	
	public int modify(RuleReplyVO vo);
	
	public int remove(Long rno);
	
	public RuleReplyPageDTO getList(Criteria cri, Long bno);

}

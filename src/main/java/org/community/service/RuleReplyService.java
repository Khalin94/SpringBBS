package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.RuleReplyVO;

public interface RuleReplyService {
	
	public int register(RuleReplyVO vo);
	
	public RuleReplyVO get(Long rno);
	
	public int modify(RuleReplyVO vo);
	
	public int remove(Long rno);
	
	public List<RuleReplyVO> getList(Criteria cri, Long bno);

}

package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevReplyVO;

public interface DevReplyService {
	
	public int register(DevReplyVO vo);
	
	public DevReplyVO get(Long rno);
	
	public int modify(DevReplyVO vo);
	
	public int remove(Long rno);
	
	public List<DevReplyVO> getList(Criteria cri, Long bno);

}

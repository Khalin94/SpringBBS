package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.RuleBoardAttachVO;
import org.community.domain.RuleBoardVO;

public interface RuleBoardService {
	
	public RuleBoardVO get(Long bno);
	
	public List<RuleBoardVO> getAll(Criteria cri);
	
	public void register(RuleBoardVO vo);
	
	public boolean modify(RuleBoardVO vo);
	
	public boolean remove(Long bno);
	
	public int getTotal(Criteria cri);
	
	public List<RuleBoardAttachVO> getAttachList(Long bno);

}

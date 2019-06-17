package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevBoardVO;

public interface DevBoardService {
	
	public DevBoardVO get(Long bno);
	
	public List<DevBoardVO> getAll(Criteria cri);
	
	public void register(DevBoardVO vo);
	
	public boolean modify(DevBoardVO vo);
	
	public boolean remove(Long bno);

	public int getTotal(Criteria cri);
}

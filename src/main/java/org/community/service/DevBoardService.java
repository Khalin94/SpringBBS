package org.community.service;

import java.util.List;

import org.community.domain.DevBoardVO;

public interface DevBoardService {
	
	public DevBoardVO get(Long bno);
	
	public List<DevBoardVO> getAll();
	
	public void register(DevBoardVO vo);
	
	public boolean modify(DevBoardVO vo);
	
	public boolean remove(Long bno);

}

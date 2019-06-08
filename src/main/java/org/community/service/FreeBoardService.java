package org.community.service;

import java.util.List;

import org.community.domain.FreeBoardVO;

public interface FreeBoardService {

	public void register(FreeBoardVO vo);
	
	public FreeBoardVO get(Long bno);
	
	public boolean modify(FreeBoardVO vo);
	
	public boolean delete(Long bno);
	
	public List<FreeBoardVO> getAll();
}

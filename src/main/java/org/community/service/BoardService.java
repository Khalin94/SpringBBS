package org.community.service;

import java.util.List;

import org.community.domain.AttachBoardVO;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;

public interface BoardService {

	public BoardVO get(Long bno);
	
	public List<BoardVO> getAll(Criteria cri);
	
	public void register(BoardVO vo);
	
	public boolean modify(BoardVO vo);
	
	public boolean remove(Long bno);

	public int getTotal(Criteria cri);
	
	public List<AttachBoardVO> getAttachList(Long bno);
}

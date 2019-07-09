package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.JobsBoardAttachVO;
import org.community.domain.JobsBoardVO;

public interface JobsBoardService {
	
	public JobsBoardVO get(Long bno);
	
	public List<JobsBoardVO> getAll(Criteria cri);
	
	public void register(JobsBoardVO vo);
	
	public boolean modify(JobsBoardVO vo);
	
	public boolean remove(Long bno);

	public int getTotal(Criteria cri);
	
	public List<JobsBoardAttachVO> getAttachList(Long bno);
}

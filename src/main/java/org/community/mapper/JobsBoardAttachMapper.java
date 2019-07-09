package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.community.domain.JobsBoardAttachVO;

public interface JobsBoardAttachMapper {
	
	public void insert(JobsBoardAttachVO vo);
	
	@Delete("delete from tbl_jobsAttach where uuid = #{uuid}")
	public void delete(String uuid);
	
	public List<JobsBoardAttachVO> findByBno(Long bno);
	
	@Delete("delete tbl_jobsAttach where bno = #{bno}")
	public void deleteAll(Long bno);
	
	public List<JobsBoardAttachVO> getOldFiles();
	
}

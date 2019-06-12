package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.community.domain.JobsBoardVO;

public interface JobsBoardMapper {
	
	@Select("select bno, title, content, regDate, hits from tbl_jobsBoard where bno = #{bno}")
	public JobsBoardVO read(Long bno);
	
	public List<JobsBoardVO> list();
	
	public void insert(JobsBoardVO vo);
	
	public int update(JobsBoardVO vo);
	
	@Delete("delete tbl_jobsBoard where bno = #{bno}")
	public int delete(Long bno);

}

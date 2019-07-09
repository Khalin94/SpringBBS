package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.JobsBoardVO;

public interface JobsBoardMapper {
	
	@Select("select bno, title, content,writer, regDate, updateDate, hits from tbl_jobsBoard where bno = #{bno}")
	public JobsBoardVO read(Long bno);
	
	public List<JobsBoardVO> list(Criteria cri);
	
	public void insert(JobsBoardVO vo);
	
	public int update(JobsBoardVO vo);
	
	@Delete("delete tbl_jobsBoard where bno = #{bno}")
	public int delete(Long bno);
	
	@Select("select count(*) from tbl_jobsBoard where bno > 0")
	public int getTotal(Criteria cri);
	
	@Update("update tbl_jobsBoard set replyCnt = replyCnt + #{amount} where bno = #{bno}")
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}

package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.JobsReplyVO;

public interface JobsReplyMapper {
	
	public int insert(JobsReplyVO vo);
	
	@Select("select * from tbl_jobsReply where rno = #{rno}")
	public JobsReplyVO read(Long rno);
	
	@Update("update tbl_jobsReply set reply = #{reply}, updateDate = sysdate where rno = #{rno}")
	public int update(JobsReplyVO vo);
	
	@Delete("delete from tbl_jobsReply where rno = #{rno}")
	public int delete(Long rno);
	
	public List<JobsReplyVO> getList(@Param("cri") Criteria cri, @Param("bno")Long bno);

}

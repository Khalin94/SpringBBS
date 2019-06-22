package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.FreeReplyVO;

public interface FreeReplyMapper {
	
	public int insert(FreeReplyVO vo);

	@Select("select * from tbl_freeReply where rno = #{rno}")
	public FreeReplyVO read(Long rno);
	
	@Delete("delete from tbl_freeReply where rno = #{rno}")
	public int delete(Long rno);
	
	@Update("update tbl_freeReply set reply = #{reply}, updateDate = sysdate where rno = #{rno}")
	public int update(FreeReplyVO vo);
	
	public List<FreeReplyVO> getList(@Param("cri") Criteria cri, @Param("bno") long bno); 
	
	@Select("select count(rno) from tbl_freeReply where bno = #{bno}")
	public int getCount(Long bno);
}

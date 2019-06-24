package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.DevReplyVO;

public interface DevReplyMapper {
	
	
	public int insert(DevReplyVO vo);

	@Select("select * from tbl_devReply where rno = #{rno}")
	public DevReplyVO read(Long rno);
	
	@Update("update tbl_devReply set reply = #{reply}, updateDate = sysdate where rno = #{rno}")
	public int update(DevReplyVO vo);
	
	@Delete("delete from tbl_DevReply where rno = #{rno}")
	public int delete(Long rno);
	
	public List<DevReplyVO> getList(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	@Select("select count(rno) from tbl_devReply where bno = #{bno}")
	public int getCount(Long bno);
}

package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.RuleReplyVO;

public interface RuleReplyMapper {
	
	public int insert(RuleReplyVO vo);
	
	@Select("select * from tbl_ruleReply where rno = #{rno}")
	public RuleReplyVO read(Long rno);
	
	@Update("update tbl_ruleReply set reply = #{reply}, updateDate = sysdate where rno = #{rno}")
	public int update(RuleReplyVO vo);
	
	@Delete("delete from tbl_ruleReply where rno = #{rno}")
	public int delete(Long rno);
	
	public List<RuleReplyVO> getList(@Param("cri") Criteria cri, @Param("bno")Long bno);

}

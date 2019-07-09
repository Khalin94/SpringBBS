package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.Criteria;
import org.community.domain.RuleBoardVO;

public interface RuleBoardMapper {
	
	@Select("select * from tbl_ruleBoard where bno = #{bno}")
	public RuleBoardVO read(Long bno);
	
//	@Select("seclt * from tbl_ruleBoard where bno > 0")
	public  List<RuleBoardVO> list(Criteria cri);
	
	public void insert(RuleBoardVO vo);
	
	public int update(RuleBoardVO vo);
	
	@Delete("delete tbl_ruleBoard where bno = #{bno}")
	public int delete(Long bno);
	
	@Select("select count(*) from tbl_ruleBoard where bno > 0")
	public int getTotal(Criteria cri);
	
	@Update("update tbl_ruleBoard set replyCnt = replyCnt + #{amount} where bno = #{bno}")
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}

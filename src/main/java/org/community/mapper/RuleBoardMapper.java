package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;

public interface RuleBoardMapper {
	
	@Select("select * from tbl_ruleBoard where bno = #{bno}")
	public BoardVO read(Long bno);
	
//	@Select("seclt * from tbl_ruleBoard where bno > 0")
	public  List<BoardVO> list(Criteria cri);
	
	public void insert(BoardVO vo);
	
	public int update(BoardVO vo);
	
	@Delete("delete tbl_ruleBoard where bno = #{bno}")
	public int delete(Long bno);
	
	@Select("select count(*) from tbl_ruleBoard where bno > 0")
	public int getTotal(Criteria cri);
	
	@Update("update tbl_ruleBoard set replyCnt = replyCnt + #{amount} where bno = #{bno}")
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
	@Update("update tbl_ruleBoard set hits = hits+1 where bno = #{bno}")
	public void updateHits(Long bno);

}

package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;

public interface DevBoardMapper {
	
	@Select("select * from tbl_devBoard where bno = #{bno}")
	public BoardVO read(Long bno);
	
	public void insert(BoardVO vo);
	
	public int update(BoardVO vo);
	
	@Delete("delete tbl_devBoard where bno = #{bno}")
	public int delete(Long bno);
	
	public List<BoardVO> list(Criteria cri);
	
	@Select("select count(*) from tbl_devBoard where bno > 0")
	public int getTotal(Criteria cri);
	
	@Update("update tbl_devBoard set replyCnt = replyCnt + #{amount} where bno = #{bno}")
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}

package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;

public interface FreeBoardMapper {

//	@Select("select * from tbl_freeboard where bno > 0")
	public List<BoardVO> get(Criteria cri);
	
	public void insert(BoardVO vo);
	
	@Select("Select * from tbl_freeboard where bno=#{bno}")
	public BoardVO read(Long bno);
	
	@Delete("delete tbl_freeboard where bno = #{bno}")
	public int delete(Long bno);
	
	public int update(BoardVO vo); 
	
	@Select("select count(*) from tbl_freeboard where bno > 0")
	public int getTotalCount(Criteria cri);
	
	@Update("update tbl_freeBoard set replycnt = replycnt + #{amount} where bno = #{bno}")
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

	@Update("update tbl_freeBoard set hits = hits+1 where bno=#{bno}")
	public void updateHits(Long bno);
}

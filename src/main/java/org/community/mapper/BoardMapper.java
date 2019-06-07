package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.community.domain.FreeBoardVO;

public interface BoardMapper {

	@Select("select * from tbl_freeboard where bno > 0")
	public List<FreeBoardVO> get();
	/*
	 * @Insert("insert into freeboard (bno, title, content, writer, hits) values (seq_board.nextval, 'boardTest', 'ContentTest', 'User', 0)"
	 * ) public
	 */
	
	public void insert(FreeBoardVO vo);
	
	@Select("Select * from tbl_freeboard where bno=#{bno}")
	public FreeBoardVO read(Long bno);
	
	@Delete("delete tbl_freeboard where bno = #{bno}")
	public int delete(Long bno);
	
	public int update(FreeBoardVO vo); 

}

package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.community.domain.Criteria;
import org.community.domain.FreeBoardVO;

public interface FreeBoardMapper {

//	@Select("select * from tbl_freeboard where bno > 0")
	public List<FreeBoardVO> get(Criteria cri);
	
	public void insert(FreeBoardVO vo);
	
	@Select("Select * from tbl_freeboard where bno=#{bno}")
	public FreeBoardVO read(Long bno);
	
	@Delete("delete tbl_freeboard where bno = #{bno}")
	public int delete(Long bno);
	
	public int update(FreeBoardVO vo); 
	
	@Select("select count(*) from tbl_freeboard where bno > 0")
	public int getTotalCount(Criteria cri);

}

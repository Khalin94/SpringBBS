package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.community.domain.Criteria;
import org.community.domain.DevBoardVO;

public interface DevBoardMapper {
	
	@Select("select * from tbl_devBoard where bno = #{bno}")
	public DevBoardVO read(Long bno);
	
	public void insert(DevBoardVO vo);
	
	public int update(DevBoardVO vo);
	
	@Delete("delete tbl_devBoard where bno = #{bno}")
	public int delete(Long bno);
	
	public List<DevBoardVO> list(Criteria cri);
	
	@Select("select count(*) from tbl_devBoard where bno > 0")
	public int getTotal(Criteria cri);
	

}

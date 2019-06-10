package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.community.domain.RuleBoardVO;

public interface RuleBoardMapper {
	
	@Select("select * from tbl_ruleBoard where bno = #{bno}")
	public RuleBoardVO read(Long bno);
	
	@Select("seclt * from tbl_ruleBoard where bno > 0")
	public  List<RuleBoardVO> list();

}

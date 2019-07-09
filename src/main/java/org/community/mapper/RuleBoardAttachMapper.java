package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.community.domain.RuleBoardAttachVO;

public interface RuleBoardAttachMapper {
	
	public void insert(RuleBoardAttachVO vo);
	
	@Delete("delete from tbl_ruleAttach where uuid = #{uuid}")
	public void delete(String uuid);
	
	public List<RuleBoardAttachVO> findByBno(Long bno);
	
	@Delete("delete tbl_ruleAttach where bno = #{bno}")
	public void deleteAll(Long bno);
	
	public List<RuleBoardAttachVO> getOldFiles();
}

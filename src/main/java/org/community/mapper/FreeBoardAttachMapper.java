package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.community.domain.FreeBoardAttachVO;

public interface FreeBoardAttachMapper {
	
	public void insert(FreeBoardAttachVO vo);
	
	@Delete("delete from tbl_freeAttach where uuid = #{uuid}")
	public void delete(String uuid);
	
	public List<FreeBoardAttachVO> findByBno(Long bno);
	
	@Delete("delete from tbl_freeAttach where bno = #{bno}")
	public void deleteAll(Long bno);
	
	public List<FreeBoardAttachVO> getOldFiles();

}

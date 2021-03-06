package org.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.community.domain.AttachBoardVO;

public interface DevBoardAttachMapper {

	public void insert(AttachBoardVO vo);
	
	@Delete("delete from tbl_devAttach where uuid = #{uuid}")
	public void delete(String uuid);
	
	public List<AttachBoardVO> findByBno(Long bno);
	
	@Delete("delete tbl_devAttach where bno = #{bno}")
	public void deleteAll(Long bno);
	
	public List<AttachBoardVO> getOldFiles();
}

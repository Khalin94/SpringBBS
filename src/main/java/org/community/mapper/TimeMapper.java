package org.community.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("select sysdate FROM dual")
	public String getTime();

}

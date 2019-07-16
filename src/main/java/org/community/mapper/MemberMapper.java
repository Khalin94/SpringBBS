package org.community.mapper;

import java.util.List;

import org.community.domain.AuthVO;
import org.community.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String username);
	
	public void insert(MemberVO vo);
	
	public void insertAuth(AuthVO vo);
	
	public List<AuthVO> findByUsername(String username);

}

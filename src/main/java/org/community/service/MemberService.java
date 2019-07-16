package org.community.service;

import org.community.domain.MemberVO;

public interface MemberService {
	
	public MemberVO get(String username);
	
	public void register(MemberVO vo);

}

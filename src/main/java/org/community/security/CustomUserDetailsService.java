package org.community.security;

import org.community.domain.MemberVO;
import org.community.mapper.MemberMapper;
import org.community.security.domain.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{
	
	private MemberMapper mapper;
	
	@Autowired
	private void setMembermapper(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("Load User By UserName : "+ username);
		
		MemberVO vo = mapper.read(username);
		
		log.warn("queried by member mapper : "+ vo);
		
		return vo == null ? null : new CustomUser(vo);
	}
	
	

}

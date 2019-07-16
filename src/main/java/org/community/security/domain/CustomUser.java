package org.community.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.community.domain.AuthVO;
import org.community.domain.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser  extends User{
	
	Logger log = LoggerFactory.getLogger(CustomUser.class);
	
	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	private AuthVO auth;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUsername(), vo.getPassword(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		log.info("vo.getAuthLost : " + vo.getAuthList());
		
		this.member = vo;
	}
}

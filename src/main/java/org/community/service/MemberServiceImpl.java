package org.community.service;

import org.community.domain.AuthVO;
import org.community.domain.MemberVO;
import org.community.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mapper;
	private PasswordEncoder pwEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
	public void setPassWordEncoder(PasswordEncoder pwEncoder) {
		this.pwEncoder = pwEncoder;
	}
	
	

	@Override
	public void register(MemberVO vo) {
		vo.setPassword(pwEncoder.encode(vo.getPassword()));

		AuthVO authVo = new AuthVO();
		
		authVo.setUsername(vo.getUsername());
		authVo.setAuth("ROLE_USER");
		vo.setAuthList(mapper.findByUsername(authVo.getUsername()));

		mapper.insert(vo);
		mapper.insertAuth(authVo);

	}



	@Override
	public MemberVO get(String username) {
		
		return mapper.read(username);
	}

	
}

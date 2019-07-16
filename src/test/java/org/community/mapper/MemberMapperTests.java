package org.community.mapper;

import org.community.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberMapperTests {
	
	private MemberMapper mapper;
	
	@Autowired
	private void setMemberMapper(MemberMapper mapper) {
		this.mapper = mapper;
	}
//	
//	@Test
//	public void insertTest() {
//		MemberVO vo = new MemberVO();
//		vo.setUsername("testId1234");
//		vo.setPassword("test");
//		vo.setName("test");
//		
//		AuthVO voAuth = new AuthVO();
//		
//		voAuth.setUsername(vo.getUsername());
//		voAuth.setAuth("ROLE_ADMIN");
//		
//		
//		mapper.insert(vo);
//		mapper.insertAuth(voAuth);
//	}
//
	
	@Test
	public void readTest() {
		MemberVO vo = mapper.read("community");
		log.info(vo);
		
		vo.getAuthList().forEach(authVo -> log.info(authVo));
	}
}

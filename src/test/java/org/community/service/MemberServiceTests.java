package org.community.service;

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
public class MemberServiceTests {
	
	private MemberService service;
	
	@Autowired
	public void setMemberService(MemberService service) {
		this.service = service;
	}
	
	@Test
	public void registerTest() {
		MemberVO vo = new MemberVO();
		vo.setUsername("abcdasd123123");
		vo.setPassword("test");
		vo.setName("test");
		
		service.register(vo);
		
		log.info(vo);
		log.info("============================");
		log.info(vo.getAuthList());
		log.info("=============================");
	}
	
	@Test
	public void getTest() {
		log.info(service.get("testId"));
	}

}

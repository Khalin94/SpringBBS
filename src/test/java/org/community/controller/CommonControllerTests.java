package org.community.controller;

import org.community.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Log4j
public class CommonControllerTests {
	
	private WebApplicationContext ctx;
	
	@Autowired
	private void setWebApplicationContext(WebApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void registerTest() throws Exception {
		String page = mock.perform(MockMvcRequestBuilders.post("/register").param("username", "mockTest").param("password", "mocktest").param("checkPassword", "mocktest").param("name", "mocktest")).andReturn().getModelAndView().getViewName();
		
		log.info(page);
		
	}
	
	
	

}

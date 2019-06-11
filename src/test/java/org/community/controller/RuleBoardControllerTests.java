package org.community.controller;

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
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class RuleBoardControllerTests {
	
	private WebApplicationContext ctx;
	
	@Autowired
	private void setWebApplicationContext(WebApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(mock.perform(MockMvcRequestBuilders.get("/ruleBoard/list")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(mock.perform(MockMvcRequestBuilders.get("/ruleBoard/get").param("bno", "2")).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testRegister() throws Exception {
		String page = mock.perform(MockMvcRequestBuilders.post("/ruleBoard/register").param("title", "controller test").param("content", "controller content")
				.param("writer", "controller writer").param("hits", "0")).andReturn().getModelAndView().getViewName();
		
		log.info(page);
	}
	
	@Test
	public void testModify() throws Exception{
		String page = mock.perform(MockMvcRequestBuilders.post("/ruleBoard/modify").param("bno", "8").param("title", "controller modify").param("content", "controller modify")).andReturn().getModelAndView().getViewName();
		
		log.info(page);
	}
	
	@Test
	public void testRemove() throws Exception{
		String page = mock.perform(MockMvcRequestBuilders.post("/ruleBoard/remove").param("bno", "2")).andReturn().getModelAndView().getViewName();
		
		log.info(page);
	}
	

}

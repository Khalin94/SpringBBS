package org.community.controller;

import org.community.service.DevBoardService;
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
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Log4j
public class DevBoardControllerTests {
	
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
	public void testGet() throws Exception {
		log.info(mock.perform(MockMvcRequestBuilders.get("/devBoard/get").param("bno", "2")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testList() throws Exception{
		log.info(
		mock.perform(MockMvcRequestBuilders.get("/devBoard/list")).andReturn().getModelAndView().getModelMap()
		);
	}

}

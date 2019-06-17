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
public class JobsBoardControllerTests {
	
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
/*	
	@Test
	public void testGet() throws Exception {
		log.info(mock.perform(MockMvcRequestBuilders.get("/jobsBoard/get").param("bno", "5")).andReturn().getModelAndView().getModelMap());
	}
*/	
	@Test
	public void testList() throws Exception{
		log.info(mock.perform(MockMvcRequestBuilders.get("/jobsBoard/list").param("pageNum", "1").param("amount", "3")).andReturn().getModelAndView().getModelMap());
	}
/*	
	@Test
	public void testRegister() throws Exception{
		mock.perform(MockMvcRequestBuilders.post("/jobsBoard/register")
				.param("title", "controller test")
				.param("content", "controller test").param("writer", "controller test").param("hits", "0"))
		.andReturn().getModelAndView().getViewName();
	}
	
	
	@Test
	public void testModify() throws Exception{
		log.info(mock.perform(MockMvcRequestBuilders.post("/jobsBoard/modify")
				.param("bno", "5").param("title", "modify controller").param("content", "modify controller").param("writer", "modify controller").param("hits", "0"))
				.andReturn().getModelAndView().getViewName());
		
	}
	
	@Test
	public void testRemove() throws Exception{
		log.info(mock.perform(MockMvcRequestBuilders.post("/jobsBoard/remove")
				.param("bno", "5")).andReturn().getModelAndView().getViewName());
	}
*/
}

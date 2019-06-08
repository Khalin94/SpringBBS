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

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class FreeBoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
/*	
	@Autowired
	private void setWebApplicationContext(WebApplicationContext ctx) {
		this.ctx = ctx;
	}
*/	
	private MockMvc mock;
	
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mock.perform(MockMvcRequestBuilders.get("/freeBoard/list")).andReturn().getModelAndView().getModelMap()
				);
	}
/*	
	@Test
	public void testRegister() throws Exception {
		
		String resultPage = mock.perform(MockMvcRequestBuilders.post("/freeBoard/register").param("title", "Controller Test")
				.param("content", "Controller Content Test").param("writer", "Controller Writer Test")
				.param("hits", "0")).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
	}
*/	
	@Test
	public void testGet() throws Exception{
		log.info(mock.perform(MockMvcRequestBuilders.get("/freeBoard/get").param("bno", "4")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testModify() throws Exception{
		String resultPage = mock.perform(MockMvcRequestBuilders.post("/freeBoard/modify").param("bno", "4").param("title", "Controller Modify").param("content", "Controller Modify Content").param("writer", "Modify Writer")).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
	}
	
	@Test
	public void testDelete() throws Exception{

		String resultPage = mock.perform(MockMvcRequestBuilders.post("/freeBoard/delete").param("bno", "3")).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
}

package org.community.service;

import org.community.domain.RuleBoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RuleBoardServiceTests {
	
	private RuleBoardService service;
	
	@Autowired
	private void setRuleBoardService(RuleBoardService service) {
		this.service = service;
	}
	
	@Test
	public void readTest() {

		log.info(service.get(3l));
	}
	
	@Test
	public void getAllTest() {
		service.getAll().forEach(board -> log.info(board));
	}
	
	@Test
	public void registerTest() {
		RuleBoardVO vo = new RuleBoardVO();
		
		vo.setTitle("service test title");
		vo.setContent("service test content");
		vo.setWriter("service test writer");
		vo.setHits((long)0);
		service.register(vo);
		
		log.info(vo.getBno());
	}
	

	
	@Test
	public void modifyTest() {
		RuleBoardVO vo = service.get(5l);
		
		vo.setTitle("modify Test");
		vo.setContent("modify content");
		
		log.info(service.modify(vo));
	}
	@Test
	public void removeTest() {
		log.info(service.remove(3l));
	}

}

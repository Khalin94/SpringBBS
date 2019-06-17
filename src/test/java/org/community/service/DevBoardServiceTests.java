package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevBoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DevBoardServiceTests {
	
	private DevBoardService service;
	
	@Autowired
	private void setDevBoardService(DevBoardService service) {
		this.service = service;
	}
/*	
	@Test
	public void testGet() {
		log.info(service.get(4l));
	}
*/	
	
	@Test
	public void testGetAll() {
		List<DevBoardVO> list = service.getAll(new Criteria(1, 10));
		list.forEach(ele -> log.info(ele));
	}
	/*
	@Test
	public void testRegister() {
		DevBoardVO vo = new DevBoardVO();
		
		vo.setTitle("register test");
		vo.setContent("register content");
		vo.setWriter("register writer");
		vo.setHits((long)0);
		
		service.register(vo);
		
		log.info(vo.getTitle());
	}
	
	@Test
	public void testModify() {
		DevBoardVO vo = service.get(5l);
		
		vo.setTitle("modify title");
		vo.setHits((long)0);
		
		log.info(service.modify(vo));
	}
	
	@Test
	public void testRemove() {
		log.info(service.remove(7l));
	}
*/
}

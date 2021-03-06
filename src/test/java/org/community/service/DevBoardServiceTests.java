package org.community.service;

import java.util.List;

import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DevBoardServiceTests {
	
	private BoardService service;
	
	@Autowired
	private void setDevBoardService(BoardService devBoardService) {
		this.service = devBoardService;
	}
/*	
	@Test
	public void testGet() {
		log.info(service.get(4l));
	}
*/	
	
	@Test
	public void testGetAll() {
		List<BoardVO> list = service.getAll(new Criteria(1, 10));
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

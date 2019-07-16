package org.community.service;

import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RuleBoardServiceTests {
	
	private BoardService service;
	
	@Autowired
	private void setRuleBoardService(BoardService ruleBoardService) {
		this.service = ruleBoardService;
	}
	
	@Test
	public void readTest() {

		log.info(service.get(3l));
	}
	
	@Test
	public void getAllTest() {
		Criteria cri = new Criteria();
		service.getAll(cri).forEach(board -> log.info(board));
	}
	
	@Test
	public void registerTest() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("service test title");
		vo.setContent("service test content");
		vo.setWriter("service test writer");
		vo.setHits((long)0);
		service.register(vo);
		
		log.info(vo.getBno());
	}
	

	
	@Test
	public void modifyTest() {
		BoardVO vo = service.get(5l);
		
		vo.setTitle("modify Test");
		vo.setContent("modify content");
		
		log.info(service.modify(vo));
	}
	@Test
	public void removeTest() {
		log.info(service.remove(3l));
	}

}

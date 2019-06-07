package org.community.mapper;

import org.community.domain.FreeBoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper; 
/*	
	@Test
	public void testInsert() {
		FreeBoardVO vo = new FreeBoardVO();
		vo.setTitle("Test class Test!");
		vo.setContent("Test class Test!");
		vo.setWriter("Test class Test!");
		vo.setHits((long) 0);
		
		mapper.insert(vo);
		
		log.info(vo);
		
	}
*/	
	@Test
	public void testRead() {
		FreeBoardVO vo = new FreeBoardVO();
		vo = mapper.read(21l);

		log.info(vo);
	}
/*	
	@Test
	public void testDelete() {
		log.info(mapper.delete(5l));
	}
*/
	
	@Test
	public void testUpdate() {
		FreeBoardVO vo = new FreeBoardVO();
		vo.setTitle("update Title");
		vo.setContent("update content");
		vo.setWriter("update Writer");
		vo.setBno(21l);
		
		log.info(mapper.update(vo));
		
	}
}

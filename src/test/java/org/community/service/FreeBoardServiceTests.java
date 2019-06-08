package org.community.service;

import static org.junit.Assert.assertNotNull;

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
public class FreeBoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private FreeBoardService service;
/*	
	@Autowired
	public void setFreeBoardService(FreeBoardService service) {
		this.service = service;
	}
*/	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
/*	
	@Test
	public void testRegister() {
		FreeBoardVO vo = new FreeBoardVO();
		
		vo.setTitle("Service Test");
		vo.setContent("Service Content Test");
		vo.setWriter("Service Test User");
		vo.setHits((long) 0);
		
		service.register(vo);
		
		log.warn(vo.getBno());
	}
*/

	@Test
	public void testGet() {
		log.warn(service.get(4l));
	}
/*	
	@Test
	public void testModify() {
		FreeBoardVO vo = service.get(1l);
		
		vo.setTitle("Modify Title");
		vo.setContent("Modify Content");
		
		log.info(service.modify(vo));
	}
	
	@Test
	public void testDelete() {
		log.info(service.delete(25l));
	}
*/	
	@Test
	public void testGetAll() {
		service.getAll().forEach(board -> log.info(board));
	}
}

package org.community.service;

import static org.junit.Assert.assertNotNull;

import org.community.domain.Criteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FreeBoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
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
		service.getAll(new Criteria(2, 10)).forEach(board -> log.info(board));
	}
}

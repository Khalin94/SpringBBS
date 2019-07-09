package org.community.service;

import org.community.domain.JobsBoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JobsBoardServiceTests {
	
	private JobsBoardService service;
	
	@Autowired
	private void setJobsBoardService(JobsBoardService service) {
		this.service = service;
	}
	
	@Test
	public void testGet() {
		log.info(service.get(1l));
	}
	
	@Test
	public void testRegister() {
		JobsBoardVO vo = new JobsBoardVO();
		
		vo.setTitle("service title");
		vo.setContent("service content");
		vo.setWriter("service writer");
		vo.setHits((long)0);
		service.register(vo);
		
		log.info(vo.getBno());
	}
	
	@Test
	public void testModify() {
		JobsBoardVO vo = service.get(1l);
		
		vo.setTitle("modify test");
		vo.setContent("asdf");
		vo.setWriter("modify test");
		vo.setHits((long) 0);
		log.info(service.modify(vo));
	}
	
	@Test
	public void testRemove() {
		log.info(service.remove(2l));
	}

}

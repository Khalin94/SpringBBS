package org.community.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JobsBoardMapperTests {
	
	private JobsBoardMapper mapper;
	
	@Autowired
	private void setJobsBoardMapper(JobsBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(1l));
	}

}

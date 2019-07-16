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
public class TimeMapperTests {
	
	private TimeMapper timeMapper;

//	private FreeBoardMapper board;
	
	@Autowired
	public void setTimeMapper(TimeMapper timeMapper) {
		this.timeMapper = timeMapper;
	}
	
//	@Autowired
//	public void setBoardMapper(FreeBoardMapper board) {
//		this.board = board;
//	}
	
//	Logger log = Logger.getLogger(this.getClass().getName());
	@Test
	public void testGetTime() {
		log.info(getClass().getName());
		log.info(timeMapper.getTime());
	}
/*	
	@Test
	public void testGet() {
		board.get().forEach(board -> log.info(board));
	}
*/
}

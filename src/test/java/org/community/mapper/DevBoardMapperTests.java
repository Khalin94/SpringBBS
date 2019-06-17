package org.community.mapper;

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
public class DevBoardMapperTests {
	
	private DevBoardMapper mapper;
	
	@Autowired
	private void setDevBoardMapper(DevBoardMapper mapper) {
		this.mapper = mapper;
	}
/*	
	@Test
	public void testRead() {
		log.info(mapper.read(1l));
		
	}
	
	@Test
	public void testInsert() {
		DevBoardVO vo = new DevBoardVO();
		vo.setTitle("mapper test");
		vo.setContent("mapper test");
		vo.setWriter("mapper tst");
		vo.setHits((long) 0);
		mapper.insert(vo);
		
		log.info(vo);
	}
	
	@Test
	public void testUpdate() {
		DevBoardVO vo = mapper.read(2l);
		vo.setTitle("update test");
		
		log.info(mapper.update(vo) == 1);

	}
	
	@Test
	public void testDelete() {

		log.info(mapper.delete(1l) == 1);
	}
*/	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		cri.setKeyword("register");
		cri.setType("T");
		List<DevBoardVO> list = mapper.list(cri);
		
		list.forEach(obj -> log.info(obj));
	}

}

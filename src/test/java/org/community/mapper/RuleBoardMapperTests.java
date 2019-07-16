package org.community.mapper;

import java.util.List;

import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RuleBoardMapperTests {
	
	private RuleBoardMapper mapper;
	
	@Autowired
	private void setRuleBoardMapper(RuleBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<BoardVO> list  = mapper.list(cri);
		
		list.forEach(el -> log.info(el));
	}

}

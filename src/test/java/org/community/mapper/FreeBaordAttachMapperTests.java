package org.community.mapper;

import org.community.domain.FreeBoardAttachVO;
import org.community.domain.FreeBoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FreeBaordAttachMapperTests {
	
	private FreeBoardAttachMapper mapper;
	
	@Autowired
	public void setFreeBoardAttachMapper(FreeBoardAttachMapper mapper) {
		this.mapper = mapper;
	}
	
	private FreeBoardMapper boardMapper;
	
	@Autowired
	public void setFreeBoardMapper(FreeBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		FreeBoardAttachVO vo = new FreeBoardAttachVO();
		FreeBoardVO boardVo = boardMapper.read(4141l);
		
		vo.setBno(boardVo.getBno());
		vo.setFileName("test");
		vo.setFileType(false);
		vo.setUploadPath("/test/test/");
		vo.setUuid("sdfasdfasdf");
		
		mapper.insert(vo);

	}
	

}

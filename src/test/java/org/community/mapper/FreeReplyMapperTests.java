package org.community.mapper;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.FreeReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FreeReplyMapperTests {
	
	private FreeReplyMapper mapper;
	
	
	@Autowired
	private void setFreeReplyMapper(FreeReplyMapper mapper) {
		this.mapper = mapper;
	}
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void insertTest() {
		FreeReplyVO vo = new FreeReplyVO();
		
		vo.setBno(4141l);
		vo.setReply("test Reply");
		vo.setReplyer("test Replyer");
		
		mapper.insert(vo);
	}

	@Test
	public void readTest() {
		FreeReplyVO vo = mapper.read(1l);
		
		log.info(vo);
	}

	@Test
	public void delteTest() {
		log.info(mapper.delete(1l));
	}
	
	@Test
	public void updateTest() {
		FreeReplyVO vo = mapper.read(2l);
		
		vo.setReply("update Test");
		int count = mapper.update(vo);
		log.info("update count = " + count);
	}
	
	@Test
	public void listTest() {
		Criteria cri = new Criteria();
		List<FreeReplyVO> replyList = mapper.getList(cri, 4141l);
		
		replyList.forEach(reply -> log.info(reply));
	}
}

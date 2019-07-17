package org.community.mapper;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DevReplyMapperTests {
	
	private DevReplyMapper mapper;
	
	@Autowired
	private void setDevReplyMapper(DevReplyMapper mapper) {
		this.mapper = mapper;
	}
	
/*	
	@Test
	public void testInsert() {
		DevReplyVO vo = new DevReplyVO();
		
		vo.setBno(24l);
		vo.setReply("댓글 테스트");
		vo.setReplyer("댓글 테스트");

		log.info(mapper.insert(vo));
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(1l));
	}
	
	@Test
	public void testUpdate() {
		DevReplyVO vo = mapper.read(2l);
		
		vo.setReply("업데이트 테스트");
		
		mapper.update(vo);
	}
	
	@Test
	public void testDelete() {
		log.info(mapper.delete(1l));
	}
*/	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria(1, 10);
		
		List<ReplyVO> list = mapper.getList(cri, 24l);
		
		list.forEach(board -> log.info(board));
	}
}

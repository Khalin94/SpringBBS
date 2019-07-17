package org.community.service;

import org.community.domain.ReplyVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DevReplyServicetests {
	
	private ReplyService service;
	
	@Autowired
	private void setDevReplyService(ReplyService service) {
		this.service = service;
	}
	
	@Test
	public void testRegister() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(24l);
		vo.setReply("서비스 테스트");
		vo.setReplyer("서비스 테스트");

		log.info(service.register(vo));
	}

}

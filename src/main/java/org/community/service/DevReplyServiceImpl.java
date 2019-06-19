package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevReplyVO;
import org.community.mapper.DevReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DevReplyServiceImpl implements DevReplyService{

	Logger logger = LoggerFactory.getLogger(DevReplyServiceImpl.class);

	private DevReplyMapper mapper;
	
	@Autowired
	private void setDevReplyMapper(DevReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int register(DevReplyVO vo) {
		logger.info("register : " + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public DevReplyVO get(Long rno) {
		logger.info("get rno : " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(DevReplyVO vo) {
		logger.info("modify vo : " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		logger.info("remove rno : " + rno);
		
		return mapper.delete(rno);
	}

	@Override
	public List<DevReplyVO> getList(Criteria cri, Long bno) {
		logger.info("DevReply bno : "+ bno);
		
		return mapper.getList(cri, bno);
	}
	
	
}

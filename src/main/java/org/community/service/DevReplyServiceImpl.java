package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.DevReplyPageDTO;
import org.community.domain.DevReplyVO;
import org.community.mapper.DevBoardMapper;
import org.community.mapper.DevReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DevReplyServiceImpl implements DevReplyService{

	Logger logger = LoggerFactory.getLogger(DevReplyServiceImpl.class);

	private DevReplyMapper mapper;
	private DevBoardMapper boardMapper;
	
	@Autowired
	private void setDevReplyMapper(DevReplyMapper mapper) {
		this.mapper = mapper;
	}
	@Autowired
	private void setDevBoardMapper(DevBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Transactional
	@Override
	public int register(DevReplyVO vo) {
		logger.info("register : " + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		
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

	@Transactional
	@Override
	public int remove(Long rno) {
		logger.info("remove rno : " + rno);
		
		DevReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}

	@Override
	public DevReplyPageDTO getList(Criteria cri, Long bno) {
		logger.info("DevReply bno : "+ bno);
		
		return new DevReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}
}

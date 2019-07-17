package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.ReplyPageDTO;
import org.community.domain.ReplyVO;
import org.community.mapper.FreeBoardMapper;
import org.community.mapper.FreeReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("freeReplyService")
public class FreeReplyServiceImpl implements ReplyService {
	
	Logger log = LoggerFactory.getLogger(FreeReplyServiceImpl.class);

	private FreeReplyMapper mapper;
	
	@Autowired
	private void setFreeReplyMapper(FreeReplyMapper mapper) {
		this.mapper = mapper;
	}
	
	private FreeBoardMapper boardMapper;
	
	@Autowired
	private void setFreeBoardMapper(FreeBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		
		log.info("register : "+vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);

		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
//		log.info("get : " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
//		log.info("modify : " + vo);
		
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove : " + rno);
		
		ReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}
/*
	@Override
	public List<FreeReplyVO> getList(Criteria cri, Long bno) {
//		log.info("FreeBoard bno : " + bno);
		
		return mapper.getList(cri, bno);
	}
*/

	@Override
	public ReplyPageDTO getList(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}

	
	
	
}

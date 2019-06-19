package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.FreeReplyVO;
import org.community.mapper.FreeReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeReplyServiceImpl implements FreeReplyService {
	
//	Logger log;

	private FreeReplyMapper mapper;
	
	@Autowired
	private void setFreeReplyMapper(FreeReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int register(FreeReplyVO vo) {
//		log.info("register : " + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public FreeReplyVO get(Long rno) {
//		log.info("get : " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(FreeReplyVO vo) {
//		log.info("modify : " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
//		log.info("remove : " + rno);
		
		return mapper.delete(rno);
	}

	@Override
	public List<FreeReplyVO> getList(Criteria cri, Long bno) {
//		log.info("FreeBoard bno : " + bno);
		
		return mapper.getList(cri, bno);
	}
	
	
}

package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.RuleReplyPageDTO;
import org.community.domain.RuleReplyVO;
import org.community.mapper.RuleBoardMapper;
import org.community.mapper.RuleReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuleReplyServiceImpl implements RuleReplyService{
	
	private RuleReplyMapper mapper;
	private RuleBoardMapper boardMapper;
	
	public RuleReplyServiceImpl(RuleReplyMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
	public void setRuleBoardMapper(RuleBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Transactional
	@Override
	public int register(RuleReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public RuleReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(RuleReplyVO vo) {
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		RuleReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}

	@Override
	public RuleReplyPageDTO getList(Criteria cri, Long bno) {
		return new RuleReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}

	
}

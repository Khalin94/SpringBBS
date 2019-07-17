package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.ReplyPageDTO;
import org.community.domain.ReplyVO;
import org.community.mapper.RuleBoardMapper;
import org.community.mapper.RuleReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ruleReplyService")
public class RuleReplyServiceImpl implements ReplyService{
	
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
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		ReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}

	
}

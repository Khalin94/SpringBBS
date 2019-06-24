package org.community.service;

import org.community.domain.Criteria;
import org.community.domain.RuleReplyPageDTO;
import org.community.domain.RuleReplyVO;
import org.community.mapper.RuleReplyMapper;
import org.springframework.stereotype.Service;

@Service
public class RuleReplyServiceImpl implements RuleReplyService{
	
	private RuleReplyMapper mapper;
	
	public RuleReplyServiceImpl(RuleReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int register(RuleReplyVO vo) {
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

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public RuleReplyPageDTO getList(Criteria cri, Long bno) {
		return new RuleReplyPageDTO(mapper.getCount(bno), mapper.getList(cri, bno));
	}

	
}

package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.RuleBoardVO;
import org.community.mapper.RuleBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleBoardServiceImpl implements RuleBoardService{

	private RuleBoardMapper mapper;
	
	@Autowired
	private void setRuleBoardMapper(RuleBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public RuleBoardVO get(Long bno) {
		System.out.println("bno : " + bno);
		return mapper.read(bno);
	}

	@Override
	public List<RuleBoardVO> getAll(Criteria cri) {
			return mapper.list(cri);
	}

	@Override
	public void register(RuleBoardVO vo) {
			mapper.insert(vo);
	}

	@Override
	public boolean modify(RuleBoardVO vo) {
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	
}

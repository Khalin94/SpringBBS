package org.community.service;

import java.util.List;

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

}

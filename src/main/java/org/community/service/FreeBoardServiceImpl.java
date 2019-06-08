package org.community.service;

import java.util.List;

import org.community.domain.FreeBoardVO;
import org.community.mapper.FreeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

	private FreeBoardMapper mapper;
	
	@Autowired
	public void setFreeBoardMapper(FreeBoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void register(FreeBoardVO vo) {
		System.out.println(vo);
		mapper.insert(vo);
	}

	@Override
	public FreeBoardVO get(Long bno) {
		System.out.println(bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(FreeBoardVO vo) {
		System.out.println(vo);
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean delete(Long bno) {
		System.out.println(bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<FreeBoardVO> getAll() {
		return mapper.get();
	}
	
	

}

package org.community.service;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevBoardVO;
import org.community.mapper.DevBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevBoardServiceImpl implements DevBoardService{
	
	private DevBoardMapper mapper;
	
	@Autowired
	private void setDevBoardMapper(DevBoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public DevBoardVO get(Long bno) {
		
		return mapper.read(bno);

	}

	@Override
	public List<DevBoardVO> getAll(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public void register(DevBoardVO vo) {
		mapper.insert(vo);
	}

	@Override
	public boolean modify(DevBoardVO vo) {
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

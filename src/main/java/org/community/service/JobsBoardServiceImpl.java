package org.community.service;

import java.util.List;

import org.community.domain.JobsBoardVO;
import org.community.mapper.JobsBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobsBoardServiceImpl implements JobsBoardService{
	
	private JobsBoardMapper mapper;
	
	@Autowired
	private void JobsBoardMapper(JobsBoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public JobsBoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public List<JobsBoardVO> getAll() {
		return mapper.list();
	}

	@Override
	public void register(JobsBoardVO vo) {
		mapper.insert(vo);
	}

	@Override
	public boolean modify(JobsBoardVO vo) {
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}

}

package org.community.service;

import java.util.List;

import org.community.domain.AttachBoardVO;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.community.mapper.FreeBoardAttachMapper;
import org.community.mapper.FreeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements BoardService{
	
//	Logger log = LoggerFactory.getLogger(FreeBoardServiceImpl.class);

	private FreeBoardMapper mapper;
	private FreeBoardAttachMapper attachMapper;
	
	@Autowired
	public void setFreeBoardMapper(FreeBoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
	public void setFreeBoardAttachMapper(FreeBoardAttachMapper attachMapper) {
		this.attachMapper = attachMapper;
	}

	@Transactional
	@Override
	public void register(BoardVO vo) {
//		log.info(vo.voPrint());
		
//		System.out.println(vo.voPrint());
		
		mapper.insert(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		
		System.out.println("log AttachList : " + vo.getAttachList());
		
		vo.getAttachList().forEach(attach ->{
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		System.out.println(bno);
		mapper.updateHits(bno);
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean modify(BoardVO vo) {
		vo.setHits((long)0);
		System.out.println(vo);
		
		attachMapper.deleteAll(vo.getBno());
		boolean modifyResult = mapper.update(vo) == 1;
		
		
//		if(modifyResult && vo.getAttachList().size() > 0) {
		if(modifyResult && vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		System.out.println(bno);
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getAll(Criteria cri) {
		return mapper.get(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<AttachBoardVO> getAttachList(Long bno) {
		System.out.println("attch List use bno : " + bno);
		return attachMapper.findByBno(bno);
	}
	
	

}

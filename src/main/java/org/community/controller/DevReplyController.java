package org.community.controller;

import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.DevReplyVO;
import org.community.service.DevReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devReplies/*")
public class DevReplyController {
	
	Logger log = LoggerFactory.getLogger(DevReplyController.class);
	
	private DevReplyService service;
	
	@Autowired
	private void setDevReplyService(DevReplyService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody DevReplyVO vo){
		log.info("DevReplyVO : " + vo);
		int insertCount = service.register(vo);
		
		log.info("insert Count : " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<DevReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get rno : " + rno);
		return new ResponseEntity<DevReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value ="/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("rno")Long rno, @RequestBody DevReplyVO vo){
		vo.setRno(rno);
		log.info("modify rno :" + rno);

		return service.modify(vo) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove rno : " + rno);
		return service.remove(rno) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<DevReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<List<DevReplyVO>>(service.getList(cri, bno), HttpStatus.OK);
	}
		
	}

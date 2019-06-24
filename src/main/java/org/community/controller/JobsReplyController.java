package org.community.controller;

import org.community.domain.Criteria;
import org.community.domain.JobsReplyPageDTO;
import org.community.domain.JobsReplyVO;
import org.community.service.JobsReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/jobsReplies/*")
public class JobsReplyController {
	
	Logger log = LoggerFactory.getLogger(JobsReplyController.class);
	
	private JobsReplyService service;
	
	public JobsReplyController(JobsReplyService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody JobsReplyVO vo){
		log.info("register : " + vo);

		return service.register(vo) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<JobsReplyVO> get(@PathVariable("rno") Long rno){
		log.info("rno : " + rno);
		return new ResponseEntity<JobsReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody JobsReplyVO vo){
		vo.setRno(rno);
		
		log.info("rno : " + rno);
		log.info("modify : " + vo);

		return service.modify(vo) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("rno : " + rno);
		return service.remove(rno) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<JobsReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		Criteria cri = new Criteria(page, 10);
		log.info("bno : " + bno);
		log.info("page : " + page);
		return new ResponseEntity<JobsReplyPageDTO>(service.getList(cri, bno), HttpStatus.OK);
	}

}

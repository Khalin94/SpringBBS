package org.community.controller;

import org.community.domain.Criteria;
import org.community.domain.RuleReplyPageDTO;
import org.community.domain.RuleReplyVO;
import org.community.service.RuleReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruleReplies/")
public class RuleReplyController {
	
	private RuleReplyService service;
	
	public RuleReplyController(RuleReplyService service) {
		this.service = service;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", produces = {MediaType.TEXT_PLAIN_VALUE}, consumes = "application/json")
	public ResponseEntity<String> regster(@RequestBody RuleReplyVO vo){
		
		return service.register(vo) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<RuleReplyVO> get(@PathVariable("rno") Long rno){
		return new ResponseEntity<RuleReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("rno")Long rno, @RequestBody RuleReplyVO vo){
		vo.setRno(rno);
		return service.modify(vo) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno")Long rno, @RequestBody RuleReplyVO vo){
		return service.remove(rno) == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK) :
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<RuleReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page ){
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<RuleReplyPageDTO>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	

}

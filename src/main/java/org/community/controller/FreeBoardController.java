package org.community.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.community.domain.AttachBoardVO;
import org.community.domain.BoardVO;
import org.community.domain.Criteria;
import org.community.domain.PageDTO;
import org.community.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/freeBoard/*")
public class FreeBoardController {
	Logger log = LoggerFactory.getLogger(FreeBoardController.class);
	private BoardService service;

	@Autowired
	private void setFreeBoardService(BoardService freeBoardService) {
		this.service = freeBoardService;
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model)  {
			model.addAttribute("list", service.getAll(cri));
			model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes ra) {
		log.info("===========================");
		log.info("register : " + vo);
		vo.setHits((long)0);
		
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info(attach.toString()));
		}
		
		log.info("===========================");
		
		service.register(vo);
		ra.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/freeBoard/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	@PreAuthorize("principal.username == #vo.writer")
	@PostMapping("/modify")
	public String modify(BoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		log.info("title : "+vo.getTitle() + " content : " + vo.getContent() + " writer : " + vo.getWriter()+" reply cnt : " +vo.getReplyCnt()+" bno : " +vo.getBno()+" hits : "+vo.getHits()+" AttachList : " +vo.getAttachList()+" regDate : " + vo.getRegDate()+" updateDate : " +vo.getUpdateDate());
		
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
			return "redirect:/freeBoard/list" + cri.getLink();
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/delete")
	public String delete(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra, String writer) {
		
		log.info("remove : " + bno);
		List<AttachBoardVO> attachList = service.getAttachList(bno);

		if(service.remove(bno)) {
			deleteFiles(attachList);
			ra.addFlashAttribute("result", "success");
		}
	
		return "redirect:/freeBoard/list" + cri.getLink();
	}
	
	private void deleteFiles(List<AttachBoardVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files");
		Iterator<AttachBoardVO> iter = attachList.iterator();
		while(iter.hasNext()) {
			String ele = String.valueOf(iter.next());
			log.info("AttachList : " + ele);
		}
		
		attachList.forEach(attach -> {
			Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
			
			try {
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).contains("image")) {
					Path thumbnail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+ attach.getUuid()+"_"+attach.getFileName());
					
					Files.delete(thumbnail);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("delte error : "+e.getMessage());
				e.printStackTrace();
			}
		});
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachBoardVO>> getAttachList(Long bno){
		log.info("getAttachList : " + bno);
		
		return new ResponseEntity<List<AttachBoardVO>>(service.getAttachList(bno), HttpStatus.OK);
	}

}

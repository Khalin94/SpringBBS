package org.community.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.FreeBoardAttachVO;
import org.community.domain.FreeBoardVO;
import org.community.domain.PageDTO;
import org.community.service.FreeBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	private FreeBoardService service;

	@Autowired
	private void setFreeBoardService(FreeBoardService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model)  {
			model.addAttribute("list", service.getAll(cri));
			model.addAttribute("pageMaker", new PageDTO(cri, service.total(cri)));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(FreeBoardVO vo, RedirectAttributes ra) {
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
	
	@PostMapping("/modify")
	public String modify(FreeBoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
			return "redirect:/freeBoard/list" + cri.getLink();
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		
		log.info("remove : " + bno);
		List<FreeBoardAttachVO> attachList = service.getAttachList(bno);

		if(service.delete(bno)) {
			deleteFiles(attachList);
			ra.addFlashAttribute("result", "success");
		}
	
		return "redirect:/freeBoard/list" + cri.getLink();
	}
	
	private void deleteFiles(List<FreeBoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files");
		Iterator<FreeBoardAttachVO> iter = attachList.iterator();
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
	public ResponseEntity<List<FreeBoardAttachVO>> getAttachList(Long bno){
		log.info("getAttachList : " + bno);
		
		return new ResponseEntity<List<FreeBoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}

}

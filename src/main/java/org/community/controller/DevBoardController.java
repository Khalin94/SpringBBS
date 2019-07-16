package org.community.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@RequestMapping("/devBoard/*")
public class DevBoardController {
	
	Logger log = LoggerFactory.getLogger(DevBoardController.class);
	private BoardService service;
	
	@Autowired
	private void setDevBoardService(BoardService devBoardService) {
		this.service = devBoardService;
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@ModelAttribute("cri") Criteria cri,@RequestParam("bno")Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.getAll(cri));
		model.addAttribute("page", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {
		
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes ra	) {
		vo.setHits((long)0);
		
		log.info("register : " + vo);
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info(attach.toString()));
		}
		service.register(vo);
		ra.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/devBoard/list";
	}

	@PreAuthorize("principal.username == #vo.writer")
	@PostMapping("/modify")
	public String modify(BoardVO vo,@ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
			return "redirect:/devBoard/list" + cri.getLink();
	}
	
	private void deleteFiles(List<AttachBoardVO> list) {
		if(list == null || list.size() == 0) {
			return;
		}
		
		list.forEach(attach ->{
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).contains("image")) {
					Path thumbnaile = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					
					Files.delete(thumbnaile);
				}
			} catch (IOException e) {
				log.info(e.getMessage());
			}
			
		});
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra, String writer) {
		
		List<AttachBoardVO> list = service.getAttachList(bno);

		if(service.remove(bno)) {
			deleteFiles(list);
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/devBoard/list" + cri.getLink();
		}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachBoardVO>> getAttachList(Long bno){
		
		return new ResponseEntity<List<AttachBoardVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	

}

package org.community.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.JobsBoardAttachVO;
import org.community.domain.JobsBoardVO;
import org.community.domain.PageDTO;
import org.community.service.JobsBoardService;
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
@RequestMapping("/jobsBoard/*")
public class JobsBoardController {
	
	Logger log = LoggerFactory.getLogger(JobsBoardController.class);
	private JobsBoardService service;
	
	@Autowired
	private void setJobsBoardService(JobsBoardService service) {
		this.service = service;
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@ModelAttribute("cri") Criteria cri, @RequestParam("bno") Long bno, Model model) {
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
	public String register(JobsBoardVO vo, RedirectAttributes ra) {
		vo.setHits((long)0);

		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> {
				log.info(attach.toString());
			});
		}

		service.register(vo);
		
		ra.addFlashAttribute("result", vo.getBno());
		return "redirect:/jobsBoard/list";
	}
	
	@PreAuthorize("principal.username == #vo.writer")
	@PostMapping("/modify")
	public String modify(@ModelAttribute("cri") Criteria cri, JobsBoardVO vo, RedirectAttributes ra) {
		log.info("===============================");
		log.info("vo : " + vo.getAttachList());
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
		
		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("keyword", cri.getAmount());
		ra.addAttribute("type", cri.getType());
		
		return "redirect:/jobsBoard/list";
	}
	
	private void deleteFiles(List<JobsBoardAttachVO> list) {
		if(list == null || list.size() == 0) {
			return;
		}
		
		list.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).contains("image")) {
					Path thumbnail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbnail);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@ModelAttribute("cri") Criteria cri,Long bno, RedirectAttributes ra, String writer) {
		
		List<JobsBoardAttachVO> list = service.getAttachList(bno);

		if(service.remove(bno)) { 
			deleteFiles(list);
			ra.addFlashAttribute("result", "success");
		}
	/*	
		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("keyword", cri.getKeyword());
		ra.addAttribute("type", cri.getType());
	*/	
		return "redirect:/jobsBoard/list"+cri.getLink();
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<JobsBoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<List<JobsBoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
}

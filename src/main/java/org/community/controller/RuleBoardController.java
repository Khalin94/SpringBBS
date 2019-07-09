package org.community.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.community.domain.Criteria;
import org.community.domain.PageDTO;
import org.community.domain.RuleBoardAttachVO;
import org.community.domain.RuleBoardVO;
import org.community.service.RuleBoardService;
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
@RequestMapping("/ruleBoard/*")
public class RuleBoardController {
	Logger log = LoggerFactory.getLogger(RuleBoardController.class);
	private RuleBoardService service;
	
	@Autowired
	private void setRuleBoardService(RuleBoardService service) {
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
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(RuleBoardVO vo, RedirectAttributes ra) {
		vo.setHits((long) 0);
		
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info(attach.toString()));
		}
		service.register(vo);
		
		ra.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/ruleBoard/list";
	}
	
	@PostMapping("/modify")
	public String modify(RuleBoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/ruleBoard/list" + cri.getLink();
	}
	
	private void deleteFiles(List<RuleBoardAttachVO> list) {
		if(list == null || list.size() ==0) {
			return;
		}
		
		list.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+ attach.getFileName());
				Files.deleteIfExists(file);
				
				Path thumbnail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
				Files.delete(thumbnail);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
	@PostMapping("/remove")
	public String remove(Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		
		List<RuleBoardAttachVO> list = service.getAttachList(bno);

		if(service.remove(bno)) {
			deleteFiles(list);
			ra.addFlashAttribute("result", "successRemove");
		}
		
		return "redirect:/ruleBoard/list" + cri.getLink();
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<RuleBoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<List<RuleBoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}

}

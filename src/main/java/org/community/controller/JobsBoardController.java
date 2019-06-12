package org.community.controller;

import org.community.domain.JobsBoardVO;
import org.community.service.JobsBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jobsBoard/*")
public class JobsBoardController {
	
	private JobsBoardService service;
	
	@Autowired
	private void setJobsBoardService(JobsBoardService service) {
		this.service = service;
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", service.getAll());
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(JobsBoardVO vo, RedirectAttributes ra) {
		vo.setHits((long)0);
		service.register(vo);
		
		ra.addFlashAttribute("result", vo.getBno());
		return "redirect:/jobsBoard/list";
	}
	
	@PostMapping("/modify")
	public String modify(JobsBoardVO vo, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/jobsBoard/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes ra) {
		if(service.remove(bno)) { 
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/jobsBoard/list";
	}
}

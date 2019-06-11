package org.community.controller;

import org.community.domain.RuleBoardVO;
import org.community.service.RuleBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ruleBoard/*")
public class RuleBoardController {
	
	private RuleBoardService service;
	
	@Autowired
	private void setRuleBoardService(RuleBoardService service) {
		this.service = service;
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
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
	public String register(RuleBoardVO vo, RedirectAttributes ra) {
		vo.setHits((long) 0);
		service.register(vo);
		
		ra.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/ruleBoard/list";
	}
	
	@PostMapping("/modify")
	public String modify(RuleBoardVO vo, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/ruleBoard/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes ra) {
		if(service.remove(bno)) {
			ra.addFlashAttribute("result", "successRemove");
		}
		
		return "redirect:/ruleBoard/list";
	}

}

package org.community.controller;

import org.community.domain.Criteria;
import org.community.domain.JobsBoardVO;
import org.community.domain.PageDTO;
import org.community.service.JobsBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public void get(@ModelAttribute("cri") Criteria cri, Long bno, Model model) {
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
	public String register(JobsBoardVO vo, RedirectAttributes ra) {
		vo.setHits((long)0);
		service.register(vo);
		
		ra.addFlashAttribute("result", vo.getBno());
		return "redirect:/jobsBoard/list";
	}
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute("cri") Criteria cri, JobsBoardVO vo, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
		
		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("keyword", cri.getAmount());
		ra.addAttribute("type", cri.getType());
		
		return "redirect:/jobsBoard/list";
	}
	
	@PostMapping("/remove")
	public String remove(@ModelAttribute("cri") Criteria cri,Long bno, RedirectAttributes ra) {
		if(service.remove(bno)) { 
			ra.addFlashAttribute("result", "success");
		}
		
		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("keyword", cri.getKeyword());
		ra.addAttribute("type", cri.getType());
		
		return "redirect:/jobsBoard/list";
	}
}

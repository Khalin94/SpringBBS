package org.community.controller;

import org.community.domain.Criteria;
import org.community.domain.FreeBoardVO;
import org.community.domain.PageDTO;
import org.community.service.FreeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/freeBoard/*")
public class FreeBoardController {
	
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
		vo.setHits((long)0);
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
		if(service.delete(bno)) {
			ra.addFlashAttribute("result", "success");
		}
	
		return "redirect:/freeBoard/list" + cri.getLink();
	}

}

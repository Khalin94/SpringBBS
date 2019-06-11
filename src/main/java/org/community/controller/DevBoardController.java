package org.community.controller;

import org.community.domain.DevBoardVO;
import org.community.service.DevBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/devBoard/*")
public class DevBoardController {
	
	private DevBoardService service;
	
	@Autowired
	private void setDevBoardService(DevBoardService service) {
		this.service = service;
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno")Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", service.getAll());
	}
	
	@PostMapping("/register")
	public String register(DevBoardVO vo, RedirectAttributes ra	) {
		vo.setHits((long)0);
		service.register(vo);
		
		return "redirect:/devBoard/list";
	}

	@PostMapping("/modify")
	public String modify(DevBoardVO vo, RedirectAttributes ra) {
		service.modify(vo);
		
		return "redirect:/devBoard/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes ra) {
		service.remove(bno);
		
		return "redirect:/devBoard/list";
	}
	
	
	
	
	

}

package org.community.controller;

import org.community.domain.Criteria;
import org.community.domain.DevBoardVO;
import org.community.domain.PageDTO;
import org.community.service.DevBoardService;
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
@RequestMapping("/devBoard/*")
public class DevBoardController {
	
	private DevBoardService service;
	
	@Autowired
	private void setDevBoardService(DevBoardService service) {
		this.service = service;
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
	
	@GetMapping("/register")
	public void register() {
		
	}

	@PostMapping("/register")
	public String register(DevBoardVO vo, RedirectAttributes ra	) {
		vo.setHits((long)0);
		service.register(vo);
		ra.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/devBoard/list";
	}

	@PostMapping("/modify")
	public String modify(DevBoardVO vo,@ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		if(service.modify(vo)) {
			ra.addFlashAttribute("result", "success");
		}
			return "redirect:/devBoard/list" + cri.getLink();
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		if(service.remove(bno)) {
			ra.addFlashAttribute("result", "success");
		}
		
		
		return "redirect:/devBoard/list" + cri.getLink();
		}
	
	
	
	
	

}

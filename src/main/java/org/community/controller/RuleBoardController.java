package org.community.controller;

import org.community.service.RuleBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ruleBoard/*")
public class RuleBoardController {
	
	private RuleBoardService service;
	
	@Autowired
	private void setRuleBoardService(RuleBoardService service) {
		this.service = service;
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}

}

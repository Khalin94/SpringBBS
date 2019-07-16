package org.community.controller;

import org.community.domain.AuthVO;
import org.community.domain.MemberVO;
import org.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommonController {
	
	private MemberService service;
	
	@Autowired
	private void setMemberService(MemberService service) {
		this.service = service;
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied!");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error : "+error);
		log.info("logout : "+logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account!");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!");
		}
	}
	
	@GetMapping("/user/login")
	public void login() {
		
	}
	
	@GetMapping("/user/register")
	public void userRegister() {
		
	}
	
	@PostMapping("/register")
	public String userRegister(MemberVO vo, RedirectAttributes ra) {
		log.info("vo : " + vo);
		service.register(vo);
		return "redirect:/";
	}
//	
//	@GetMapping("/customLogout")
//	public void logoutGet() {
//		log.info("custom logout");
//	}
//
	@PostMapping("/customLogout")
	public void logoutPost() {
		log.info("post custom logout");
	}

}

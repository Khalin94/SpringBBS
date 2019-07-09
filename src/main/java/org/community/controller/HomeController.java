package org.community.controller;

import org.community.domain.Criteria;
import org.community.service.DevBoardService;
import org.community.service.FreeBoardService;
import org.community.service.JobsBoardService;
import org.community.service.RuleBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	private FreeBoardService freeService;
	private RuleBoardService ruleService;
	private DevBoardService devService;
	private JobsBoardService jobsService;
	
	@Autowired
	private void setFreeBoardService(FreeBoardService freeService) {
		this.freeService = freeService;
	}
	
	@Autowired
	private void setRuleBoardService(RuleBoardService ruleService) {
		this.ruleService = ruleService;
	}
	
	@Autowired
	private void setDevBoardService(DevBoardService devService) {
		this.devService = devService;
	}
	
	@Autowired
	private void setJobsBoardService(JobsBoardService jobsService) {
		this.jobsService = jobsService;
	}

	@GetMapping("/")
	public String index(Criteria cri, Model model) {
		
		model.addAttribute("freeList", freeService.getAll(cri));
		model.addAttribute("ruleList", ruleService.getAll(cri));
		model.addAttribute("devList", devService.getAll(cri));
		model.addAttribute("jobsList", jobsService.getAll(cri));

		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "user/register";
	}
	
}

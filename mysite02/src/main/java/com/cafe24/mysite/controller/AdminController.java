package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.vo.SiteVo;
import com.cafe24.security.Auth;

@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	@Autowired
	private MainService mainservice;
	
	@RequestMapping({"","main"})
	public String main(Model model) {
		SiteVo siteVo = mainservice.showcurrentSite();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@PostMapping("/main/update")
	public String mainupdate(Model model,@ModelAttribute SiteVo siteVo) {
		
		int result = adminService.mainupdate(siteVo);
		
		if(result == 1) {
			model.addAttribute("updatesuccess", "success");
		}
		return "redirect:/admin/main";
	}
	
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	
	
	
}

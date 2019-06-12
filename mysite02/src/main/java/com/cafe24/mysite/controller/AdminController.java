package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.SiteVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
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
	
	@GetMapping("/role")
	public String role(Model model) {
		
		List<UserVo> list= userService.getUserList(); 
		model.addAttribute("userlist",list);
		return "admin/role";
	}
	
	@PostMapping("/role")
	public String role(@ModelAttribute UserVo vo) {
		
		for(UserVo uservo:vo.getList()) {
			adminService.updateRole(uservo);
		}
		
		
		return "redirect:/";
	}
	
	
	
	
	
}

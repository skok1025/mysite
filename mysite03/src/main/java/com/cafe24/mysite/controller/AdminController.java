package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Auth(role = Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping({"","/main"})
	public String main() {
		return "admin/main";
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
	
	public String site() {
		return "";
	}
}

package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.SiteVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping({"/","/main"})
	public String main(Model model) {
		
		SiteVo siteVo = service.showcurrentSite();
		model.addAttribute("siteVo", siteVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>한글 겁나 잘되겠네</h1>";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setName("김석현");
		vo.setEmail("skok1025@naver.com");
		
		return vo;
	}
	
	
	
	
	
}

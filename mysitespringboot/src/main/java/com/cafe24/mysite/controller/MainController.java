package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@GetMapping({"/","main"})
	public String main() {
		return "main/index";
	}
	@PostMapping({"/","main"})
	public String main2() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요?</h1>";//한글깨짐
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(11L);
		vo.setName("강수진");
		vo.setEmail("tgif@gmail.com");
		
		return vo;//한글깨짐
	}
}

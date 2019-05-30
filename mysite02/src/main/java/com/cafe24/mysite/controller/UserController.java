package com.cafe24.mysite.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/update")
	public String update(UserVo userVo) {
		int result = userService.update(userVo);
		// result 처리

		return "/user/updatesuccess";
	}

//	@Auth
//	@GetMapping("/update")
//	public String update(Model model, HttpSession session,@AuthUser UserVo authuser) {
//		String password = userService.getPassword((UserVo) session.getAttribute("authuser"));
//		UserVo authuser2 = (UserVo)session.getAttribute("authuser");
//		if(authuser2 == null) {
//			return "redirect:/user/login?auth=false";
//		}
//		
//		model.addAttribute("password", password);
//
//		return "/user/update";
//	}
	
	@Auth
	@RequestMapping( value="/update", method=RequestMethod.GET )
	public String update(
		@AuthUser UserVo authUser,
		Model model ){
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/update";
	}
	
	

	@GetMapping(value = "/join")
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@PostMapping(value = "/join")
	public String join(
			@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model) {
		System.out.println(userVo);

		// DB 작업
		// userDao.insert(userVo);
		
		if(result.hasErrors()) {
		//List<ObjectError> list =result.getAllErrors();
//		for(ObjectError error:list) {
//			System.out.println(error);
//		}
		//	model.addAllAttributes(result.getModel());
			return "/user/join";
		}
		userService.join(userVo);

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "user/login";
	}

//	// @RequestMapping(value = "/login",method = RequestMethod.POST)
//	@PostMapping(value = "/auth")
//	// public String login(@RequestParam(value = "email",required =
//	// true,defaultValue = "") String email) {
//	public String auth(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
//
//		// UserVo authuser = userDao.get(userVo);
//		UserVo authuser = userService.getUser(userVo);
//
//		if (authuser == null) {
//			model.addAttribute("result", "fail"); //
//			return "user/login";
//		}
//		// session 처리
//		session.setAttribute("authuser", authuser);
//
//		return "redirect:/";
//	}

//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session) {
//
//		session.removeAttribute("authuser");
//		session.invalidate();
//
//		return "redirect:/";
//	}

//	@ExceptionHandler(Exception.class)
//	public String handleUserDaoException() {
//		System.out.println("!!!!!!!!!!!!!");
//		return "error/exception";
//	}

}
package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;


@Controller
@RequestMapping("/user")
public class UserController {
	
	public UserController() {
		System.out.println("userController 생성");
	}
	@Autowired
	private UserService userService;
	
	//회원가입 폼
	@RequestMapping(value="/join",method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	//회원가입 동작
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String join(@ModelAttribute  @Valid UserVo userVo, BindingResult result,Model model) {
		
		//Valid 체크가 틀릴 시, join form으로 넘김
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel()); // Map으로 보내줌
			return "user/join";
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";//dispatcher가 컨텍스트 패스를 붙이고 다시 리다이렉트를 보낸다.
	}
	//회원가입 성공
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	
	@Auth
	@RequestMapping( value="/update", method=RequestMethod.GET )
	public String update(@AuthUser UserVo authUser,Model model ){
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/update";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(@AuthUser UserVo updateUserVo,HttpSession session,Model model) {
		
		boolean result = userService.update(updateUserVo);
		if(result) {
			session.setAttribute("authUser", updateUserVo);
		}
		return "user/updatesuccess";
	}

	@RequestMapping(value ="/auth", method = RequestMethod.POST)
	public void auth() {}
	@RequestMapping(value ="/logout", method = RequestMethod.GET)
	public void logout() {}
//	컨트롤러에서 처리
//	@ExceptionHandler(Exception.class)
//	public String handleUserDaoException() {
//		return "error/exception";
//	}

}
//@RequestMapping(value="/auth",method = RequestMethod.POST)
//public String login(@RequestParam(value="email",required = true,defaultValue = "")String email,
//					@RequestParam(value="password",required = true,defaultValue = "")String password,
//					HttpSession session,
//					Model model) {
//	
//	UserVo authUser = userService.getUser(new UserVo(email,password));
//	if(authUser == null) {
//		model.addAttribute("result", "fail");
//		return "/user/login";
//	}
//	
//	//session 처리(지금은 HttpSession을 사용(기술침투)하지만 나중엔 제거 예정-interceptor)
//	session.setAttribute("authUser", authUser);
//	
//	
//	return "redirect:/";
//}
//@RequestMapping(value="/logout",method = RequestMethod.GET)
//public String logout(HttpSession session) {
//	
//	session.removeAttribute("authUser");
//	return "redirect:/";
//}
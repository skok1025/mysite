package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.dto.BoardDTO;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/board")
public class BoardController {

	
	@Autowired
	private BoardService boardservice;
	
	
	@RequestMapping("/list")
	public String list(Model model,
						@RequestParam(value = "page",required = true,defaultValue = "1") int page,
						@RequestParam(value = "kwd", required = true,defaultValue = "") String kwd,
						HttpSession session) {
		session.setAttribute("visited", false);
		//System.out.println(page);
		List<BoardVo> list = boardservice.getList(page,kwd);
		
		// pagebar
		String pagebar = boardservice.createPagebar(page,kwd);
		System.out.println(pagebar);
		model.addAttribute("pagebar", pagebar);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@Auth(role = Role.USER)
	@GetMapping("/write")
	public String write(
			@ModelAttribute BoardVo boardVo) {
		return "board/write";
	}
	
	@PostMapping("/write")
	public String write(
			@RequestParam(value = "replyno", required = false, defaultValue="0") int replyno,
			@ModelAttribute @Valid BoardVo boardVo,
			BindingResult bindresult,
			Model model) {
		int result = 0;
		
		result = boardservice.writeContents(replyno,boardVo);
		if(bindresult.hasErrors()) {
			List<ObjectError> list =bindresult.getAllErrors();
			for(ObjectError error:list) {
				System.out.println(error);
			}
			model.addAllAttributes(bindresult.getModel());
			return "/board/write";
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/view")
	public String view(Model model,String no,HttpSession session) {
		BoardVo vo = boardservice.viewContent(no,(Boolean)session.getAttribute("visited"));
		model.addAttribute("vo",vo);
		session.setAttribute("visited",true);
		return "board/view";
	}
	
	@GetMapping("/del")
	public String del(String no) {
		
		int result = boardservice.deleteContents(no);
		
		return "redirect:/board/list";
	}
	
	
	
	
	
}

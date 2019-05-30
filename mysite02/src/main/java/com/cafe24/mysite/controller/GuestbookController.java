package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@PostMapping("/list")
	public String list(Model model,GuestbookVo guestbookVo) {
		
		int result = guestbookService.addContent(guestbookVo);
		// result 에 따른 처리
		
		return "redirect:/guestbook/list";
	}
	
	@GetMapping("/delete/{no}")
	public String delete(Model model,@PathVariable(value = "no") Long no) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	@PostMapping("/delete")
	public String delete(GuestbookVo guestbookVo) {

		int result = guestbookService.deleteContent(guestbookVo);
		// result 에 따른 처리
	
		return "redirect:/guestbook/list";
	}
	
	
	
	

}

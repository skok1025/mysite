package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestBookService;
import com.cafe24.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	public GuestBookController() {
		System.out.println("GuestBookController 생성");
	}
	
	@RequestMapping(value="")
	public String guestbook(Model model) {
		model.addAttribute("list", guestBookService.getlist());
		return "guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestBookVo guestbookvo) {
		guestBookService.add(guestbookvo);
		return "redirect:/guestbook";
	}
	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable(value="no")long no) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo guestbookvo,Model model) {
		boolean result =guestBookService.delete(guestbookvo);
		if(result) {
			return "redirect:/guestbook";
		}else {
			model.addAttribute("result", "fail");
			return "redirect:/guestbook/delete/"+guestbookvo.getNo();
		}
		
	}
}

package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//게시판 페이지
	@RequestMapping(value={"","/list"})
	public String list(Model model,@RequestParam(value="page", required = true,defaultValue = "1")int page) {
		Map<String,Object> map = boardService.getList(page);
		model.addAttribute("map", map);
		
		return "board/list";
	}
	@RequestMapping("/search")
	public String search(Model model,@RequestParam("kwd")String kwd,@RequestParam(value="page", required = true,defaultValue = "1")int page) {
		Map<String,Object> map = boardService.getSearch(kwd,page);
		model.addAttribute("map", map);
		model.addAttribute("kwd", kwd);
		return "board/searchList";
	}
	
	//write페이지 가기
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/write",method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	//답글
	@Auth
	@RequestMapping(value="/write/{no}",method = RequestMethod.GET)
	public String write(@PathVariable(value="no")long no,Model model) {
		
		model.addAttribute("boardVo", boardService.getTitle(no));
		return "board/write";
	}
	
	//작성
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		if(boardVo.getNo()==null) {
			boardService.write(boardVo);
		}else {
			boardService.reply(boardVo);
		}
		
		return "redirect:/board/view/"+boardService.lastID();
	}
	//view페이지
	@RequestMapping(value="/view/{no}", method = RequestMethod.GET)
	public String view(Model model,@PathVariable(value="no")long no,HttpServletRequest request,HttpServletResponse response) {
		int count = 0;
		String[] list = null;
		String viewList = "";
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if ("hit".equals(c.getName())) {
					list = c.getValue().split("/");
					viewList = c.getValue();
					break;
				}
			}
		}
		if(list==null) {
			// 조회수 증가
			boardService.updateByHit(no);
			// 쿠키쓰기
			Cookie cookie = new Cookie("hit", String.valueOf(no+"/"));
			cookie.setMaxAge(60*60*24);// 하루 셋팅
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}else {
			//조회수 판단하기
			for(String s : list) {
				//읽은 글이 있으면
				if(Long.parseLong(s)== no) {
					count++;
				}
			}
			if(count==0) {
				// 조회수 증가
				boardService.updateByHit(no);
				// 쿠키쓰기
				Cookie cookie = new Cookie("hit", String.valueOf(viewList+no+"/"));
				cookie.setMaxAge(60*60*24);// 하루 셋팅
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
		}
		
		model.addAttribute("boardVo", boardService.getView(no));

		return "board/view";
	}
	//수정 화면 
	@Auth
	@RequestMapping(value="/modify/{no}", method = RequestMethod.GET)
	public String update(@PathVariable(value="no")long no,Model model) {
		BoardVo boardVo = boardService.getView(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	//수정
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVo boardVo) {
		boardService.modify(boardVo);
		return "redirect:/board/view/"+boardVo.getNo();
	}
	//삭제
	@Auth
	@RequestMapping(value="/delete/{no}")
	public String delete(@PathVariable(value="no")long no,@AuthUser UserVo authUser) {
		//삭제
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("user_no", authUser.getNo());
		
		boardService.delete(map);
		return "redirect:/board";
	}
}

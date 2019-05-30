package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class EditAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		
		if(((UserVo)session.getAttribute("authuser")).getNo()!= Long.parseLong(no)) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		
		new BoardDao().update(vo);
		WebUtil.redirect(request, response, request.getContextPath()+"/board");
	}

}

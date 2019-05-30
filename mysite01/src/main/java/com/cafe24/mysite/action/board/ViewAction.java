package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.get(no);
		
		request.setAttribute("vo", vo);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}

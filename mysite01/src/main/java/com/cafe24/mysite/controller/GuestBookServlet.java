package com.cafe24.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.guestbook.GuestbookActionFactory;
import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;


@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		String actionName = request.getParameter("a");
		Action action = new GuestbookActionFactory().getAction(actionName);
		action.execute(request, response);
		
//		if("add".equals(actionName)) {
//			
//
//			String name = request.getParameter("name");
//			String password = request.getParameter("password");
//			String contents = request.getParameter("content");
//			
//			GuestbookVo v= new GuestbookVo();
//			v.setName(name);
//			v.setPassword(password);
//			v.setContents(contents);
//			
//			System.out.println(new GuestbookDao().insert(v));
//			WebUtil.redirect(request, response, request.getContextPath()+"/guestbook");
//			
//		} else if("deleteform".equals(actionName)) {
//			String no = request.getParameter("no");
//			request.setAttribute("no", no);
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
//		} else if("delete".equals(actionName)) {
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			GuestbookVo vo = new GuestbookVo();
//			vo.setNo(Long.parseLong(no));
//			vo.setPassword(password);
//			
//			new GuestbookDao().delete(vo);
//			
//			WebUtil.redirect(request, response, request.getContextPath()+"/guestbook");
//		} else {
//			/* list action */
//			GuestbookDao dao = new GuestbookDao();
//			List<GuestbookVo> list = dao.getList();
//			
//			request.setAttribute("list", list);
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

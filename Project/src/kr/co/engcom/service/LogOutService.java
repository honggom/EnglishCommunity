package kr.co.engcom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;

public class LogOutService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		String msg = "로그아웃 되셨습니다.";
		String url = "Main.go";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return forward;
	}
}

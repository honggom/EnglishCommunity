package kr.co.engcom.service.cmboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;

public class CMBoardCheckLoginService implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
			   	
				HttpSession session = request.getSession();
			   	String userid = (String)session.getAttribute("userid");
			   	String msg = "";
			   	String url = "";
			   	
			   	System.out.println("밑에가 세션");
			   	System.out.println(userid);
			   	if(userid==null) {
				   	ActionForward forward = new ActionForward();
				   	forward.setRedirect(false);
				   	forward.setPath("/WEB-INF/views/redirect.jsp");
			   		msg = "로그인이 필요합니다.";
			   		url = "list.cm";
			   		request.setAttribute("msg", msg);
			   		request.setAttribute("url", url);
			   		return forward;
			   	}else {
			   		//로그인 돼어 있으니 write view로 이동
				   	ActionForward forward = new ActionForward();
				   	forward.setRedirect(false);
			   		forward.setPath("/goToWrite.cm");
			   		return forward;
			   	}

			 }
}
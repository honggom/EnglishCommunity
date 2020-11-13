package kr.co.engcom.service.cmboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.CMReplyDAO;

public class CMBoardDeleteService implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 throws Exception{ 
		
			 	int boardNumber = Integer.parseInt(request.getParameter("num"));
			 	
			 	System.out.println("hello"+boardNumber);
			 	CMReplyDAO dao = new CMReplyDAO();
			 	int num = dao.CMReplyAndBoardDelete(boardNumber);
			 	
			 	String msg = "";
			 	String url = "";
			 	
			 	if(num > 0) {
			 		System.out.println("정상 삭제");
				   	ActionForward forward = new ActionForward();
				   	forward.setRedirect(false);
			   		forward.setPath("/WEB-INF/views/redirect.jsp");
			   		
			   		msg = "정상 삭제되었습니다.";
			   		url = "list.cm";
			   		
			   		request.setAttribute("msg", msg);
			   		request.setAttribute("url", url);
			   		
			   		return forward;
			 	}else {
			 		//else를 탈일이 없기는 하지만 코드상 문제가 있음 
			 		System.out.println("오류오류오류");
			 		return null;
			 	}
			 }
}
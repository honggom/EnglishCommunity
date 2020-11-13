package kr.co.engcom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.TimeLineDao;

public class TimeLineDeleteService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String msg="";
		String url="";
		
		
		int timelinenumber = Integer.parseInt(request.getParameter("timeLineNumber"));
		try {
			TimeLineDao timelinedao = new TimeLineDao();
			
			int rowcount = timelinedao.deleteTimeLine(timelinenumber);
			
			if(rowcount > 0) {
				msg="정상적으로 삭제되었습니다.";
				url="index2.jsp";
			}else {
				msg="삭제에 실패하였습니다.";
				url="index2.jsp";
				System.out.println("삭제실패2");
			}
		}catch (Exception e) {
			msg="삭제에 실패하였습니다.";
			url="index2.jsp";
			System.out.println("삭제실패1"+e);
		}
		
		request.setAttribute("board_msg",msg);
	    request.setAttribute("board_url", url);
	
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    forward.setPath("/WEB-INF/views/redirect2.jsp");
	
	return forward;
	}

}

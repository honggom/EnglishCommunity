package kr.co.engcom.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.TimeLineDao;
import kr.co.engcom.dto.TimeLine;

public class TimeLineEditService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int timelinenumber = Integer.parseInt(request.getParameter("timeLineNumber"));
		
		TimeLineDao timelinedao = new TimeLineDao();
		try {
			TimeLine timeline = timelinedao.selectTimeLine(timelinenumber);
			
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("TimeLine",timeline);
			
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/timeline/TimeLineEdit.jsp");
			
			dis.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;
	}

}

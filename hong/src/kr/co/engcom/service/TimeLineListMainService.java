package kr.co.engcom.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.dto.TimeLine;
import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.TimeLineDao;

public class TimeLineListMainService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = null;;
		
		try {
		TimeLineDao timelinedao= new TimeLineDao();
		List<TimeLine> timelinelist = timelinedao.getTimeLineListMain();
		request.setAttribute("timelinelist", timelinelist);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/timeline/TimeLineListMain.jsp");
		
		}catch (Exception e) {
			System.out.println("관리자 회원목록 불러오기 중 오류 발생 : " + e.getMessage());
		}
		return forward;
	}

}

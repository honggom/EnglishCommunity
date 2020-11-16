package kr.co.engcom.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.TimeLineDao;
import kr.co.engcom.dto.TimeLine;


public class TimeLineListService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = new ActionForward();
		
		TimeLineDao timelinedao = new TimeLineDao();
		
		String ps = request.getParameter("ps");		// 클라이언트로부터
		String cp = request.getParameter("cp");		// 보고싶은 글 수와 현재 페이지 번호를 받아옴
		
		if(ps == null || ps.trim().equals("")) {	// ps값이 없는 경우 기본 페이지 크기 지정
			ps = "5";
		}
		if(cp == null || cp.trim().equals("")) {	// cp값이 없는 경우 현재 페이지 지정
			cp = "1";
		}
		
		int pagesize = Integer.parseInt(ps);
		int currpage = Integer.parseInt(cp);
		int totalcount = timelinedao.getTimeLineListCount();
		int pagecount = 0;
		
		if (totalcount % pagesize == 0) {
			pagecount = totalcount / pagesize;
		} else {
			pagecount = (totalcount / pagesize) + 1;
		}
		
		List<TimeLine> timelinelist = timelinedao.getTimeLineList(currpage, pagesize);
		
		request.setAttribute("timelinelist", timelinelist);
		request.setAttribute("pagesize", pagesize);		// 출력할 글 수(명수)
		request.setAttribute("pagecount", pagecount);	// 
		request.setAttribute("currpage", currpage);	
		
		forward.setPath("/WEB-INF/views/timeline/TimeLineList2.jsp");
		return forward;

	}
}


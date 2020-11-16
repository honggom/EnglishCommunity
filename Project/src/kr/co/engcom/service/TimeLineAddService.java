package kr.co.engcom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.dto.TimeLine;
import kr.co.engcom.action.Action;
import kr.co.engcom.dao.TimeLineDao;
import kr.co.engcom.action.ActionForward;

public class TimeLineAddService implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String content_eng = request.getParameter("content_eng");
		String content_kor = request.getParameter("content_kor");

		/*
		 * int timelinenumber =
		 * Integer.parseInt(request.getParameter("timelinenumber"));
		 */

		TimeLineDao timelinedao = new TimeLineDao();

		String msg = "";
		String url = "";
		int rowcount = 0;

		rowcount = timelinedao.TimeLineInsert(new TimeLine(content_eng, content_kor));

		if (rowcount > 0) {
			msg = "글이 등록되었습니다.";
			url = "default.jsp";
		} else {
			msg = "글 등록이 실패하였습니다.";
			url = "TimeLineWrite.tc";
		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect2.jsp");
		return forward;

	}
}
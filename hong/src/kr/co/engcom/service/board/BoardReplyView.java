package kr.co.engcom.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.BoardDto;

public class BoardReplyView implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	 	ActionForward forward = new ActionForward();
	 	
		BoardDao boarddao=new BoardDao();
   		BoardDto boarddata=new BoardDto();
   		
   		int num=Integer.parseInt(request.getParameter("num"));
   		
   		boarddata=boarddao.getDetail(num);
   		
   		if(boarddata==null){
   			System.out.println("답장 페이지 이동 실패");
   			return null;
   		}
   		System.out.println("답장 페이지 이동 완료");
   		
   		request.setAttribute("boarddata", boarddata);
   		
   		forward.setRedirect(false);
   		forward.setPath("./WEB-INF/views/board/BoardReply.jsp");
   		return forward;
	}
}

package kr.co.engcom.service.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.BoardDto;

public class BoardReplyService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
	 	request.setCharacterEncoding("utf-8");
	 	ActionForward forward = new ActionForward();
	 	
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
	 	System.out.println("id: " + id);
		BoardDao boarddao=new BoardDao();
   		BoardDto boarddata=new BoardDto();
   		int result=0;
   		
   		boarddata.setContentNumber(Integer.parseInt(request.getParameter("contentNumber")));
   		boarddata.setId(id);
   		boarddata.setContentTitle(request.getParameter("contentTitle"));
   		boarddata.setContent(request.getParameter("content"));
   		boarddata.setBoardName(request.getParameter("boardName"));
   		boarddata.setRefer(Integer.parseInt(request.getParameter("refer")));
   		boarddata.setDepth(Integer.parseInt(request.getParameter("depth")));
   		boarddata.setStep(Integer.parseInt(request.getParameter("step")));
   		
   		
   		
   		result=boarddao.boardReply(boarddata);
   		if(result==0){
   			System.out.println("답장 실패");
   			return null;
   		}
   		System.out.println("답장 완료");
   		
   		forward.setRedirect(false);
   		forward.setPath("BoardDetailService.bo?num="+result);
   		return forward;
	}
}

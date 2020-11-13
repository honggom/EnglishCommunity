package kr.co.engcom.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;

public class BoardDeleteService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, SQLException {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
		
	   	boolean result=false;
	   	boolean usercheck=false;
	   	boolean userReplyCheck = false;
	   	int num=Integer.parseInt(request.getParameter("num"));
	   	System.out.println("BoardDeleteService/num: " + num);
	   	
	   	BoardDao boarddao=new BoardDao();
	   	usercheck=boarddao.isBoardWriter(num, id);
	   	userReplyCheck = boarddao.isRepliesWriter(num, id);
	   	
	   	
	   	if(usercheck==false){
	   		response.setContentType("text/html;charset=utf-8");
	   		PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('삭제할 권한이 없습니다.');");
	   		out.println("location.href='./BoardList.bo';");
	   		out.println("</script>");
	   		out.close();
	   		return null;
	   	}
	   	
	   	
	   	result=boarddao.boardDelete(num, id, userReplyCheck);
	   	if(result==false){
	   		System.out.println("게시판 삭제 실패");
	   		return null;
	   	}
	   	
	   	
	   	System.out.println("게시판 삭제 성공");
	   	forward.setRedirect(true);
   		forward.setPath("BoardList.bo");
   		return forward;
	}
}

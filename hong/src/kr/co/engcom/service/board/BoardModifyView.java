package kr.co.engcom.service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.BoardDto;

public class BoardModifyView implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
		
	   	boolean result=false;
	   	boolean usercheck=false;
		
		int num =Integer.parseInt(request.getParameter("num"));
		
		BoardDao boarddao = new BoardDao();
		usercheck=boarddao.isBoardWriter(num, id);
		BoardDto boarddata = new BoardDto();
		
		if(usercheck==false){
	   		response.setContentType("text/html;charset=utf-8");
	   		PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('수정할 권한이 없습니다.');");
	   		out.println("location.href='./BoardList.bo';");
	   		out.println("</script>");
	   		out.close();
	   		return null;
	   	}
		
		
		boarddata =boarddao.getDetail(num);
	
		if(boarddata == null){
			System.out.println("수정 상세보기 실패");
			return null;
		}
		System.out.println("수정 상세보기 성공");
		
		// BoardModify.jsp 
		// request.getAttribute("boarddata") 데이터를 받는다
		request.setAttribute("boarddata", boarddata);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/board/BoardModify.jsp");
		
		return forward;
		
		
	}

}

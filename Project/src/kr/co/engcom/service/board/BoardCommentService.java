package kr.co.engcom.service.board;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.CommentTableDto;

public class BoardCommentService implements Action{
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
       ActionForward forward = new ActionForward();
       
       // 게시판 댓글 등록 시 아래와 같은 파라미터를 가져온다.
       String id = request.getParameter("userid");
       int contentNumber = Integer.parseInt(request.getParameter("contentNumber"));
       String commentContent = request.getParameter("commentContent");       
      
       String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
       Date redate = Date.valueOf(date); 

       CommentTableDto dto = new CommentTableDto();
         
       dto.setId(id);
       dto.setCommentContent(commentContent);
       dto.setContentNumber(contentNumber);
       dto.setRedate(redate);
         
       BoardDao dao = new BoardDao();

       int result = dao.insertComment(dto);
       
       String msg = "";
       String url = "";
       
       if(result > 0) {
          msg = "댓글이 작성되었습니다.";
       } else {
          msg = "댓글이 작성되지 않았습니다.";
       }

       url = "BoardDetailService.bo?num=" + contentNumber;
         
       request.setAttribute("msg", msg);
       request.setAttribute("url", url);
       
         forward.setRedirect(false);
         forward.setPath("/WEB-INF/views/redirect.jsp");
         return forward;
   }
}




/*
public class BoardCommentService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	 	ActionForward forward = new ActionForward();
	 	
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
	 	System.out.println("id: " + id);
		BoardDao boarddao=new BoardDao();
   		BoardDto boarddata=new BoardDto();
   		int result=0;
   		
   		boarddata.setContentNumber(Integer.parseInt(request.getParameter("contentNumber")));
   		boarddata.setId(id);
   		boarddata.setReplyContent(request.getParameter("replyContent"));
   		
   		forward.setRedirect(false);
   		forward.setPath("BoardDetailAction.bo?num="+result);
   		return forward;
	}
}
*/
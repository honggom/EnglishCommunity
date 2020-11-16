package kr.co.engcom.service.cmboard;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.CMReplyDAO;
import kr.co.engcom.dto.CMReplyDTO;

public class CMReplyWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
			String answer = request.getParameter("answer");
			String CMReplyId = request.getParameter("CMReplyId");
			int CMBoardNumber = Integer.parseInt(request.getParameter("CMBoardNumber"));
		    ActionForward forward = new ActionForward();
		    forward.setRedirect(false);
		    
			System.out.println("==================");
			System.out.println(CMReplyId);
			System.out.println("==================");
			
			String msg = "";
			String url = "";
			
			if(CMReplyId.equals("null")) {
				System.out.println("로그인이 필요함");
				forward.setPath("/WEB-INF/views/redirect.jsp");
		   		msg = "로그인이 필요합니다.";
		   		url = "CMBoardDetailService.cm?num="+CMBoardNumber;
		   		request.setAttribute("msg", msg);
		   		request.setAttribute("url", url);
		   		return forward;
		   		
			}else {
				
				System.out.println(answer);
				System.out.println(CMReplyId);
				System.out.println(CMBoardNumber);
	    	
				//리플라이 DAO를 통해 DB에 insert
				CMReplyDTO dto = new CMReplyDTO();
				CMReplyDAO dao = new CMReplyDAO();
				
				dto.setCMBoardNumber(CMBoardNumber);
				dto.setCMReplyContent(answer);
				dto.setCMReplyId(CMReplyId);
				
				boolean check = dao.CMReplyInsert(dto);
				if(check) {
					System.out.println("댓글이 정상적으로 DB에 insert됨");
				}else {
					System.out.println("댓글 DB삽입 실패");
				}
				
		   		msg = "댓글 작성 성공";
		   		url = "CMBoardDetailService.cm?num="+CMBoardNumber;
		   		request.setAttribute("msg", msg);
		   		request.setAttribute("url", url);
		   		forward.setPath("/WEB-INF/views/redirect.jsp");
			    //글 작성 로직 처리하고 리스트 화면으로 이동 
			   
			return forward;
			}
			
		
	}

}


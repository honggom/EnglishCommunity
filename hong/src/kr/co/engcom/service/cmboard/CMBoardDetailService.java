 package kr.co.engcom.service.cmboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.CMBoardDAO;
import kr.co.engcom.dao.CMReplyDAO;
import kr.co.engcom.dto.CMBoardDTO;

public class CMBoardDetailService implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 throws Exception{ 
			   	boolean check = false;
				int num=Integer.parseInt(request.getParameter("num"));//게시판 번호 
				
				CMBoardDTO dto = new CMBoardDTO();
				CMBoardDAO dao = new CMBoardDAO();
				CMReplyDAO rdao = new CMReplyDAO();
				/////여기서 댓글도 같이 이동 				
				
				dto = dao.getDetail(num);
				
				check =	dao.getAnswerCheck(num);//만약에 정답이 있으면 true;
				
				List replyList = rdao.getCMReplyList(num);
			
				if(dto==null){
			   		return null;
			   	}
			   	
			   	request.setAttribute("CMBoardDTO", dto);
			   	request.setAttribute("replyList", replyList);
			   	request.setAttribute("check",check);
			  
			   	ActionForward forward = new ActionForward();
			   	forward.setRedirect(false);
		   		forward.setPath("/detailView.cm");
		   		return forward;

			 }
}

package kr.co.engcom.service.cmboard;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.CMBoardDAO;
import kr.co.engcom.dto.CMBoardDTO;



public class CMBoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
    		//정보 가져오기
			//String id = request.getSession("hi");
		
			HttpSession session = request.getSession();
			
			String msg = "";
			String url = "";
			String id = (String)session.getAttribute("userid");
	   	
			String CMBoardSubject = request.getParameter("CMBoardSubject"); //cm 글 제목 
			String CMBoardAnswer = request.getParameter("CMBoardAnswer");	//cm 글 정답
			String CMImage = request.getParameter("CMImage"); //cm 이미지 
			//세션으로 아이디 가져오기 String id = 
			
			//System.out.println(); 아이디 
			System.out.println(id);
			System.out.println(CMBoardSubject);
			System.out.println(CMBoardAnswer);
			System.out.println(CMImage);//base64로 넘어옴 
		
			CMBoardDAO dao = new CMBoardDAO();
			CMBoardDTO dto = new CMBoardDTO();
			
			dto.setCMBoardSubject(CMBoardSubject);//제목
			dto.setCMBoardAnswer(CMBoardAnswer);//정답
			dto.setCMBoardFile(CMImage);//그림
			dto.setCMBoardId(id);//아이디
			
			try {
				dao.CMBoardInsert(dto);//board 인서트
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
		    ActionForward forward = new ActionForward();
			msg = "글 작성 성공";
	   		url = "list.cm";
	   		request.setAttribute("msg", msg);
	   		request.setAttribute("url", url);
	   		forward.setRedirect(false);
	   		forward.setPath("/WEB-INF/views/redirect.jsp");
	   		
		    
		return forward;
	}

}



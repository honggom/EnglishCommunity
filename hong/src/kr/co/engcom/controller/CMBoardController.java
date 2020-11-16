package kr.co.engcom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.service.cmboard.CMBoardCheckLoginService;
import kr.co.engcom.service.cmboard.CMBoardDeleteService;
import kr.co.engcom.service.cmboard.CMBoardDetailService;
import kr.co.engcom.service.cmboard.CMBoardListService;
import kr.co.engcom.service.cmboard.CMBoardWriteService;
import kr.co.engcom.service.cmboard.CMReplyWriteService;

@WebServlet("*.cm")
public class CMBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CMBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("컨트롤러로 들어옴");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	
    	ActionForward forward=null;
    	
    	System.out.println(url_Command);
    	
    	if(url_Command.equals("/writeView.cm")) { //write view 로그인 했는지 안 했는지 검증해야됨
    		action = new CMBoardCheckLoginService();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
    	}else if(url_Command.equals("/list.cm")) { //list view로 이동하기
    		action = new CMBoardListService();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
    	}else if(url_Command.equals("/write.cm")) { //write
    		action = new CMBoardWriteService();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
    	}else if(url_Command.equals("/listView.cm")) { //write view
    		forward = new ActionForward();
    		forward.setPath("/WEB-INF/views/boardcm/CMBoardListView.jsp");
    		System.out.println("CMBoardListView 실행");
    		
    	}else if(url_Command.equals("/CMBoardDetailService.cm")){
			   action = new CMBoardDetailService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		}else if(url_Command.equals("/detailView.cm")) {
			forward = new ActionForward();
    		forward.setPath("/WEB-INF/views/boardcm/CMBoardDetailView.jsp");
    		System.out.println("detailView 실행");
		}else if(url_Command.equals("/goToWrite.cm")) {
			forward = new ActionForward();
    		forward.setPath("/WEB-INF/views/boardcm/CMBoardWriteView.jsp");
		}else if(url_Command.equals("/replyWrite.cm")) {
			action = new CMReplyWriteService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		}else if(url_Command.equals("/CMBoardDeleteCheck.cm")) {
			forward = new ActionForward();
    		forward.setPath("/WEB-INF/views/boardcm/CMBoardDeleteCheck.jsp");
		}else if(url_Command.equals("/CMBoardDelete.cm")) {
			action = new CMBoardDeleteService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		}
    	
    	
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

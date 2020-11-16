/* 
    파일명: UserFrontController.java
    설명: 최초 화면 및 회원가입, 로그인, 로그아웃 시 요청에 따른 url 매핑 servlet
    작성일: 2020-11-09
    작성자: 백희승
*/

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
import kr.co.engcom.service.LogInService;
import kr.co.engcom.service.LogOutService;
import kr.co.engcom.service.SignUpService;

@WebServlet("*.go")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFrontController() {
        super();
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response, String method) 
    		throws Exception {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_Command = requestURI.substring(contextPath.length());

		System.out.println("requestURI : " + requestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("url_Command : " + url_Command);

		ActionForward forward = null;
		Action action = null;

		if (url_Command.equals("/Main.go")) {			// 메인화면
			forward = new ActionForward();
			forward.setPath("/default.jsp");
			
		}else if (url_Command.equals("/SignUp.go")) {	// 회원가입 완료 시
			action = new SignUpService();
			forward = action.execute(request, response);
			
		}else if (url_Command.equals("/LogIn.go")) {	// 로그인 됐을 때
			action = new LogInService();
			forward = action.execute(request, response);
		
		}else if (url_Command.equals("/LogOut.go")) {	// 로그아웃 시
			action = new LogOutService();
			forward = action.execute(request, response);
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response ,"Get");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response, "Post");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

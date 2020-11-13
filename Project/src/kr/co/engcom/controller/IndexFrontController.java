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
import kr.co.engcom.service.board.BoardAddService;
import kr.co.engcom.service.board.BoardCommentService;
import kr.co.engcom.service.board.BoardDeleteService;
import kr.co.engcom.service.board.BoardDetailService;
import kr.co.engcom.service.board.BoardListIndexService;
import kr.co.engcom.service.board.BoardModifyService;
import kr.co.engcom.service.board.BoardModifyView;
import kr.co.engcom.service.board.BoardReplyService;
import kr.co.engcom.service.board.BoardReplyView;


@WebServlet("/default.jsp")
public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IndexFrontController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}

	private void Process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();

		String Command = RequestURI.substring(ContextPath.length());

		System.out.println("RequestURI : " + RequestURI);
		System.out.println("ContextPath : " + ContextPath);
		System.out.println("Command : " + Command);

		// 변수 2개
		ActionForward forward = null;
		Action action = null;

		if (Command.equals("/default.jsp")) {
			System.out.println("boardlistIndex Display");
			action = new BoardListIndexService(); // 다형성 (BoardListAction 이 action 구현)
			try {
				forward = action.execute(request, response);
				// BoardListActon.java 설정한 (request)정보 :
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		// 전체 설정
		if (forward != null) {
			if (forward.isRedirect()) { // true (isRedirect)
				System.out.println("forward.isRedirect : "
						+ forward.isRedirect());
				// 결론: 그냥 UI를 가지는 페이지를 재요청 하도록
				response.sendRedirect(forward.getPath());
			} else {
				// 결론 : view 단에서 처리할 데이터가 있다
				System.out.println("forward.getPath() : " + forward.getPath());
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}

	}
	
}

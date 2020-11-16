package kr.co.engcom.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.dao.CMReplyDAO;


@WebServlet("/HaveMoreReplyServlet")
public class HaveMoreReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public HaveMoreReplyServlet() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int count = Integer.parseInt(request.getParameter("data"));
    	int CMBoardNumber = Integer.parseInt(request.getParameter("CMBoardNumber"));
    	String check = null;
    	
    	
    	PrintWriter out = response.getWriter();
    	CMReplyDAO dao = new CMReplyDAO();
    	
    	check = dao.havaMoreReplyList(CMBoardNumber, count);
    	
    	if(check != null) {
    		//dto가 null이 아니면 객체가 있는 것이므로 true를 리턴 
    		System.out.println(check);
        	out.print(check);
    	}else {
    		//dto가 null이라면 객체가 없으므로 더 이상 출력할 댓글 리스트가 없다.
    		//따라서 check를 false로 바꿔주고 리턴해줌 
    		System.out.println(check);
    		out.print(check);
    		
    	}
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

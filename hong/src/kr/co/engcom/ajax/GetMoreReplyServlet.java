package kr.co.engcom.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.dao.CMReplyDAO;
import net.sf.json.JSONArray;



@WebServlet("/GetMoreReplyServlet")
public class GetMoreReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetMoreReplyServlet() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	int count = Integer.parseInt(request.getParameter("data"));
    	int CMBoardNumber = Integer.parseInt(request.getParameter("CMBoardNumber"));
    	
    	PrintWriter out = response.getWriter();
    	CMReplyDAO dao = new CMReplyDAO();
    	
    	List dto = null;
    	
    	dto = dao.getMoreCMReplyList(CMBoardNumber, count);
    	
    	
    	
    	JSONArray jsonmember =  JSONArray.fromObject(dto);
    	System.out.println("제이슨객체확인제이슨객체확");
    	System.out.println(jsonmember);
    	System.out.println("제이슨객체확인제이슨객체확");
    	
    	if(jsonmember!=null) {
    		//dto가 null이 아니면 객체가 있는 것이므로 true를 리턴 
    		out.print(jsonmember);
    	}
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

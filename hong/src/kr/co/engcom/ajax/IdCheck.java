package kr.co.engcom.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.dao.UserTableDao;

@WebServlet("/CheckId")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdCheck() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String data = request.getParameter("data").trim();
		
		System.out.println(data);
		PrintWriter out = response.getWriter();
		
		UserTableDao dao = new UserTableDao();
		String responseData = dao.hasId(data);

		System.out.println("비동기 결과 : " + responseData);
		out.print(responseData);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}

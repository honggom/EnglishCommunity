/* 
    파일명: SignUpService.java
    설명: 회원가입 처리 Service
    작성일: 2020-11-09
    작성자: 백희승
*/

package kr.co.engcom.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.UserTableDao;
import kr.co.engcom.dto.UserTable;

public class SignUpService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String birthYYYY = request.getParameter("birthYYYY");
		String birthMM = request.getParameter("birthMM");
		String birthDD = request.getParameter("birthDD");
		String email = request.getParameter("email");

		if (birthMM.length() == 1) {
			birthMM = "0" + birthMM;
		}
		if (birthDD.length() == 1) {
			birthDD = "0" + birthDD;
		}

		// UserTable과 동일한 데이터 타입의 java.sql.Date 타입으로 바꿔주기
		Date birth = Date.valueOf(birthYYYY + "-" + birthMM + "-" + birthDD);

		UserTable user = new UserTable();
		user.setId(id);
		user.setPwd(pwd);
		user.setBirth(birth);
		user.setEmail(email);
		user.setUsercode(2);

		UserTableDao dao = new UserTableDao();

		int result = dao.insertAccount(user);

		String msg = "";

		if (result > 0) {
			msg = "회원가입이 완료되었습니다!";
		} else {
			msg = "Creating Account Failed";
		}
		String url = "Main.go";

		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return forward;
	}
}

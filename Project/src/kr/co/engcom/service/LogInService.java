package kr.co.engcom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.UserTableDao;
import kr.co.engcom.dto.UserTable;

public class LogInService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("sign_id");
		String pwd = request.getParameter("sign_pwd");

		UserTableDao dao = new UserTableDao();
		UserTable dto = dao.isExist(id);

		String msg = "";
		String url = "";

		if (dto != null) {

			if (dto.getId().equals(id)) {

				if (dto.getPwd().equals(pwd)) {
					HttpSession session = request.getSession();

					if (dto.getUsercode() == 1) { 	// 관리자
						session.setAttribute("userid", "admin");
						msg = "관리자 로그인 성공";
					} else { 						// 회원
						session.setAttribute("userid", id);
						msg = id + "님 로그인 되셨습니다.";
					}

				} else {
					msg = "아이디 또는 비밀번호가 틀립니다.";
				}

			} else {
				msg = "아이디 또는 비밀번호가 틀립니다.";
			}
		} else {
			msg = "등록된 회원이 아닙니다. 회원가입을 해주세요.";
		}

		url = "Main.go";
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");

		return forward;
	}
}

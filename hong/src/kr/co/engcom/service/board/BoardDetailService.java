package kr.co.engcom.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.BoardDto;
import kr.co.engcom.dto.CommentTableDto;

public class BoardDetailService implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDao boarddao = new BoardDao();
		BoardDto boarddata = new BoardDto();
		
		// 보여질 댓글 리스트

		// 해당 글 번호 num : 보여질 글과 그 글의 댓글'들'
		int num = Integer.parseInt(request.getParameter("num"));
		// boarddao.setReadCountUpdate(num);
		boarddata = boarddao.getDetail(num);

		if (boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}else {
			System.out.println("상세보기 성공");
		}

		// ----------Comment(댓글) 페이징----------
		
		String ps = request.getParameter("ps");		// 클라이언트로부터
		String cp = request.getParameter("cp");		// 보고싶은 글 수와 현재 페이지 번호를 받아옴
		
		if(ps == null || ps.trim().equals("")) {	// page size 값이 없는 경우 기본 페이지 크기 지정
			ps = "5";
		}
		if(cp == null || cp.trim().equals("")) {	// current page 값이 없는 경우 현재 페이지 지정
			cp = "1";
		}

		int pagesize = Integer.parseInt(ps);		// 최초 pagesize = 5
		int currpage = Integer.parseInt(cp);		// 최초 currpage = 1
		
		int totalcount = boarddao.getCommentsCount(num);	// 해당 글 번호의 댓글 개수
		int pagecount = 0;							
		
		if (totalcount % pagesize == 0) {			// 페이징 공식 : 5개씩만 뽑겠다.
			pagecount = totalcount / pagesize;
		} else {
			pagecount = (totalcount / pagesize) + 1;
		}
		
		List<CommentTableDto> pagedCommentList = boarddao.getPagedComments(currpage, pagesize, num);
		
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("commentList", pagedCommentList);
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("currpage", currpage);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./WEB-INF/views/board/BoardView.jsp");
		return forward;
	}
}
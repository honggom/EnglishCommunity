package kr.co.engcom.service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.dao.BoardDao;
import kr.co.engcom.dto.BoardDto;

public class BoardModifyService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
		boolean result = false;
		boolean userCheck = false;
		
		String realFolder="";
   		String saveFolder="boardupload";
   		int fileSize=5*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
		MultipartRequest multi=null;
		
		multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"utf-8",
				new DefaultFileRenamePolicy());

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		int num = Integer.parseInt(multi.getParameter("contentNumber"));
		
		BoardDao boarddao = new BoardDao();
		BoardDto boarddata = new BoardDto();
		
		userCheck = boarddao.isBoardWriter(num, multi.getParameter("id"));	// 패스워드가 없으니 ID를 받아 일치하면!
		
		if(userCheck == false){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('수정할수 없습니다');");
			out.print("location.href='BoardList.bo';");
			out.print("</script>");
			out.close();
			
			return null;
			
		}
		

        //실수정 (글제목 , 글내용) => BoardModify.jsp
		boarddata.setId(id);
		boarddata.setContentNumber(num);
		boarddata.setContentTitle(multi.getParameter("contentTitle"));
		boarddata.setContent(multi.getParameter("content"));
		boarddata.setFilename(
				multi.getFilesystemName(
						(String)multi.getFileNames().nextElement()));
		
		result = boarddao.boardModify(boarddata);
		
		if(result == false){
			System.out.println("게시판 수정 실패");
			return null;
		}
		System.out.println("게시판 수정 성공");
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("BoardDetailService.bo?num="+num);
		
		return forward;
	}
}

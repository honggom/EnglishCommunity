package kr.co.engcom.service.board;

import java.io.IOException;
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

public class BoardAddService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		
		BoardDao boarddao=new BoardDao();
	   	BoardDto boarddata=new BoardDto();
	   	ActionForward forward=new ActionForward();
	   	
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
	   	
		String realFolder="";
   		String saveFolder="boardupload";
   		
   		int fileSize=5*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
   		System.out.println("realFolder: " + realFolder);
   		boolean result=false;
   		
   		response.setContentType("text/html;charset=utf-8");
   		PrintWriter out=response.getWriter();
   		
		MultipartRequest multi=null;
		
		multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"utf-8",
				new DefaultFileRenamePolicy());
		
		
		boarddata.setId(multi.getParameter("id"));
		System.out.println("boarddata: " + boarddata.getId());
		boarddata.setBoardName(multi.getParameter("boardName"));
   		boarddata.setContentTitle(multi.getParameter("contentTitle"));
   		boarddata.setContent(multi.getParameter("content"));
   		boarddata.setFilename(
   				multi.getFilesystemName(
   						(String)multi.getFileNames().nextElement()));
   		
   		result=boarddao.boardInsert(boarddata, id);
   		
   		if(result==false){
   			System.out.println("게시판 등록 실패");
   			return null;
   		}
   		System.out.println("게시판 등록 완료");
   		
   		forward.setRedirect(false);
   		forward.setPath("BoardList.bo");
   		return forward;
	   		
	}
}

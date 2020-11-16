package kr.co.engcom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.engcom.dto.BoardDto;
import kr.co.engcom.dto.CommentTableDto;
import kr.co.engcom.utils.DB_Close;

public class BoardDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB 연결 실패: " + e.getMessage());
			return;
		}
	}
	
	// SELECT count(*) FROM board WHERE boardname = 'Grammar';
	// 글의 개수 구하기
	// ListBoard.jsp
	public int getListCount() {
		// select count(*) from board
		int rowcount = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowcount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return rowcount;
	}
	public int getGrammarListCount() {
		// select count(*) from board
		int rowcount = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board WHERE boardname = 'Grammar'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowcount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return rowcount;
	}
	public int getReadingListCount() {
		// select count(*) from board
		int rowcount = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board WHERE boardname = 'Reading'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowcount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return rowcount;
	}
	public int getListeningListCount() {
		// select count(*) from board
		int rowcount = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board WHERE boardname = 'Listening'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowcount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return rowcount;
	}
	
	// 글 목록 보기
	// ListBoard.jsp
	public List<BoardDto> getBoardList(int page, int limit) {

		
		String board_list_sql = "select * from "
				+ "(select rownum rnum, contentNumber, id, contentTitle, "
				+ "content, filename, filesize, refer, depth, step, "
				+ "boardCode, boardName, "
				+ "TO_CHAR(ReportingDate, 'YYYY/MM/DD') AS ReportingDate from "
				+ "(select * from board order by Refer desc, Step asc)) "
				+ "where rnum>=? and rnum<=?";
		
		
		List<BoardDto> boardlist = new ArrayList<>();
		
		int startrow = (page - 1) * 10 + 1;	// 읽기 시작할 row 번호.
		int endrow = startrow + limit -1;	// 읽을 마지막 row 번호.	
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto board = new BoardDto(); 	
				board.setContentNumber(rs.getInt(2));		//글번호
				board.setId(rs.getString(3));				//아이디
				board.setContentTitle(rs.getString(4));		//글제목
				board.setContent(rs.getString(5));			//글내용
				board.setFilename(rs.getString(6));			//첨부파일명
				board.setFilesize(rs.getInt(7));			//첨부파일사이즈
				board.setRefer(rs.getInt(8));					//원글번호
				board.setDepth(rs.getInt(9));					//들여쓰기
				board.setStep(rs.getInt(10));					//답글정렬
				board.setBoardCode(rs.getInt(11));			//게시판 코드	
				board.setBoardName(rs.getString(12));			//게시판 이름
				board.setReportingDate(rs.getString(13));	//작성일
				
				boardlist.add(board);
			}
			
			
		}catch(Exception e) {
			System.out.println("getBoardList 에러 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return boardlist;
	}
	
	// 글 목록 보기(index.jsp에 게시판데이터 불러오기)
	// ListBoard.jsp
	public List<BoardDto> getIndexBoardList(int page, int limit) {

		
		String board_list_sql = "select * from "
				+ "(select rownum rnum, contentNumber, id, contentTitle, "
				+ "content, filename, filesize, refer, depth, step, "
				+ "boardCode, boardName, "
				+ "TO_CHAR(ReportingDate, 'YYYY/MM/DD') AS ReportingDate from "
				+ "(select * from board order by Refer desc, Step asc)) "
				+ "where rnum>=? and rnum<=?";
		
		
		List<BoardDto> boardlist = new ArrayList<>();
		
		int startrow = (page - 1) * 10 + 1;	// 읽기 시작할 row 번호.
		int endrow = startrow + limit -1;	// 읽을 마지막 row 번호.	
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto board = new BoardDto(); 	
				board.setContentNumber(rs.getInt(2));		//글번호
				board.setId(rs.getString(3));				//아이디
				board.setContentTitle(rs.getString(4));		//글제목
				board.setContent(rs.getString(5));			//글내용
				board.setFilename(rs.getString(6));			//첨부파일명
				board.setFilesize(rs.getInt(7));			//첨부파일사이즈
				board.setRefer(rs.getInt(8));					//원글번호
				board.setDepth(rs.getInt(9));					//들여쓰기
				board.setStep(rs.getInt(10));					//답글정렬
				board.setBoardCode(rs.getInt(11));			//게시판 코드	
				board.setBoardName(rs.getString(12));			//게시판 이름
				board.setReportingDate(rs.getString(13));	//작성일
				
				boardlist.add(board);
			}
			
			
		}catch(Exception e) {
			System.out.println("getBoardList 에러 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return boardlist;
	}

	
	// 글 내용 보기
	// ViewBoard.jsp
	public BoardDto getDetail(int num) throws Exception{
		
		BoardDto board = null;
		String sql = "";
		try {
			String board_list_sql = "select * from "
					+ "(select rownum rnum, contentNumber, id, contentTitle, "
					+ "content, filename, filesize, refer, depth, step, "
					+ "boardCode, boardName, "
					+ "TO_CHAR(ReportingDate, 'YYYY/MM/DD') AS ReportingDate from "
					+ "(select * from board order by Refer desc, Step asc)) "
					+ "where contentNumber=?";
			
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDto();
				board.setContentNumber(num);		
				board.setId(rs.getString(3));						
				board.setContentTitle(rs.getString(4)); 	
				board.setContent(rs.getString(5));	
				board.setFilename(rs.getString(6));
				board.setFilesize(rs.getInt(7));
				board.setRefer(rs.getInt(8));						
				board.setDepth(rs.getInt(9));						
				board.setStep(rs.getInt(10));						
				board.setBoardCode(rs.getInt(11));				
				board.setBoardName(rs.getString(12));			
				board.setReportingDate(rs.getString(13));	
			}
		}catch(Exception e) {
			System.out.println("getDetail 에러 : " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return board;
	}
	
	
	// 글 등록
	// WriteBoard.jsp
	public boolean boardInsert(BoardDto board, String userid) {
		System.out.println("userid: " + userid);
		int num = 0;
		String sql = "";
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(ContentNumber) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;
			
			System.out.println("BoardDao/boardInsert/num: " + num);
//			if(board.getNotice() == null) {	
//				board.setNotice("N");
//			}
			
			
			if(board.getBoardCode() == 1) {			// 1�� = Grammer 				board.setBoardName("Grammer");
				board.setBoardCode(1);
				board.setBoardName("Grammer");
			}else if (board.getBoardCode() == 2) {	// 2�� = Reading
				board.setBoardCode(2);
				board.setBoardName("Reading");
			}else if(board.getBoardCode() == 3) {	// 3�� = Listening
				board.setBoardCode(3);
				board.setBoardName("Listening");
			}
			
			sql = "insert into board (ContentNumber, Id, ContentTitle,"
					+ "Content, File, Refer, Depth, Step, "
					+ "ReadCount,"
					+ "notice, ReportingDate"
					+ "values(?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			String sql2 = "insert " + 
					//"into replies(boardcode, boardname) values(?,?)" +
					"into board(ContentNumber, ContentTitle, Content, ReportingDate, " + 
					"Filename, filesize, Depth, Step, Refer, BoardCode, boardName, Id) " + 
					"values(?,?,?,sysdate, "+
					"?,?,?,?,?,?,?,?)";
					//"select * from dual";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);		
			pstmt.setString(2, board.getContentTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			pstmt.setInt(5, board.getFilesize());
			pstmt.setInt(6, 0);								
			pstmt.setInt(7, 0);			
			pstmt.setInt(8, num);		
			pstmt.setInt(9, board.getBoardCode());
			pstmt.setString(10, board.getBoardName());
			pstmt.setString(11, userid);
			System.out.println("getId: "+board.getId());
			System.out.println("getContentTitle: "+board.getContentTitle());
			System.out.println("getContent: "+board.getContent());
			System.out.println("getFilename: "+board.getFilename());
			
			result = pstmt.executeUpdate();
			System.out.println("result: " + result);
			if(result == 0) {
				return false;
			}
			return true;
			
		}catch(Exception e) {
			System.out.println("boardInsert 에러 : " + e.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return false;
	}




	// 글 답변
	// ReplyBoard.jsp
	public int boardReply(BoardDto board) {
		// 1. update
		// 2. insert		
		String board_max_sql = "select max(ContentNumber) from board";
		String sql = "";
		int num = 0;
		int result = 0;
		
		int Refer = board.getRefer();
		int Depth = board.getDepth();
		int Step = board.getStep();
				
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}else {
				num = 1;
			}
			System.out.println("BoardDao/boardReply/num: " + num);
			
			//update
			sql = "UPDATE board SET Step = Step + 1"
				+ "WHERE Refer = ? AND Step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Refer);
			pstmt.setInt(2, Step);
			
			result = pstmt.executeUpdate();
			// insert board
			Step = Step + 1;	
			Depth = Depth + 1;
			
			String sql2 = "insert " + 
					"into board(ContentNumber, ContentTitle, Content, ReportingDate, " + 
					"Filename, Filesize, Depth, Step, Refer, BoardCode, boardName, Id)" + 
					"values(?,?,?,sysdate,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);		
			pstmt.setString(2, board.getContentTitle());			
			pstmt.setString(3, board.getContent());				
			pstmt.setString(4, board.getFilename());	
			pstmt.setInt(5, board.getFilesize());	
			pstmt.setInt(6, Depth);								
			pstmt.setInt(7, Step);			
			pstmt.setInt(8, Refer);		
			pstmt.setInt(9, board.getBoardCode());
			pstmt.setString(10, board.getBoardName());
			pstmt.setString(11, board.getId());		
			
			System.out.println("board.getId(): " + board.getId());
			System.out.println("check1");
			pstmt.executeUpdate();
			System.out.println("check2");
			
		}catch(SQLException s) {
			System.out.println("boardReply 에러 : " + s.getMessage());
		}finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return num;
				
	}
	
	
	// 글 수정
	// ModifyBoard.jsp
	public boolean boardModify(BoardDto board) {
		String sql = "update board set contentTitle=?, content=?, filename=? where contentNumber=?";
		
		try {	
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getContentTitle());	
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setInt(4, board.getContentNumber());
			
			pstmt.executeUpdate();
			
			return true;
		}
			
			catch (Exception e) {
				System.out.println("boardModify 에러 : " + e.getMessage());
		} finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
			return false;
				
	}
	
	
	// 글 삭제
	// DeleteBoard.jsp 
	public boolean boardDelete(int num, String userid, boolean userReplyCheck) throws SQLException {	

		//PreparedStatement pstmt2 = null;
		//제약조건(ORA-02292: integrity constraint (DBUSER.FK_BOARD_TO_REPLIES) violated - child record found)
		//특정 글번호의 comment(댓글)을 모두 삭제한 후에야 특정 글번호의 글(board)를 삭제할 수 있습니다
		String commentTable_delete_sql = 
				"delete from commentTable where ContentNumber=?";
		String board_delete_sql = 
				"delete from board where ContentNumber=?";

		int result = 0;

		try {
			
			conn = ds.getConnection();

			//해당 글번호에 대한 댓글이 있으면 댓글부터 삭제하고,게시글을 그 다음에 삭제합니다
			if(userReplyCheck == true) {
				pstmt = conn.prepareStatement(commentTable_delete_sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
				if(result == 0) return false;
			}
			
			//해당 글번호에 대한 댓글이 없으므로 게시글만 삭제합니다.
			pstmt = conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			System.out.println("result: " + result);
			if(result == 0) return false;
			
			return true;
			
			/*
			//트랜잭션
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(replies_delete_sql);
			pstmt.setInt(1, num);
			System.out.println("BoardDao/boardDelete/num: " + num);
			result = pstmt.executeUpdate();
			System.out.println("BoardDao/boardDelete/result: "+result);
			
			pstmt2 = conn.prepareStatement(board_delete_sql);
			pstmt2.setInt(1, num);
			pstmt2.executeQuery();
			conn.commit();
			return true;
			/////////
			*/
		} catch (Exception e) {
			System.out.println("boardDelete 에러 : " + e.getMessage());
			//트랜잭션
			//conn.rollback();
			/////////
		} finally {
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return false;
	}
	
	
	// 조회수 업데이트
	// ViewBoard.jsp
	public void setReadCountUpdate(int num) throws Exception {
			String sql = "update board set ReadCount = "
				+ "ReadCount + 1 where ContentNumber = " + num;
			try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			System.out.println("setReadCountUpdate 에러 : " + s);
		} finally {
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
	}
	
	//글쓴이인지 확인.
	public boolean isBoardWriter(int num,String id){
		System.out.println("BoardDao/isBoardWriter/id: "+id);
		String board_sql=
			"select * from board where contentnumber=?";
		
		try{
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(id);
				System.out.println(rs.getString("id"));
				if(id != null) {
					if(id.equals(rs.getString("id")) || id.equalsIgnoreCase("admin")){
						return true;
					}
				}
			}

		}catch(SQLException ex){
			System.out.println("isBoardWriter 에러 : "+ex);
		}
		finally{
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return false;
	}
	
	//댓글의 글쓴이인지 확인.
	public boolean isRepliesWriter(int num,String id){
		System.out.println("BoardDao/isRepliesWriter/id: "+id);
		String reply_sql=
			"select * from commentTable where contentnumber=?";
		
		try{
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(reply_sql);
			pstmt.setInt(1, num);
			System.out.println("check1");
			rs=pstmt.executeQuery();
			System.out.println("check2");
			if(rs.next()) {
				System.out.println(id);
				System.out.println(rs.getString("id"));
				if(id != null) {
					if(id.equals(rs.getString("id"))){
						return true;
					}
				}
			}

		}catch(SQLException ex){
			System.out.println("isRepliesWriter 에러 : "+ex);
		}
		finally{
			DB_Close.close(pstmt);
			DB_Close.close(conn);
		}
		return false;
	}

	// 댓글 작성하기
	   // 백희승
	   public int insertComment(CommentTableDto dto) {
	      int row = 0;

	      try {
	         conn = ds.getConnection();

	         String query = "insert into commentTable"
	        		 + "(commentNumber, commentContent, contentNumber, id, redate)"
	        		 + " values(COMMENTNUMBER.nextval, ?, ?, ?, ?)";
	         pstmt = conn.prepareStatement(query);

	         pstmt.setString(1, dto.getCommentContent());
	         pstmt.setInt(2, dto.getContentNumber());
	         pstmt.setString(3, dto.getId());
	         pstmt.setDate(4, dto.getRedate());

	         row = pstmt.executeUpdate();
	      } catch (Exception e) {

	         e.printStackTrace();
	         System.out.println("댓글 작성 INSERT 예외 발생 : " + e.getMessage());
	      } finally {
	         DB_Close.close(pstmt);
	         DB_Close.close(conn);
	      }

	      return row;
	   }
	
	   // 해당 글 번호의 댓글 개수 카운트
		// 백희승
		public int getCommentsCount(int num) {
			int row = 0;

			try {
				conn = ds.getConnection();

				String query = "SELECT CONTENTNUMBER, COMMENTNUMBER, COMMENTCONTENT, ID, REDATE    "
							 + "FROM COMMENTTABLE    " 
						 	 + "WHERE CONTENTNUMBER = ?";
							// + "order by commentnumber desc";
				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, num);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					row += 1;
				}

			} catch (Exception e) {
				System.out.println("댓글 목록 가져오기 중 예외 발생 : " + e.getMessage());
				e.printStackTrace();
			} finally {
				DB_Close.close(rs);
				DB_Close.close(pstmt);
				DB_Close.close(conn);
			}

			return row;
		}

		// 페이징 된 댓글
		// 백희승
		public List<CommentTableDto> getPagedComments(int currpage, int pagesize, int num) {
			List<CommentTableDto> commentList = null;

			String query = "SELECT * FROM"
						 + "    (SELECT ROWNUM AS RN,COMMENTNUMBER, COMMENTCONTENT, CONTENTNUMBER, ID, REDATE FROM"
						 + "        (SELECT COMMENTNUMBER, COMMENTCONTENT, CONTENTNUMBER, ID, REDATE"
						 + "        FROM COMMENTTABLE)) " 
						 + " WHERE RN >= ? and RN <= ? CONTENTNUMBER = ?";
			
			String query2 = "SELECT RN, COMMENTNUMBER, COMMENTCONTENT, CONTENTNUMBER, ID, REDATE FROM " + 
					"(select ROWNUM AS RN, COMMENTNUMBER, COMMENTCONTENT, CONTENTNUMBER, ID, REDATE FROM COMMENTTABLE " + 
					"WHERE CONTENTNUMBER = ? ORDER BY COMMENTNUMBER) " + 
					"WHERE RN >= ? AND RN <= ?";

			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(query2);
				
				int to_N = currpage * pagesize;
				int from_N = currpage * pagesize - (pagesize - 1);
				
				pstmt.setInt(1, num);
				pstmt.setInt(2, from_N);// 글 번호에 대한 댓글만
				pstmt.setInt(3, to_N);	// from_N번부터 to_N번까지 
				
				
				rs = pstmt.executeQuery();
				commentList = new ArrayList<CommentTableDto>();
				
				while(rs.next()) {
					CommentTableDto cmt = new CommentTableDto();
					
					cmt.setCommentNumber(rs.getInt(2));
					cmt.setCommentContent(rs.getString(3));
					cmt.setContentNumber(rs.getInt(4));
					cmt.setId(rs.getString(5));
					cmt.setRedate(rs.getDate(6));
					
					commentList.add(cmt);
				}
			} catch (Exception e) {
				System.out.println("getPagedComments 중 예외 : " + e.getMessage());
				e.printStackTrace();
			} finally {
				DB_Close.close(rs);
				DB_Close.close(pstmt);
				DB_Close.close(conn);
			}

			return commentList;
		}



}

package kr.co.engcom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.engcom.dto.CMBoardDTO;
import kr.co.engcom.utils.ConnectionHelper;
import kr.co.engcom.utils.DB_Close;

public class CMBoardDAO {

	public boolean CMBoardInsert(CMBoardDTO board) throws SQLException {// CM 게시판 데이터 insert

		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0;
		String sql = "";
		int result = 0;

		try {

			pstmt = conn.prepareStatement("select max(CMBoardNumber) from CMBoard"); // CM보드 마지막 작성 번호를 가져옴
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;// 게시글이 한개라도 있으면 그 게시글의 번호의 +1을 해줌
			else
				num = 1; // 없으면 1

			sql = "insert into CMBoard (CMBoardNumber, CMBoardId, CMBoardSubject,";
			sql += "CMBoardFile, CMBoardAnswer, CMBoardDate) values(?,?,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getCMBoardId());
			pstmt.setString(3, board.getCMBoardSubject());
			pstmt.setString(4, board.getCMBoardFile());
			pstmt.setString(5, board.getCMBoardAnswer());

			result = pstmt.executeUpdate();
			if (result == 0) {
				return false;
			}

			return true;

		} catch (Exception ex) {
			System.out.println("boardInsert 에러 : " + ex);
		} finally {
			DB_Close.close(rs);
			DB_Close.close(pstmt);
			conn.close(); // 반환하기
		}
		return false;
	}

	public int getCMBoardListCount() {
		
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;

		try {
			pstmt = conn.prepareStatement("select count(*) from CMBoard");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			if (rs != null)
				DB_Close.close(rs);
			if (pstmt != null)
				DB_Close.close(pstmt);
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	public List getCMBoardList(int page, int limit) {
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String board_list_sql = "SELECT * FROM (" + 
				"        SELECT a.*, ROWNUM as rnum FROM (" + 
				"               SELECT CMBoardNumber, CMBoardSubject, CMBoardDate, CMBoardId FROM CMBoard ORDER BY CMBoardNumber desc" + 
				"               )" + 
				"        a)  WHERE rnum >= ? and rnum <=?";

		List list = new ArrayList();
		
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호.
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호.
		try {
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CMBoardDTO dto = new CMBoardDTO();
				dto.setCMBoardNumber(rs.getInt("CMBoardNumber"));
				dto.setCMBoardSubject(rs.getString("CMBoardSubject"));
				dto.setCMBoardDate(rs.getDate("CMBoardDate"));
				dto.setCMBoardId(rs.getString("CMBoardId"));
				list.add(dto);
				
				System.out.println(list);
			}

			return list; //리스트에 CM보드 정보가 10개씩 담김 물론 총 게시물이 10개가 안 되면 그 미만으로 담김 
		} catch (Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		} finally {
			if (rs != null)
				DB_Close.close(rs);
			if (pstmt != null)
				DB_Close.close(pstmt);
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	public CMBoardDTO getDetail(int num) throws Exception{
		CMBoardDTO board = null;
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement(
					"select * from CMBoard where CMBoardNumber = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				board = new CMBoardDTO();
				
				board.setCMBoardFile(rs.getString("CMBoardFile"));//파일
				board.setCMBoardDate(rs.getDate("CMBoardDate"));//날짜
				board.setCMBoardNumber(rs.getInt("CMBoardNumber"));//보드 넘버
				board.setCMBoardSubject(rs.getString("CMBoardSubject"));//보드제목
				board.setCMBoardId(rs.getString("CMBoardId"));//작성자 아이디
			}
			return board;
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			if(rs!=null)
				DB_Close.close(rs);
			if(pstmt !=null)
				DB_Close.close(pstmt);
			if(conn !=null)try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}

	public boolean getAnswerCheck(int num) throws Exception{
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		
		try{
			pstmt = conn.prepareStatement("select * from CMReply where CMReplyContent "
							+ "= (select cmboardanswer from cmboard where cmboardnumber = ?) AND cmboardnumber = ?");
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			
			result = pstmt.executeUpdate();
			
			if(result > 0){
				//정답이 하나라도 있으면 true 리턴 
				check = true;
				return check;
			}
			
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			if(pstmt !=null)
				DB_Close.close(pstmt);
			if(conn !=null)try{conn.close();}catch(SQLException ex){}
		}
		//정답이 없으면 false 리턴 
		return check;
	}
}

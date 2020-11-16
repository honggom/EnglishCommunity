package kr.co.engcom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.engcom.dto.CMReplyDTO;
import kr.co.engcom.utils.ConnectionHelper;
import kr.co.engcom.utils.DB_Close;

public class CMReplyDAO {

	public boolean CMReplyInsert(CMReplyDTO board) throws SQLException {// CM 게시판 데이터 insert

		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;

		String sql = "";
		int result = 0;

		try {

			sql = "insert into CMReply (CMBoardNumber, CMReplyContent,";
			sql += "CMReplyId ,CMReplyNumber, CMReplyDate) values(?,?,?,CMReply_IDX.nextval,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCMBoardNumber());
			pstmt.setString(2, board.getCMReplyContent());
			pstmt.setString(3, board.getCMReplyId());
			result = pstmt.executeUpdate();
			
			if (result == 0) {
				return false;
			}
			return true;

		} catch (Exception ex) {
			System.out.println("boardInsert 에러 : " + ex);
		} finally {
			DB_Close.close(pstmt);
			conn.close(); // 반환하기
		}
		return false;
	}
	
	
	public List getCMReplyList(int num) {
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String board_list_sql = "select CMReplyNumber, CMBoardNumber, CMReplyDate, CMReplyContent, CMReplyId  from cmreply where cmboardnumber = ? order by cmreplynumber desc";

		List list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CMReplyDTO dto = new CMReplyDTO();
				dto.setCMReplyNumber(rs.getInt("CMReplyNumber"));
				dto.setCMBoardNumber(rs.getInt("CMBoardNumber"));
				dto.setCMReplyDate(rs.getDate("CMReplyDate"));
				dto.setCMReplyContent(rs.getString("CMReplyContent"));
				dto.setCMReplyId(rs.getString("CMReplyId"));
				list.add(dto);
				
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
	
	
	public int CMReplyAndBoardDelete(int num) throws SQLException{

		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String del_reply_sql="delete from CMReply where CMBoardNumber=?";
			String del_board_sql="delete from CMBoard where CMBoardNumber=?";
						
						conn.setAutoCommit(false);

						pstmt = conn.prepareStatement(del_reply_sql);
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						
						pstmt = conn.prepareStatement(del_board_sql);
						pstmt.setInt(1, num);
						int row = pstmt.executeUpdate();
						
						if(row > 0){
							conn.commit(); //정상처리
						}else{
							conn.rollback();
						}
						
					return row;
			
		}finally{
			if(pstmt !=null)DB_Close.close(pstmt);
	    	if(rs !=null)DB_Close.close(rs);
	    	if(conn !=null)conn.close();
		}
	}

	public List getMoreCMReplyList(int CMBoardNumber, int num) {

		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String board_list_sql = "SELECT CMReplyNumber, CMBoardNumber, TO_CHAR(CMReplyDate, 'YYYY-MM-DD') as CMReplyDate, CMReplyContent, CMReplyId FROM (" + 
				"        SELECT a.*, ROWNUM as rnum FROM (" + 
				"               SELECT * FROM CMReply where cmboardnumber = ? ORDER BY CMreplyNumber desc" + 
				"               )" + 
				"        a)  WHERE rnum >= ? and rnum <=?";

		List list = new ArrayList();
		
		try {
			int startNum = num-14;
			//num = 30 - 14 = 16 16~30 
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, CMBoardNumber);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, num);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CMReplyDTO dto = new CMReplyDTO();
				dto.setCMReplyNumber(rs.getInt("CMReplyNumber"));
				dto.setCMBoardNumber(rs.getInt("CMBoardNumber"));
				dto.setCMReplyStringDate(rs.getString("CMReplyDate"));
				dto.setCMReplyContent(rs.getString("CMReplyContent"));
				dto.setCMReplyId(rs.getString("CMReplyId"));
				list.add(dto);
				
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
	
	public String havaMoreReplyList(int CMBoardNumber, int num) {
		Connection conn = ConnectionHelper.getConnection("oracle"); // 객체 얻기
		PreparedStatement pstmt = null;
		int row = 0;
		String check = "";
		
		String board_list_sql = "SELECT CMReplyNumber, CMBoardNumber, CMReplyDate, CMReplyContent, CMReplyId FROM (" + 
				"        SELECT a.*, ROWNUM as rnum FROM (" + 
				"               SELECT * FROM CMReply where cmboardnumber = ? ORDER BY CMreplyNumber desc" + 
				"               )" + 
				"        a)  WHERE rnum > ?";

		List list = new ArrayList();
		
		try {
			//num = 30 - 14 = 16 16~30 
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, CMBoardNumber);
			pstmt.setInt(2, num);
			
			row = pstmt.executeUpdate();

			if(row > 0) {
				check = "true";
				//담긴 로우가 하나라도 있으면 객체가 있으므로 트루로 리턴 
				
			}else {
				check = "false";
				//담긴 로우가 없으면 객체가 없으니 폴스로 리턴 
			}

			return check; //리스트에 CM보드 정보가 10개씩 담김 물론 총 게시물이 10개가 안 되면 그 미만으로 담김 
		} catch (Exception ex) {
			System.out.println("havaMoreReplyList 에러 : " + ex);
		} finally {
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
}

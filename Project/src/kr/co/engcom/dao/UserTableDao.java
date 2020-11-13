package kr.co.engcom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.engcom.dto.UserTable;

public class UserTableDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static DataSource ds = null;

	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Context envctx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserDAO JNDI 오류 : " + e.getMessage());
		}
	}
	
	public UserTable isExist(String id) {
		UserTable dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();

			String query = "select id, pwd, usercode from usertable where id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new UserTable();
				dto.setId(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setUsercode(rs.getInt(3));
			}

		} catch (Exception e) {
			System.out.println("데이터 전체 조회 중 문제 발생 : " + e.getMessage());
		} finally {
			closeDB(rs, pstmt, conn);
		}
		return dto;
	}
	
	// 회원가입 시 
	public int insertAccount(UserTable dto) {
		int row = 0;

		try {
			conn = ds.getConnection();
			
			String query = "insert into usertable(id, pwd, birth, email, usercode) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setDate(3, dto.getBirth());
			pstmt.setString(4, dto.getEmail());
			pstmt.setInt(5, dto.getUsercode());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println("INSERT 작업 도중 SQLException 발생 : " + e.getMessage());
		} finally {
			closeDB(pstmt, conn);
		}
		return row;
	}
	
	// id 중복 유무 확인하는 함수
	public String hasId(String id) {
		
		String idCheck = "";
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();

			String query = "select id from usertable where id=? and usercode=2";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idCheck = "true";
			}else {
				idCheck = "false";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert : " + e.getMessage());
		} finally {
			closeDB(rs, pstmt, conn);
		}
		
		return idCheck;
	}
	
	// ------------------------- Connection 및 자원 반환 시 closeDB() -------------------------
	private void closeDB(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("resultSet close error : " + e.getMessage());
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println("preparedStatement close error : " + e.getMessage());
			}
		}

		if (conn != null) {
			try {
				conn.close();
				System.out.println(conn.isClosed() ? "Connection 반환 됨" : "Connection 반환 안 됨");
			} catch (Exception e) {
				System.out.println("Connection close error : " + e.getMessage());
			}
		}
	}

	private void closeDB(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println("preparedStatement close error : " + e.getMessage());
			}
		}

		if (conn != null) {
			try {
				conn.close();
				System.out.println(conn.isClosed() ? "Connection 반환 됨" : "Connection 반환 안 됨");
			} catch (Exception e) {
				System.out.println("Connection close error : " + e.getMessage());
			}
		}
	}
	
}

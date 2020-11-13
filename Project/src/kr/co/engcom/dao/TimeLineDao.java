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

import kr.co.engcom.dto.TimeLine;

public class TimeLineDao {

	private static DataSource ds;

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 정적 초기자 : 클래스 body의 어느 곳에서도 올 수 있으며, 해당 클래스 호출 시 단 1번 실행된다.
	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext(); // DBCP를 이용하기 위해선 JNDI 서비스
			Context envctx = (Context) ctx.lookup("java:comp/env"); // DNS 와 같은 맥락으로,
			ds = (DataSource) envctx.lookup("/jdbc/oracle"); // DB이름검색을 통해 해당 DB의 자원을 이용할 수 있다.
		} catch (Exception e) {
			System.out.println("look up fail :" + e.getMessage());
		}

	}

	public int getTimeLineListCount() {
		String sql = "select count(*) from TimeLine";
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("count: " + e.getMessage());
		} finally {
			closeDB(rs, pstmt, con);
		}
		return count;
	}
	public List<TimeLine> getTimeLineList(int currpage, int pagesize) {
		List<TimeLine> timelinelist = null;
		
		String sql = "select no, content_eng, content_kor, timelinenumber "
				+ "from "
				+ "(select rownum as no, content_eng, content_kor, timelinenumber "
				+ "from timeline"
				+ " where rownum <=?"
				+ ")where no>=? order by timelinenumber" ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
						
			int from_N = currpage * pagesize - (pagesize - 1);
			int to_N = currpage * pagesize;
			
			pstmt.setInt(1, to_N);		// N까지
			pstmt.setInt(2, from_N);	// N부터
			
			rs = pstmt.executeQuery();
			timelinelist = new ArrayList<TimeLine>();
			
			while(rs.next()) {
				TimeLine timeline = new TimeLine();
				timeline.setContent_eng(rs.getString("content_eng"));
				timeline.setContent_kor(rs.getString("content_kor"));
				timeline.setTimeLineNumber(rs.getInt("timelinenumber"));
				
				timelinelist.add(timeline);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("<EmpDAO> getPagedEmpList 중 : " + e.getMessage());
		} finally {
			closeDB(rs, pstmt, con);
		}
		
		
		return timelinelist;
	}
	

	public int TimeLineInsert(TimeLine timeline) {
		int num = 0;
		String sql = "";
		int result = 0;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(timelinenumber) from TimeLine");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into TimeLine(timelinenumber,content_eng,content_kor,id) values(?,?,?,'admin')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, timeline.getContent_eng());
			pstmt.setString(3, timeline.getContent_kor());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("INSERT 작업 도중 SQLException 발생 : " + e.getMessage());
		} finally {
			closeDB(pstmt, con);
		}
		return result;

	}

	public List<TimeLine> getTimeLineListMain() {
		List<TimeLine> timelinelist = new ArrayList<TimeLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String sql = "select content_eng, content_kor, timelinenumber from timeline order by timelinenumber";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TimeLine timeline = new TimeLine();
				timeline.setContent_eng(rs.getString("content_eng"));
				timeline.setContent_kor(rs.getString("content_kor"));
				timeline.setTimeLineNumber(rs.getInt("timelinenumber"));
				timelinelist.add(timeline);
			}
		} catch (Exception e) {
			System.out.println("데이터 전체 조회 중 문제 발생 : " + e.getMessage());
		} finally {
			closeDB(rs, pstmt, con);
		}
		return timelinelist;
	}


	public int deleteTimeLine(int timelinenumber) {

		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			con = ds.getConnection();
			String sql = "delete from timeline where timelinenumber=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, timelinenumber);
			rowcount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("delete: " + e.getMessage());
		} finally {
			closeDB(pstmt, con);
		}

		return rowcount;
	}

	public TimeLine selectTimeLine(int timelinenumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		TimeLine timeline = null;
		try {
			con = ds.getConnection();

			String sql = "select content_eng,content_kor,timelinenumber from TimeLine where timelinenumber=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, timelinenumber);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				timeline = new TimeLine();
				timeline.setContent_eng(rs.getString(1));
				timeline.setContent_kor(rs.getString(2));
				timeline.setTimeLineNumber(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAccount 에러 발생");
		} finally {
			closeDB(rs, pstmt, con);
		}
		return timeline;
	}

	public int updateTimeLine(TimeLine timeline) {
		PreparedStatement pstmt = null;
		int rowcount = 0;

		try {
			con = ds.getConnection();
			String sql = "update timeline set content_eng=?,content_kor=?,timelinenumber=? where timelinenumber=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, timeline.getContent_eng());
			pstmt.setString(2, timeline.getContent_kor());
			pstmt.setInt(3, timeline.getTimeLineNumber());
			pstmt.setInt(4, timeline.getTimeLineNumber());
			rowcount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("update: " + e.getMessage());
		} finally {
			closeDB(pstmt, con);
		}
		return rowcount;

	}

	private void closeDB(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				System.out.println("resultSet close error : " + e.getMessage());
			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				System.out.println("preparedStatement close error : " + e.getMessage());
			}
		}

		if (connection != null) {
			try {
				connection.close();
				System.out.println(con.isClosed() ? "Connection 반환 됨" : "Connection 반환 안 됨");
			} catch (Exception e) {
				System.out.println("Connection close error : " + e.getMessage());
			}
		}
	}

	private void closeDB(PreparedStatement preparedStatement, Connection connection) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				System.out.println("preparedStatement close error : " + e.getMessage());
			}
		}

		if (connection != null) {
			try {
				connection.close();
				System.out.println(con.isClosed() ? "Connection 반환 됨" : "Connection 반환 안 됨");
			} catch (Exception e) {
				System.out.println("Connection close error : " + e.getMessage());
			}
		}
	}
}

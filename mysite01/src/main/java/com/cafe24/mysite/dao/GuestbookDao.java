package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.GuestbookVo;


public class GuestbookDao {
	
	
	public Boolean delete(GuestbookVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstat= null;
		try {

			conn = getConnection();
			
			String sql = "delete from guestbook where no=? and password = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, vo.getNo());
			pstat.setString(2, vo.getPassword());
			
			
			int count = pstat.executeUpdate();
			
			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: "+e);
		} finally {
			try {
			
				if(pstat != null) {
					pstat.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}

	public Boolean insert(GuestbookVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstat= null;
		try {

			conn = getConnection();
			
			String sql = "insert into guestbook values(null,?,?,?,now())";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getName());
			pstat.setString(2, vo.getPassword());
			pstat.setString(3, vo.getContents());
			
			
			int count = pstat.executeUpdate();
			
			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: "+e);
		} finally {
			try {
			
				if(pstat != null) {
					pstat.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	public List<GuestbookVo> getList() {

		List<GuestbookVo> result = new ArrayList<GuestbookVo>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.42:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			String sql = "select no, name, contents, date_format(reg_date,'%Y-%m-%d %h:%i:%s') from guestbook order by reg_date desc";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				
				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.42:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		return conn;

	}

}

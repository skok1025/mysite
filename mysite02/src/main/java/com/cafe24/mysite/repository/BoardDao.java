package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.46:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		return conn;

	}

	public List<BoardVo> getList(Map<String, Object> map) {
		return sqlSession.selectList("board.getList", map);
	}

//	public BoardVo get(String no) {
//		BoardVo result = null;
//
//		Connection conn = null;
//		PreparedStatement pstat = null;
//		ResultSet rs = null;
//		try {
//
//			Class.forName("org.mariadb.jdbc.Driver");
//
//			String url = "jdbc:mariadb://192.168.1.46:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			System.out.println("연결성공");
//
//			String sql = "select no, title, cnt,date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date,(select name from user where no = b.member_no) as memberName,(select email from user where no = b.member_no) as memberEmail,contents,b.member_no from board b where no = ? order by reg_date desc";
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, no);
//			rs = pstat.executeQuery();
//
//			while (rs.next()) {
//				Long boardNo = rs.getLong(1);
//				String title = rs.getString(2);
//				String cnt = rs.getString(3);
//				String regDate = rs.getString(4);
//				String memberName = rs.getString(5);
//				String memberEmail = rs.getString(6);
//				String contents = rs.getString(7);
//				Long memberNo = rs.getLong(8);
//				
//				result = new BoardVo();
//				result.setNo(boardNo);
//				result.setTitle(title);
//				result.setCnt(cnt);
//				result.setRegDate(regDate);
//				result.setMemberName(memberName);
//				result.setMemberEmail(memberEmail);
//				result.setContents(contents);
//				result.setMember_no(memberNo);
//			}
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//		} catch (SQLException e) {
//			System.out.println("error: " + e);
//		} finally {
//			try {
//
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstat != null) {
//					pstat.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;
//
//	}

	public Boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.46:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			String sql = "update board set title=?, contents=? where no=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getTitle());
			pstat.setString(2, vo.getContents());
			pstat.setLong(3, vo.getNo());
			
			result = (pstat.executeUpdate()==1);

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

	public String getMemberno(String no) {
		String result = null;
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.46:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			String sql = "select member_seq from board where no = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, no);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				result = rs.getString(1);
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

	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo);
	}

	public BoardVo get(String no) {
		return sqlSession.selectOne("board.get",no);
	}

	public void updateHit(String no) {
		sqlSession.update("board.updatehit", no);
	}

	public int getGroupNo(int replyno) {
		return sqlSession.selectOne("board.getgroupno", replyno);
	}

	public int getOrderNo(int replyno) {
		
		return sqlSession.selectOne("board.getorderno",replyno);
	}

	public void updateOrderNo(Map<String, Integer> map) {
		sqlSession.update("board.updateorderno", map);
	}

	public int replyinsert(BoardVo vo) {
		return sqlSession.insert("board.replyinsert", vo);
	}

	public int getDepth(int replyno) {
		return sqlSession.selectOne("board.getdepth", replyno);
	}

	public int updateisexist(String no) {
		return sqlSession.update("board.updateisexist", no);
	}

	public int getTotalcount(String kwd) {
		return sqlSession.selectOne("board.gettotalcount",kwd);
	}

//	public boolean insert(BoardVo vo) {
//		Boolean result = false;
//		
//		Connection conn = null;
//		PreparedStatement pstat= null;
//		try {
//
//			conn = getConnection();
//			
//			String sql = " insert into board values(null,?,0,now(),?,?)";
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, vo.getTitle());
//			pstat.setString(2, vo.getContents());
//			pstat.setLong(3, vo.getMember_no());
//			
//			
//			int count = pstat.executeUpdate();
//			
//			result = (count == 1);
//		} catch (SQLException e) {
//			System.out.println("error: "+e);
//		} finally {
//			try {
//			
//				if(pstat != null) {
//					pstat.close();
//				}
//				
//				if(conn != null) {
//					conn.close();
//				}
//			
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		return result;
//	}

}

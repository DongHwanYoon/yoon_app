package kr.ac.sunmoon.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.ac.sunmoon.model.dto.Portfolio;
import kr.ac.sunmoon.model.dto.PortfolioData;
import kr.ac.sunmoon.utill.DBManager;



/* DAO(Data Access Object)
 * - 데이터베이스 관련 작업을 전담하는 클래스
 * - 데이터베이스에 접근하여 입력, 수정, 삭제, 조회 등의 작업을 하는 클래스
 * - CRUD 작업
 *   C : Create => insert
 *   R : Read => select 
 *   U : Update
 *   D : Delete
 */

public class PortfolioDAO {
	
	// 포트폴리오를 전체 조회하는 메소드
	public ArrayList<Portfolio> select() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no,title,leader,members,start_date,end_date,reg_date,"
				+ "(select count(*) from portfolio_data where portfolio_no = p.no) " // 서브쿼리. 이것은 상관관계(스칼라) 서브쿼리문. portfolio 클래스의 dataCount 변수에 담김
				+ "from portfolio p";
		ArrayList<Portfolio> list = new ArrayList<Portfolio>();
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// 퀴리순서대로 ()안에 숫자 부여
				list.add(new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						null, rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		}finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	}
	
	// 제목에 title과 비슷한 words가 들어간 포트폴리오를 조회하는 메소드
	public ArrayList<Portfolio> select(String title) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no,title,leader,members,start_date,end_date,reg_date,"
				+ "(select count(*) from portfolio_data where portfolio_no = p.no) " // 서브쿼리. 이것은 상관관계(스칼라) 서브쿼리문. portfolio 클래스의 dataCount 변수에 담김
				+ "from portfolio p where title like ?"; // title이 ?와 비슷한 것을 가져와라
		ArrayList<Portfolio> list = new ArrayList<Portfolio>();
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + title + "%");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// 퀴리순서대로 ()안에 숫자 부여
				list.add(new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						null, rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		}finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	}
	
	//
	public ArrayList<PortfolioData> selectDataList(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no,original_file_name,real_file,name "
				+ "from portfolio_data " 
				+ "where portfolio_no = ?"; // title이 ?와 비슷한 것을 가져와라
		ArrayList<PortfolioData> list = new ArrayList<PortfolioData>();
		
		try {
			con = DBManager.getConnection(); // Connection 빌려온다
			stmt = con.prepareStatement(sql); // Statement 생성
			stmt.setInt(1, no);
			rs = stmt.executeQuery(); // 쿼리문 실행
			
			while (rs.next()) {
				// 퀴리순서대로 ()안에 숫자 부여
				list.add(new PortfolioData(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		}finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	}
	
	// int no에 해당하는 포트폴리오를 검색하는 메소드. 
	public Portfolio select(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no,title,leader,members,content,start_date,end_date,reg_date "
				+ "from portfolio where no = ?"; // 번호가 ?인 포트폴리오를 가져와라
		Portfolio p = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				// 퀴리순서대로 (?)안에 숫자 부여
				p = new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(5), rs.getString(6), rs.getString(7), 0);
			}
		}finally {
			DBManager.close(rs, stmt, con);
		}
		return p;
	}
	
	// 포트폴리오 등록에 사용되는 메소드
	public void update(Portfolio p) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		// 아래 물음표 부분에 해당하는 데이터가 채워질 것.
		String sql = "update portfolio set title=?,leader=?,members=?,content=?,"
				+ "start_date=STR_TO_DATE(?, '%Y-%m-%d'),"
				+ "end_date=STR_TO_DATE(?, '%Y-%m-%d'),reg_date=curdate()";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getTitle()); // 물음표 첫번째
			stmt.setString(2, p.getLeader()); // 물음표 두번째
			stmt.setString(3, p.getMember());
			stmt.setString(4, p.getContent());
			stmt.setString(5, p.getStartDate());
			stmt.setString(6, p.getEndDate());
			stmt.executeUpdate();
		} finally {
			//try 중 오류가 발생하면 예외처리 전 finally를 먼저 거쳐라
			DBManager.close(stmt, con);
		}
	}
	
	// 포트폴리오 등록에 사용되는 메소드
	public int insert(Portfolio p) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null, noStmt = null;
		ResultSet rs = null;
		// 아래 ? 부분에 해당하는 데이터가 채워질 것.
		String sql = "insert into portfolio(title,leader,members,content,start_date,end_date,reg_date)"
				+ " values(?,?,?,?,STR_TO_DATE(?, '%Y-%m-%d'),STR_TO_DATE(?, '%Y-%m-%d'),curdate())";
		String noSql = "select last_insert_id() from portfolio";
		int no = 0;

		try {
			con = DBManager.getConnection();
			con.setAutoCommit(false); // 지금 처리된 모든 작업을 db에 할당하세요 . 12:11m~ 20.1.2-1
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getTitle()); // 물음표 첫번째
			noStmt = con.prepareStatement(noSql);
			stmt.setString(2, p.getLeader());
			stmt.setString(3, p.getMember());
			stmt.setString(4, p.getContent());
			stmt.setString(5, p.getStartDate());
			stmt.setString(6, p.getEndDate());
			stmt.executeUpdate();
			rs = noStmt.executeQuery();

			if (rs.next()) {
				no = rs.getInt(1);
			}
			con.commit(); // setAutoCommit이 정상 실행되었을 때
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		finally {
			con.setAutoCommit(true);
			// try 중 오류가 발생하면 예외처리 전 finally를 먼저 거쳐라
			DBManager.close(rs, stmt, con);
			DBManager.close(noStmt, con);
		}
		return no;
	}
		
	// 포트폴리오에 첨부파일을 올리는데 사용되는 메소드
	public void insert(int no, ArrayList<PortfolioData> list) throws Exception {
			Connection con = null;
			PreparedStatement stmt = null;
			//아래 물음표 부분에 해당하는 데이터가 채워질 것.
			String sql = "insert into portfolio_data(original_file_name,real_file_name,portfolio_no) "
					+ "values(?,?,?)";
			
			try {
				con = DBManager.getConnection();
				stmt = con.prepareStatement(sql);
				
				for (PortfolioData p : list) {
					stmt.setString(1, p.getOriginalFileName());
					stmt.setString(2, p.getRealFileName());
					stmt.setInt(3, no);
					stmt.executeUpdate();
				}
			} finally {
				//try 중 오류가 발생하면 예외처리 전 finally를 먼저 거쳐라
				DBManager.close(stmt, con);
			}
		}
	
	/* 포트폴리오 삭제에 사용되는 메소드
	 * 지우고자 하는 포트폴리오의 넘버(int no)만 필요
	 * int no가 일치하지 않으면 삭제안됨
	*/
	public void delete(int no) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete from portfolio where no = ?";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no); //물음표가 하나이므로 setString도 하나
			stmt.executeUpdate();
		} finally {
			//try 중 오류가 발생하면 예외처리 전 finally를 먼저 거침
			DBManager.close(stmt, con);
		}
	}
}

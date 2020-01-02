package net.edu.myapp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.edu.myapp.board.dto.BoardDto;
import net.edu.myapp.board.dto.BoardFileDto;
import net.edu.myapp.db.DBManager;
import net.edu.myapp.user.dto.UserDto;

public class BoardDaoImpl implements BoardDao {

	@Override
	public int boardInsert(BoardDto boardDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int ret = -1;
		
		try {
			con = DBManager.getConnection();
			String sql = 
					"INSERT INTO BOARD ( USER_SEQ, TITLE, CONTENT, REG_DT, READ_COUNT ) " +
					" VALUES ( ?, ?, ?, now(), 0 ) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  boardDto.getUserSeq());
			pstmt.setString(2,  boardDto.getTitle());
			pstmt.setString(3,  boardDto.getContent());

			ret = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

	@Override
	public List<BoardDto> boardList(int limit, int offset) {
		
		List<BoardDto> list = new ArrayList<BoardDto>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			String sql = 
					"SELECT b.BOARD_ID, b.USER_SEQ, u.USER_NAME, u.USER_PROFILE_IMAGE_URL," + 
					"		b.TITLE, b.CONTENT, b.REG_DT, b.READ_COUNT" + 
					"  FROM board b, user u" + 
					" WHERE b.USER_SEQ = u.USER_SEQ" + 
					" ORDER BY BOARD_ID DESC" + 
					" LIMIT ? OFFSET ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  limit);
			pstmt.setInt(2,  offset);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBoardId(rs.getInt("BOARD_ID"));
				boardDto.setUserSeq(rs.getString("USER_SEQ"));
				boardDto.setUserName(rs.getString("USER_NAME"));
				boardDto.setUserProfileImageUrl(rs.getString("USER_PROFILE_IMAGE_URL"));
				boardDto.setTitle(rs.getString("TITLE"));
				boardDto.setContent(rs.getString("CONTENT"));
				boardDto.setReadCount(rs.getInt("READ_COUNT"));
				boardDto.setRegDt(rs.getDate("REG_DT"));
				list.add(boardDto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return list;
	}

	@Override
	public int boardListTotalCnt() {
		int totalCnt = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return totalCnt;
	}

	@Override
	public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord) {
		List<BoardDto> list = new ArrayList<BoardDto>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			String sql = 
					"SELECT b.BOARD_ID, b.USER_SEQ, u.USER_NAME, u.USER_PROFILE_IMAGE_URL, " + 
					"		b.TITLE, b.CONTENT, b.REG_DT, b.READ_COUNT " + 
					"  FROM board b, user u " + 
					" WHERE b.USER_SEQ = u.USER_SEQ " + 
					"   AND b.title like ? " +
					" ORDER BY BOARD_ID DESC " + 
					" LIMIT ? OFFSET ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  "%" + searchWord + "%");
			pstmt.setInt(2,  limit);
			pstmt.setInt(3,  offset);
			
			System.out.println("pstmt" + pstmt);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBoardId(rs.getInt("BOARD_ID"));
				boardDto.setUserSeq(rs.getString("USER_SEQ"));
				boardDto.setUserName(rs.getString("USER_NAME"));
				boardDto.setUserProfileImageUrl(rs.getString("USER_PROFILE_IMAGE_URL"));
				boardDto.setTitle(rs.getString("TITLE"));
				boardDto.setContent(rs.getString("CONTENT"));
				boardDto.setReadCount(rs.getInt("READ_COUNT"));
				boardDto.setRegDt(rs.getDate("REG_DT"));
				list.add(boardDto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return list;
	}

	@Override
	public int boardListSearchWordTotalCnt(String searchWord) {
		int totalCnt = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement("select count(*) from board where title like ? ");
			pstmt.setString(1,  "%" + searchWord + "%");
			rs = pstmt.executeQuery();

			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return totalCnt;
	}

	
}

package net.edu.myapp.board.service;

import java.util.List;

import net.edu.myapp.board.dao.BoardDao;
import net.edu.myapp.board.dao.BoardDaoImpl;
import net.edu.myapp.board.dto.BoardDto;

public class BoardServiceImpl implements BoardService{

	@Override
	public int boardInsert(BoardDto dto) {
		BoardDao boardDao = new BoardDaoImpl();
		return boardDao.boardInsert(dto);
	}
	
	@Override
	public List<BoardDto> boardList(int limit, int offset, String searchWord) {
		
		System.out.println("searchWord " + searchWord);
		
		BoardDao boardDao = new BoardDaoImpl();
		if(searchWord == null || "".equals(searchWord)) {
			return boardDao.boardList(limit, offset);
		}else {
			return boardDao.boardListSearchWord(limit, offset, searchWord);
		}
	}
	
	@Override
	public int boardListTotalCnt(String searchWord) {
		BoardDao boardDao = new BoardDaoImpl();
		if("".equals(searchWord)) {
			return boardDao.boardListTotalCnt();
		}else {
			return boardDao.boardListSearchWordTotalCnt(searchWord);
		}
	}

}

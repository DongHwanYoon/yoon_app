package net.edu.myapp.board.dao;

import java.util.List;

import net.edu.myapp.board.dto.BoardDto;

public interface BoardDao {
	public int boardInsert(BoardDto dto);
	
	public List<BoardDto> boardList(int limit, int offset);
	public int boardListTotalCnt();
	
	public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord);
	public int boardListSearchWordTotalCnt(String searchWord);
}

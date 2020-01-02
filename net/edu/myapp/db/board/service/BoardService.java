package net.edu.myapp.board.service;

import java.util.List;

import net.edu.myapp.board.dto.BoardDto;

public interface BoardService {
	public int boardInsert(BoardDto dto);
	
	public List<BoardDto> boardList(int limit, int offset, String searchWord);

	public int boardListTotalCnt(String searchWord);
}

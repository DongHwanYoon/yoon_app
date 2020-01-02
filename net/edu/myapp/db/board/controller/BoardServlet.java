package net.edu.myapp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.edu.myapp.board.dto.BoardDto;
import net.edu.myapp.board.service.BoardService;
import net.edu.myapp.board.service.BoardServiceImpl;
import net.edu.myapp.login.service.LoginService;
import net.edu.myapp.login.service.LoginServiceImpl;
import net.edu.myapp.user.dto.UserDto;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet(
	{
	"/board", 
	"/board/list",
	"/board/list/totalCnt",
	"/board/insert"
	})
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
		      throws ServletException, IOException {
		
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		System.out.println("contextPath : " + contextPath);
		System.out.println("servletPath : " + servletPath);
		System.out.println("pathInfo : " + pathInfo);
		if("/board".equals(servletPath)) {
			System.out.println("/");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board.jsp");
			dispatcher.forward(request, response);
			
		}else if("/board/list".equals(servletPath)) {
			System.out.println("/list");
			
			String limit = request.getParameter("limit");
			String offset = request.getParameter("offset");
			String searchWord = request.getParameter("searchWord");
			
			System.out.println("/list-limit " + limit);
			System.out.println("/list-offset " + offset);
			int limitInt = Integer.parseInt(limit);
			int offsetInt = Integer.parseInt(offset);
			BoardService boardService = new BoardServiceImpl();
			List<BoardDto> list = boardService.boardList(limitInt, offsetInt, searchWord);
			System.out.println("/list.size() " + list.size());
			
			JSONArray jsonArray = new JSONArray();
			for(int i=0; i<list.size(); i++) {
				BoardDto boardDto = list.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("boardId", boardDto.getBoardId());
				map.put("userSeq", boardDto.getUserSeq());
				map.put("userName", boardDto.getUserName());
				map.put("userUserProfileImageUrl", boardDto.getUserProfileImageUrl());
				map.put("title", boardDto.getTitle());
				map.put("content", boardDto.getContent());
				map.put("readCount", boardDto.getReadCount());
				
				System.out.println(boardDto.getRegDt());
				map.put("regDt", boardDto.getRegDt()+"");
				JSONObject jsonObj =  new JSONObject(map);
				jsonArray.add(jsonObj);
			}
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonArray);
			
		}else if("/board/list/totalCnt".equals(servletPath)) {
			System.out.println("/list/totalCnt");

			String searchWord = request.getParameter("searchWord");
			
			System.out.println("/list/totalCnt-searchWord " + searchWord);

			BoardService boardService = new BoardServiceImpl();
			int totalCnt = boardService.boardListTotalCnt(searchWord);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("totalCnt", totalCnt);
			JSONObject jsonObj =  new JSONObject(map);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObj);
			
		}else if("/board/detail".equals(servletPath)) {
			System.out.println("/detail");
		}else if("/board/insert".equals(servletPath)) {
			System.out.println("/insert");
			
			String userSeq = request.getParameter("userSeq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardDto boardDto = new BoardDto();
			boardDto.setUserSeq(userSeq);
			boardDto.setTitle(title);
			boardDto.setContent(content);
			
			BoardService boardService = new BoardServiceImpl();
			int cnt = boardService.boardInsert(boardDto);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			if( cnt > 0 ) {
				map.put("result", "success");
			}else {
				map.put("result", "fail");
			}
			
			JSONObject jsonObj =  new JSONObject(map);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObj);
			
		}else if("/board/update".equals(servletPath)) {
			System.out.println("/update");
		}else if("/board/delete".equals(servletPath)) {
			System.out.println("/delete");
		}

	}
}

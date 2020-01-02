package net.edu.myapp.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import net.edu.myapp.login.service.LoginService;
import net.edu.myapp.login.service.LoginServiceImpl;
import net.edu.myapp.user.dto.UserDto;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet - doPost()");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		
		LoginService loginService = new LoginServiceImpl();
		UserDto userDto = loginService.login(userEmail, userPassword);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(userDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userDto", userDto);
			
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		JSONObject jsonObj =  new JSONObject(map);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonObj);
	}

}

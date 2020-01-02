package com.edu.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InitParamServlet extends HttpServlet{

	String id, password;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		id = config.getInitParameter("id");
		password = config.getInitParameter("password");
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print("<h2>서블릿 초기 추출 변수 </h2>" );
		out.print("<h3>ID : "+id+"</h3>");
		out.print("<h3>PASSWORD : "+password+"</h3>");
		out.close();
	}
}

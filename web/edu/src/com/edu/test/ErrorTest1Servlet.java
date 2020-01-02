package com.edu.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/errorTest1")
public class ErrorTest1Servlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		String getquery = req.getQueryString();
		out.print("Query : " + getquery + "<br>");
		out.print("Query ±Ê¿Ã : " + getquery.length());
		out.print("Done!");

		out.close();
	}
}

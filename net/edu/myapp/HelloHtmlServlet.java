package net.edu.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloHtmlServlet
 */
@WebServlet("/HelloHtmlServlet")
public class HelloHtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter()
		.append("<!DOCTYPE html>")
		.append("<html>")
		.append("<head>")
		.append("<meta charset=\"EUC-KR\">")
		.append("<title>Hello</title>")
		.append("</head>")
		.append("<body>")
		.append("<h1>안녕하세요.</h1>")
		.append("</body>")
		.append("</html>");
		
//		PrintWriter out = response.getWriter();
//		out.print("<!DOCTYPE html>");
//		out.print("<html>");
//		out.print("<html>");
//		out.print("<head>");
//		out.print("<meta charset=\"EUC-KR\">");
//		out.print("<title>Hello</title>");
//		out.print("</head>");
//		out.print("<body>");
//		out.print("<h1>안녕하세요.</h1>");
//		out.print("</body>");
//		out.print("</html>");
//		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

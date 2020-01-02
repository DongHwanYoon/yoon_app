package kr.ac.sunmoon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sunmoon.model.service.PortfolioService;

/**
 * Servlet implementation class PortfolioDetalSearchServlet
 */
@WebServlet("/portfolio_detail.do")
public class PortfolioDetalSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// parameter : no
		int no = Integer.parseInt(request.getParameter("no"));
		PortfolioService pService = new PortfolioService();
		try {
			//request.setCharacterEncoding("utf-8"); // title을 한글로 받을수도 있으므로 인코딩 설정
			pService.getDetail(no);
			request.setAttribute("no", pService.getDetail(no));
			response.sendRedirect("portfolio_list.do");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
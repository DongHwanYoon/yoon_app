package kr.ac.sunmoon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.ac.sunmoon.model.dto.Portfolio;
import kr.ac.sunmoon.model.dto.PortfolioData;
import kr.ac.sunmoon.model.service.PortfolioService;
import kr.ac.sunmoon.utill.DateTimeFileReNamePolicy;
import sun.net.www.content.text.plain;


@WebServlet("/portfolio_register.do") // portfolio_input.html파일에서 action에 의해 호출됨. 10번째 줄
public class PortfolioRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String saveDir = "uploadFileSave"; // 업로드한 파일 저장하는 곳. WebContent 하위 폴더로 존재
			String saveFullDir = getServletContext().getRealPath(saveDir); // 현재 이 서블릿이 동작하고 있는 앱이 배포된 실제 물리적 경로를 알아옴
			int maxFileSize = 5*1024*1024;
			String encoding = "utf-8";
			
			MultipartRequest mRequest = null;
		 	mRequest = new MultipartRequest(request,saveFullDir,maxFileSize,encoding,
		 			new DateTimeFileReNamePolicy());
		 	
		 	String title = mRequest.getParameter("title");
		 	String leader = mRequest.getParameter("leader"); 
		 	String member = mRequest.getParameter("member");
		 	String content = mRequest.getParameter("content");
		 	String startDate = mRequest.getParameter("startDate");
		 	String endDate = mRequest.getParameter("endDate");
		 	
		 	Portfolio p = new Portfolio(title, leader, member, content, startDate, endDate);
		 	ArrayList<PortfolioData> list = new ArrayList<PortfolioData>();		
		 	Enumeration em = mRequest.getFileNames();
		 	
		 	while (em.hasMoreElements()) {
				String name = (String)em.nextElement();
				String originalFileName = mRequest.getOriginalFileName(name);
				if(originalFileName == null) continue;
				String realFileName = mRequest.getFilesystemName(name);
				System.out.println(originalFileName + "//" + realFileName);
				list.add(new PortfolioData(originalFileName, realFileName));
			}
		 	p.setDataList(list);
		 	
		 	PortfolioService pService = new PortfolioService();
		 	pService.register(p);
	 		
		 	response.sendRedirect("portfolio_list.do");
	 		
		}catch(Exception e){
			/*
			 * 어떤 controller던지 오류가 발생하면 에러메시지를 저장하고
			 * error.jsp로 이동시킴
			 */
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response); // 포워딩 ?
		}	
	}

}

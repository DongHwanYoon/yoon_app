package net.edu.myapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. http://localhost:8080/ParameterTestServlet - null - 
 * 2. http://localhost:8080/ParameterTestServlet?message=Hello...
 * 3. http://localhost:8080/ParameterTestServlet?message=안녕하세요...
 * 4. http://localhost:8080/ParameterTestServlet?message=안녕하세요...한글 잘 나오나요?
 * 5. http://localhost:8080/ParameterTestServlet?message=안녕하세요...&myName=김대섭
 */
@WebServlet("/ParameterTestServlet")
public class ParameterTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParameterTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String message = request.getParameter("message");
		String myName = request.getParameter("myName");
		String score = request.getParameter("score");
		System.out.println("GET client message : " + message);
		System.out.println("GET client myName : " + myName);
		System.out.println("GET client score : " + score);
		response.getWriter().append("GET client message : ").append(message);
		response.getWriter().append("GET client myName : ").append(myName);
		response.getWriter().append("GET client score : ").append(score);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String message = request.getParameter("message");
		String myName = request.getParameter("myName");
		System.out.println("POST client message : " + message);
		System.out.println("POST client myName : " + myName);
		response.getWriter().append("POST client message : ").append(message);
		response.getWriter().append("POST client myName : ").append(myName);
	}

}

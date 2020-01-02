<%@page import="com.edu.biz.MemberService"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="com.edu.beans.Member" />
<jsp:setProperty property="*" name="member" />

<%
	MemberService service = new MemberService();
	service.memberInsert(member);

	RequestDispatcher rd = request.getRequestDispatcher("memberOutput.jsp");
	rd.forward(request, response);
%>

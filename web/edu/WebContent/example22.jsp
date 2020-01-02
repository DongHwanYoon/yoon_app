<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import ="com.edu.beans.BookBean" %>
<html>
<head>
<title>example</title>
</head>
<body>
	<jsp:useBean id="book" class="com.edu.beans.BookBean" />
	<jsp:setProperty property="*" name="book" />

	<%
		request.setAttribute("book", book);
	%>

	<jsp:forward page="bookOutput.jsp" />

</body>
</html>
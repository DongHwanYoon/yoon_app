<html>
<body>
  <!--  HTML 주석문 -->
	<%
		String user = request.getParameter("name");
		if (user == null)
		  // user = "Guest";
	%>
	<h1>
		Hello
		<%-- <%=user%>! --%>
	</h1>
</body>
</html>



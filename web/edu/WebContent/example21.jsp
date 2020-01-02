<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" %>
<html>
<head>
<title></title>
</head>
<body>
	<%
		Enumeration<String> list = request.getHeaderNames();
		while (list.hasMoreElements()) {
			String key = list.nextElement();
			out.print("<br>" + key + " : " + request.getHeader(key));
		}
	%>

<hr>
${header}
</body>
</html>
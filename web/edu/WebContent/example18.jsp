<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>EL</title>
</head>
<body>
	Literals : ${"Literals"}
	<br> Operators : ${5>3}
	<br> Implicit Objects : ${header["host"]}
	<br>${true}
	<br>${false}
	<br>${123}
	<br>${3.14}
	<br>${"JAVA"}
	<br>${'java'}
	
	
	<br> ${empty "" }
	<br> ${empty null }
</body>
</html>
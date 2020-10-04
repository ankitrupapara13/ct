<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="demo" uri="http://ankit.com"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<demo:myTag query="select * from app.product"
		database="jdbc:derby:C:\Users\Ankit\MyDB" username="admin"
		password="derby"></demo:myTag>

</body>
</html>
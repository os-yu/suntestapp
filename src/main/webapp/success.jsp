<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*,java.util.Calendar,login.UserAccount"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserAccount account = (UserAccount)request.getAttribute("account");
	String id = account.getId();
	String pass = account.getPass();
	String email = account.getEmail();
	String birth = account.getBirthday();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
</head>
<body>
	<h1>登録完了しました！</h1>
	<ul>
		<li>ユーザーID：<%out.print(id);%></li>
		<li>パスワード：<%out.print(pass);%></li>
		<li>E-Mailアドレス：<%out.print(email);%></li>
		<li>生年月日：<%out.print(birth);%></li>
	</ul>
	<br><a href="login.jsp">ログインページへ</a>
</body>
</html>
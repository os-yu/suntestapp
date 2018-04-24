<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
</head>
<body>
	<h1>登録完了しました！</h1>
	<ul>
		<li>ユーザーID：${requestScope['account'].id}</li>
		<li>パスワード：${requestScope['account'].pass}</li>
		<li>E-Mailアドレス：${requestScope['account'].email}</li>
		<li>生年月日：${requestScope['account'].birthday}</li>
	</ul>
	<br><a href="login.jsp">ログインページへ</a>
</body>
</html>
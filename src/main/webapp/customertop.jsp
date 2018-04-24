<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*,java.util.ArrayList,login.UserAccount"%>
<%
	String id = (String)session.getAttribute("id");
	if(id == null){id = "名無し";}
%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧ページ</title>
</head>
<body>
<div id="page">
	<div id="top">
		<div id="user">ようこそ<%=id %>さん&nbsp;&nbsp;
			<a href="login.jsp">ログアウト</a><br>
		</div><br>
	</div><!-- id=top -->
	<div id="left"><br>ユーザー
		<table>
			<tr><th>ユーザＩＤ</th><th>メールアドレス</th></tr>
<%
	ArrayList<UserAccount> accounts = (ArrayList<UserAccount>)session.getAttribute("clist");
	for(int i = 0; i < accounts.size(); i++){
		out.print("<tr><td><a href=\"AccountServlet?id="+ URLEncoder.encode(accounts.get(i).getId(),"UTF-8") +"\">" + accounts.get(i).getId() + "</a></td><td>" + accounts.get(i).getEmail() + "</td></tr>");
	}
%>
		</table>
	</div><!-- id=left -->
</div><!-- id=page -->
</body>
</html>
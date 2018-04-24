<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログインフォーム</title>
	<script Language="JavaScript">
		function check(){
			var id = document.form1.id.value;
			var pass = document.form1.pass.value;

			if(id == "" || pass == ""){
				alert("空欄があります。");
				return false;
			}

			if(id.match(/[^a-zA-Z0-9_]/) || pass.match(/[^a-zA-Z0-9_]/)){
				alert("使用可能な特殊文字は _ のみです。")
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<h1>ログイン</h1>
<%
	if(request.getParameter("message") != null){

	}
%>
	<form name="form1" method="POST" action="LoginServlet" onSubmit="return check()">
		<br>ユーザーID：<input type="text" name="id" style="ime-mode:disabled" /><br />
		<br>パスワード：<input type="text" name="pass" style="ime-mode:disabled" /><br />
		<input type="submit" value="ログイン" /><br />
	</form>
	<br><a href="enter.jsp">ユーザー登録へ</a>
</body>
</html>
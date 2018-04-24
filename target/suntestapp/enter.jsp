<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 		String msg = (String)request.getAttribute("msg");
    	if(msg == null){msg = "";}
%>
<%!
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
	<script Language="JavaScript">
		function check(){
			var id = document.form2.id.value;
			var pass = document.form2.pass.value;
			var email = document.form2.email.value;

			if(id == "" || pass == "" || email == ""){
				alert("空欄があります。");
				return false;
			}

			if(id.match(/[^a-zA-Z0-9_]/) || pass.match(/[^a-zA-Z0-9_]/)){
				alert("使用可能な特殊文字は _ のみです。")
				return false;
			}
			if(email.match(/[^A-Za-z0-9_@.]/)){
				alert("使用不可能な文字が含まれています。")
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<h1>ユーザー登録</h1>
	<%=msg %><br>
	<form name="form2" method="POST" action="EnterServlet" onSubmit="return check()">
		<br>登録するユーザーIDを入力してください:<br />
		<input type="text" name="id" style="ime-mode:disabled" />
		<br>パスワードを入力してください<br />
		<input type="text" name="pass" style="ime-mode:disabled" />
		<br>E-Mailアドレスを入力してください<br />
		<input type="text" name="email" style="ime-mode:disabled" />
		<br>生年月日を選択してください<br />
		<select name="year">
<%
	for(int i = 1900; i <= year; i++)
	{
		if(i == year)
			out.print("<option value=\"" + i + "\" selected=\"selected\">" + i + "</option>");
		out.print("<option value=\"" + i + "\">" + i + "</option>");
	}
%>
		</select>
		年
		<select name="month">
<%
	for(int i = 1; i <= 12; i++)
	{
		out.print("<option value=\"" + i + "\">" + i + "</option>");
	}
%>
		</select>
		月
		<select name="day">
<%
	for(int i = 1; i <= 31; i++)
	{
		out.print("<option value=\"" + i + "\">" + i + "</option>");
	}
%>
		</select>
		日<br />
		<input type="submit" value="登録">
	</form>
	<br><a href="login.jsp">ログインページへ</a>
</body>
</html>
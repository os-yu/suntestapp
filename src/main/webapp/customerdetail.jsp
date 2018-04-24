<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*,java.util.Calendar,login.UserAccount"%>
<%
	UserAccount account = (UserAccount)request.getAttribute("account");
	String id = account.getId();
	String email = account.getEmail();
	String birth = account.getBirthday();
	int year = Integer.parseInt(birth.substring(0,birth.indexOf("年")));
	int mon = Integer.parseInt(birth.substring(birth.indexOf("年")+1,birth.indexOf("月")));
	int day = Integer.parseInt(birth.substring(birth.indexOf("月")+1,birth.length()-1));
%>
<% 		String msg = (String)request.getAttribute("msg");
    	if(msg == null){msg = "";}
%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ詳細ページ</title>
	<script Language="JavaScript">
		function check(){
			var id = document.form1.id.value;
			var email = document.form1.email.value;

			if(id == "" || email == ""){
				alert("空欄があります。");
				return false;
			}

			if(id.match(/[^a-zA-Z0-9_]/)){
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
	<%=msg %><br>
	<form name="form1" method="POST" action="AccountServlet" onSubmit="return check()">
	<table>
		<tr><th>ユーザＩＤ</th><th>メールアドレス</th><th>生年月日</th></tr>
		<tr>
			<td><%out.print(id);%></td>
			<td><input type="text" name="email" style="ime-mode:disabled" value="<%out.print(email);%>"/></td>
			<td>
		<select name="year"><%out.print(birth);%>
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
//		if(i == mon)
//			out.print("<option value=\"" + i + "\" selected=\"selected\">" + i + "</option>");
		out.print("<option value=\"" + i + "\">" + i + "</option>");
	}
%>
		</select>
		月
		<select name="day">
<%
	for(int i = 1; i <= 31; i++)
	{
//		if(i == day)
//			out.print("<option value=\"" + i + "\" selected=\"selected\">" + i + "</option>");
		out.print("<option value=\"" + i + "\">" + i + "</option>");
	}
%>
		</select>
		</td>
		</tr>
	</table>
	<input type="submit" value="登録">
	<input type="hidden" name="code" value="update"/>
	</form>
	<form name="form2" method="POST" action="AccountServlet">
	<input type="submit" value="削除">
	<input type="hidden" name="id" value="<%out.print(id);%>"/>
	<input type="hidden" name="code" value="del"/>
	</form>
</body>
</html>
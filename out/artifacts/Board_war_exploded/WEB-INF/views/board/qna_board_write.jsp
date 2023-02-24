<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%--<%
	String loginSession = null;
	if(session.getAttribute("id") != null){
		loginSession = (String)session.getAttribute("id");

	}
%>--%>

<%
	String loginSession = null;
	if(session.getAttribute("id") != null){
		loginSession = (String)session.getAttribute("id");
	}else{
		PrintWriter write = response.getWriter();
		write.println("<script> alert('로그인이 필요합니다.') </script>");
		write.println("<script> location.href='/member/login'</script>");
		write.close();
	}
%>

<head>
	<title>MVC 게시판</title>
	<script language="javascript">
	function addboard(){
		boardform.submit();
	}
	</script>
</head>
<body>

<!-- 게시판 등록 -->
<form action="writeAction" method="post" name="boardform">
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋음;" height="16">
			<div align="center">글쓴이</div>
		</td>
		<td>
			<input name="name" type="text" size="10" maxlength="10"
				value=<%=loginSession%> readonly/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; " height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="pass" type="password" size="10" maxlength="10"
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음;" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="subject" type="text" size="50" maxlength="100"
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음;">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="content" cols="67" rows="15"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; ">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			<input name="file" type="file"/>
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
		</td>
	</tr>
</table>
</form>
<!-- 게시판 등록 -->
</body>
</html>
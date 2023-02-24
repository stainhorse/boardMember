<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<head>
	<title>게시글 목록</title>
	<style>
		th, td {
			padding: 5px;
		}
	</style>

</head>

<body>

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


<!-- 게시판 리스트 -->
<table style="width: 650px; border-collapse: collapse;
                border-color: rgb(0, 84, 255);" border="1">
	<tr align="center" style="background-color: rgb(178, 204, 255);" valign="middle">
		<td colspan="4">MVC 게시판</td>
		<td align=right>
			<%--<font size=2>글 개수 : ${listcount }</font>--%>
		</td>
	</tr>

	<tr align="center" valign="middle" bordercolor="#333333">
		<td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26">
			<div align="center">번호</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="50%">
			<div align="center">제목</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="14%">
			<div align="center">작성자</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="17%">
			<div align="center">날짜</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="11%">
			<div align="center">조회수</div>
		</td>
	</tr>

	<c:forEach items="${list}" var="dto">

	<tr align="center" valign="middle" bordercolor="#333333"
		onmouseover="this.style.backgroundColor='F8F8F8'"
		onmouseout="this.style.backgroundColor=''">
		<td height="23" style="font-family:Tahoma;font-size:10pt;">
			${dto.board_num}
		</td>
		
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="left">

			<a href="detail?num=${dto.board_num}">
				${dto.board_subject}
			</a>
			</div>
		</td>
		
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center">${dto.board_name}</div>
		</td>
		<td style="font-family:Tahoma;font-size:10pt;">
		<div align="center">${dto.board_date}</div>
		</td>	
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center">${dto.board_readcount}</div>
		</td>
	</tr>
	</c:forEach>

	<tr align="right">
		<td colspan="5" align="center">
	   		<a href="write">[글쓰기]</a>
		</td>
	</tr>
</table>
	<br>
	<%
		assert loginSession != null;
		if(loginSession.equals("admin")){
	%>
		<a href="/member/list"> 관리자 모드 접속(회원 목록 보기) </a>	<br><br>
	<%
		}
	%>

	<a href="/member/detail?id=<%=loginSession%>"> 마이페이지 </a>	<br><br>

	<input type="button" value="뒤로가기" onclick="history.back()" >
	<input type="button" value="로그아웃" onclick="location.href='/member/logout' ">
	<input type="button" value="세션확인" onclick="alert('<%=loginSession%>')" >
	
</body>
</html>
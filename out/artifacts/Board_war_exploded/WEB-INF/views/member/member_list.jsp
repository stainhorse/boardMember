<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>유저목록</title>
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


	<div align="center">
		<div style="text-align: center">
			<div class="row">
				<table  width="500" cellpadding="0" cellspacing="0" border="1">
					<thead>
					<th align="center" style="background-color: rgb(178, 204, 255);" colspan="3">회원 목록</th>
					</thead>
						<tr>
						<td>아이디</td>
						<td>이름</td>
						<td>메일</td>
						</tr>
					 <c:forEach items="${list}" var="dto">
						<tr>
							<td><a href="detail?id=${dto.id}">${dto.id}</a></td>
							<td>${dto.name}</td>
							<td>${dto.mail}</td>
						</tr>
					 </c:forEach>
					<tr>
						<td colspan="3">
							<a href="/board/list">[게시판으로]</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</body>
</html>
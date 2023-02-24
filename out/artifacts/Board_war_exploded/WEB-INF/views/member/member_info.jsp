<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세보기 페이지</title>
<style>
    th, td {
        padding: 5px;
    }
 </style>
</head>
<body>
	<div style="text-align: center">
		<div class="row">
			<table style="width: 300px; border-collapse: collapse; 
                border-color: rgb(0, 84, 255);" border="1">
				
					<th align="center" style="background-color: rgb(178, 204, 255);" colspan="8">회원 정보</th>
						<tr>  <td>${memberDetail.id}</td> </tr>
						<tr>  <td>${memberDetail.pw}</td> </tr>
						<tr>  <td>${memberDetail.mail}</td> </tr>
						<tr>  <td>${memberDetail.name}</td> </tr>
						<tr>  <td>${memberDetail.serial}</td> </tr>
						<tr>  <td>${memberDetail.birth}</td> </tr>
						<tr>  <td>${memberDetail.hobby}</td> </tr>
						<tr>  <td>${memberDetail.intro}</td> </tr>
						<tr>  <td>${memberDetail.address}</td> </tr>
						<tr>
							<td>
								<a href="modify?id=${memberDetail.id}">수정 | </a>
								<a href="deleteAction?id=${memberDetail.id}">삭제 | </a>
								<a href="javascript:history.go(-1)">[뒤로]</a>
							</td>
						</tr>
			</table>
		</div>
	</div>
</body>
</html>
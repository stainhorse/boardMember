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
			<form action="modifyAction?id=${memberDetail.id}">
				<input type="hidden" name="id" value=${memberDetail.id}>
				<table style="width: 300px; border-collapse: collapse;
                border-color: rgb(0, 84, 255);" border="1">

					<th align="center" style="background-color: rgb(178, 204, 255);" colspan="8">회원 정보 수정</th>
					<tr>  <td> <input name="pw" type="text" value="${memberDetail.pw}"></td> </tr>
					<tr>  <td><input name="mail" type="text" value="${memberDetail.mail}"></td> </tr>
					<tr>  <td><input name="name" type="text" value="${memberDetail.name}"></td> </tr>
					<tr>  <td><input name="serial" type="text" value="${memberDetail.serial}"></td> </tr>
					<tr>  <td><input name="birth" type="text" value="${memberDetail.birth}"></td> </tr>
					<tr>  <td><input name="hobby" type="text" value="${memberDetail.hobby}"></td> </tr>
					<tr>  <td><input name="intro" type="text" value="${memberDetail.intro}"></td> </tr>
					<tr>  <td><input name="address" type="text" value="${memberDetail.address}"></td> </tr>
					<tr>
						<td>
							<button type="submit">수정하기</button>
							<a href="javascript:history.go(-1)">[뒤로]</a>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>
</html>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    Object obj = session.getAttribute("id");
    if(obj != null){
        PrintWriter script = response.getWriter();
        script.println("<script>alert('이미 로그인된 회원입니다.')</script>");
        script.println("<script>history.back()</script>");
        script.close();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 입니다.</title>
<style>
    th, td {
        padding: 5px;
    }
 </style>
</head>
<body>
	<!-- 음악 재생을 위해 추가한 부분 -->
        <audio controls>
            <source src="seoul_subway.mp3" type="audio/mpeg">
          </audio>

        <br><br><br><br><br>

        <!-- 화면 전체에서 중간으로 옮기기 위해 추가 -->
        <div align="center">

         
            <form action="loginAction" method="post" >
                <table style="width: 300px; border-collapse: collapse;  border-color: rgb(0, 84, 255);" border="1">
                
                    <th align="center" style="background-color: rgb(178, 204, 255);" colspan="2">로그인 페이지</th>
                    <tr>
                        <td>아이디:</td>
                        <td><input id="id" type="text" name="id" size="15"></td>
                    </tr>
                    <tr>
                        <td>비밀번호:</td>
                        <td><input id="pw" type="password" name="pw" size="15"></td>
                    </tr>
                    
                    <tr align="center" style="background-color: rgb(178, 204, 255);" colspan="2">
             			
             			<td colspan="2">
             				<input type="submit" value="로그인">
             				<input type="button" value="회원가입" onclick="location.href='join'">
             			 </td>
                    </tr>
                </table>
    
            </form>

        </div>
	



</body>
</html>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function findAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('member_post').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("member_addr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("member_addr").value = jibunAddr;
            }
        }
    }).open();
}
</script>
<%	
	Object obj = session.getAttribute("id");
	if(obj != null){
		PrintWriter script = response.getWriter();
		script.println("<script>alert('이미 로그인된 회원입니다.')</script>");
        script.println("<script>history.back()</script>");
		script.close();
		//out.println("<script>alert(\"  \")</script>")
		response.sendRedirect("./BoardList.bo");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지입니다.</title>
<style>
    th, td {
        padding: 5px;
    }
</style>

</head>
<body>



	<% request.setCharacterEncoding("UTF-8"); %>

	<!-- 음악 재생을 위해 추가한 부분 -->
        <audio controls>
            <source src="seoul_subway.mp3" type="audio/mpeg">
          </audio>

        <br><br><br><br><br>

        <!-- 화면 전체에서 중간으로 옮기기 위해 추가 -->
        <div align="center">
            <form action="joinAction" method="post">
                <table style="width: 650px; border-collapse: collapse;
                border-color: rgb(0, 84, 255);" border="1">

                    <th align="center" style="background-color: rgb(178, 204, 255);" colspan="2">회원 기본 정보</th>
                    <tr>
                        <td>아이디:</td>
                        <td><input id="inputId" type="text" name="id" size="15">4~12자의 영문 대소문자와 숫자로만 입력</td>
                    </tr>
                    <tr>
                        <td>비밀번호:</td>
                        <td><input id="inputPw" type="password" name="pw" size="15">4~12자의 영문 대소문자와 숫자로만 입력</td>
                    </tr>
                    <tr>
                        <td>비밀번호확인:</td>
                        <td><input id="inputPwConfirm" type="password" size="15"></td>
                    </tr>
                    <tr>
                        <td>메일주소:</td>
                        <td><input id="inputMail" type="email" size="20" name="mail">예)id@domain.com</td>
                    </tr>
                    <tr>
                        <td>이름:</td>
                        <td><input id="inputName" type="text" size="20" name="name"></td>
                    </tr>
                    <th align="center" style="background-color: rgb(178, 204, 255);" colspan="2">개인 신상 정보</th>
                    <tr>
                        <td>주민등록번호:</td>
                        <td><input id="inputSerial" type="text" size="20" name="serial">예) 1234561234567</td>
                    </tr>
                    <tr>
                        <td>생일:</td>
                        <td><input id="inputYear" type="text" size="20" name="birth">
                    </tr>
                    <tr>
                        <td>관심분야</td>
                        <td>
                            <input type="checkbox" name="hobby" value="computer">컴퓨터
                            <input type="checkbox" name="hobby" value="internet">인터넷
                            <input type="checkbox" name="hobby" value="travel">여행
                            <input type="checkbox" name="hobby" value="movie">영화감상
                            <input type="checkbox" name="hobby" value="music">음악감상
                        </td>
                    </tr>
                    <tr>
                        <td>자기소개</td>
                        <td><textarea id="inputIntro" cols="50" rows="5" name="intro"></textarea></td>
                    </tr>
                    <tr>
                        <td>주소</td>
                        <td>
                            <input type="text" id="member_post" placeholder="우편번호"  readonly>
                            <input type="text" id="member_addr" placeholder="주소" name="address" readonly>
                            <input type="button" value="주소찾기" onclick="findAddr()">
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="회원 가입">
                            <input type="reset" value="다시 입력">
                            <input type="button" value="뒤로가기" onclick="history.back()" >
                        </td>
                    </tr>
                </table>

            </form>
        </div>


</body>
</html>
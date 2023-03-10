<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<%
	String loginSession = null;
	if(session.getAttribute("id") != null){
		loginSession = (String)session.getAttribute("id");
	}
%>
<head>
	<title>MVC 게시판</title>
	<script language="javascript">
	function replyboard(){
		boardform.submit();
	}
	</script>
</head>
<body>
<!-- 게시판 답변 -->
<form action="replyAction" method="post" name="boardform">
<input type="hidden" name="num" value="${boardReply.board_num}">
<input type="hidden" name="re_ref" value="${boardReply.board_re_ref}">
<input type="hidden" name="re_lev" value="${boardReply.board_re_lev}">
<input type="hidden" name="re_seq" value="${boardReply.board_re_seq}">

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
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="subject" type="text" size="50"
				maxlength="100" value="Re: ${boardReply.board_subject}"/>
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
		<td style="font-family:돋음;">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="password" type="password">
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
		<a href="javascript:replyboard()">[등록]</a>&nbsp;&nbsp;
		<a href="javascript:history.go(-1)">[뒤로]</a>
		</td>
	</tr>
</table>
</form>
<!-- 게시판 답변 -->
</body>
</html>
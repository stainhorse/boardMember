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
	<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
	</script>
</head>

<body>


<!-- 게시판 수정 -->
<form action="modifyAction" name="modifyform">
<input type="hidden" name="num" value=${boardModify.board_num}>
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; ">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="subject" size="50" maxlength="100"
				value="${boardModify.board_subject}">
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음;">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="content" cols="67" rows="15">
				${boardModify.board_content}
			</textarea>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</table>
</form>
<!-- 게시판 수정 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구단 관리</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<h1>${message }</h1>
<form action = "Router.do" method = "post" name = "check" enctype = "multipart/form-data">
<table>
	<tr>
		<td>
			<c:choose>
				<c:when test = "${empty sVo.picture }">
					<img src = "upload/no.gif">
				</c:when>
				<c:otherwise>
					<img src = "upload/${sVo.picture }">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>구단명</td>
		<td><input type = "text" name = "teamname" value = "${sVo.teamname }"></td>
	</tr>
	<tr>
		<td>연고지</td>
		<td><input type = "text" name = "country" value = "${sVo.country }"></td>
	</tr>
	<tr>
		<td>홈 구장</td>
		<td><input type = "text" name = "homeground" value = "${sVo.homeground }"></td>
	</tr>
	<tr>
		<td>감독명</td>
		<td><input type = "text" name = "coach" value = "${sVo.coach }"></td>
	</tr>
	<tr>
		<td>선수단 정보(url)</td>
		<td><input type = "text" name = "players" value = "${sVo.players }"></td>
	</tr>
	<tr>
		<td>구단 로고</td>
		<td><input type = "file" name = "picture" value = "${sVo.picture }"></td>
	</tr>
	<tr>
		<td colspan="2"><h5>(이미지 파일은 5MB 이하로 업로드 해주세요.)</h5>
		</td>
	</tr>
</table>
	<input type = "hidden" name = "action" value = "${action }">
	<input type = "hidden" name = "code" value = "${code }">
	<input type = "submit" value = "수정" onclick = "return CreateCheck()">
	<input type = "reset" value = "다시작성">
	<input type = "button" value = "목록" onclick = "location.href='Router.do?action=selectAll'">
</form>

</body>
</html>
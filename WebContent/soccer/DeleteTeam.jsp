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
		<td>${sVo.teamname }</td>
	</tr>
	<tr>
		<td>연고지</td>
		<td>${sVo.country }</td>
	</tr>
	<tr>
		<td>홈 구장</td>
		<td>${sVo.homeground }</td>
	</tr>
	<tr>
		<td>감독명</td>
		<td>${sVo.coach }</td>
	</tr>
	<tr>
		<td>선수단 정보(url)</td>
		<td>${sVo.players }</td>
	</tr>
	<tr>
		<td>구단 로고</td>
		<td>${sVo.picture }</td>
	</tr>
</table>
	<input type = "hidden" name = "action" value = "${action }">
	<input type = "hidden" name = "code" value = "${code }">
	<c:if test = "${choice != null}">
	<input type = "submit" value = "${choice }">
	</c:if>
	<input type = "button" value = "목록" onclick = "location.href='Router.do?action=selectAll'">
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 - 회원가입 페이지</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="top">
		<h1>회원가입</h1>
	</div>
	<form action="join" method="post" name="join_form">
		아이디:<input type="text" name="id"><br/>
		비밀번호:<input type="password" name="pw"><br/>
		이름:<input type="text" name="name"><br/>
		전화번호:
		<select name="phone1">
			<option>010</option>
			<option>02</option>
			<option>031</option>
			<option>051</option>
		</select>
		- <input type="text" name="phone2" size="5">
		- <input type="text" name="phone3" size="5">
		<br/>
		<input type="radio" name="gender" value="m" checked>남자
		<input type="radio" name="gender" value="f">여자
		<input type="submit" value="가입" onclick="join(); return false">
	</form>
</body>
</html>
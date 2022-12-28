<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<div class="title">쇼핑회원 등록</div>
		<form name="frm" action="insert">
			<input type="hidden" id="GUBUN" value="insert">
			<div class="wrapper">
				<table>
					<tr>
						<th>고객이름</th>
						<td><input type="text" name="custname"></td>
					</tr>
					<tr>
						<th>고객전화번호</th>
						<td><input type="text" name="phone"></td>
					</tr>
					<tr>
						<th>고객주소</th>
						<td><input type="text" name="address"></td>
					</tr>
					<%--<tr>
						<th>고객아이디</th>
						<td><input type="text" name="custno" value="<%=%>"
							readonly></td>
					</tr> --%>
					<tr>
						<th>주문일</th>
						<td><input type="text" name="grade"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" onclick="return false;">입력완료</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
</body>
</html>
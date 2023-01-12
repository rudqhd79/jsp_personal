<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DTO.Product"%>
<%@ page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Product> products = new ArrayList<>();
products = (ArrayList<Product>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 - 관리자페이지</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="top">
		<h2>관리자페이지</h2>
	</div>
	<form>
		<table class="manager_table">
			<tr>
				<th class="manager_table_title">상품 이름</th>
				<th class="manager_table_title">상품 가격</th>
				<th class="manager_table_title">상품 코드</th>
			</tr>
				<%
				for (Product p : products) {
				%>
			<tr>
				<th class="manager_th_info"><%=p.getProduct_name()%></th>
				<th class="manager_th_info"><%=p.getProduct_price()%>원</th>
				<th class="manager_th_info"><a href="info?product_id=<%=p.getProduct_id()%>">상품코드 : <%=p.getProduct_id()%> (수정하기)</a></th>
			</tr>
				<%
				}
				%>
		</table>
	</form>
</body>
</html>
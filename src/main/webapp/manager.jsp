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
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="top">
		<h1>관리자페이지</h1>
	</div>
	<form>
		<table>
			<tr>
				<%
				for (Product p : products) {
				%>
				<th><%=p.getProduct_name()%></th>
			</tr>
			<tr>
				<th><%=p.getProduct_price()%></th>
			</tr>
			<tr>
				<th><a href="info?product_id=<%=p.getProduct_id()%>">상품코드 : <%=p.getProduct_id()%> (수정하기)</a></th>
				<%
				}
				%>
			</tr>
		</table>
	</form>
</body>
</html>
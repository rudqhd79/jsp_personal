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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="top">
		<h1>가격조정</h1>
	</div>
	<form action="update" name="modify_frm">
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
				<th><a href="update?product_id=<%=p.getProduct_id()%>">상품코드 : <%=p.getProduct_id()%> (수정하기)</a></th>
				<%
				}
				%>
			</tr>
			<tr>
				<th>상품코드 : <input type="text" name="p_code"
					placeholder="예) 1, 2, 3.." />
				</th>
			</tr>
			<tr>
				<th>가격조정하기 : <input type="text" name="p_code" />
				</th>
			</tr>
		</table>
		<input type="submit" value="수정" />
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="DTO.Product"%>
<%
Product p = new Product();
p = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
<title>마켓컬리 - 쇼핑 페이지 - 가격조정</title>
</head>
<body>
	<%@ include file="header.jsp"%>
		<div class="top">
			<h2>상품수정</h2>
		</div>
	<section>
		<form name="frm" action="update">
			<div>
				<table class="info_table">
					<tr>
						<th class="info_table_title">상품 코드명</th>
						<td><input type="text" name="p_id" value="<%=p.getProduct_id()%>" class="info_table_input"></td>
					</tr>
					<tr>
						<th class="info_table_title">상품명</th>
						<td><input type="text" name="p_name" value="<%=p.getProduct_name()%>" class="info_table_input"></td>
					</tr>
					<tr>
						<th class="info_table_title">상품가격</th>
						<td><input type="text" name="p_price" value="<%=p.getProduct_price()%>" class="info_table_input"></td>
					</tr>
					<tr>
						<th class="info_table_title">상품이미지</th>
						<td><input type="text" name="img_name" value="<%=p.getImg_name()%>" class="info_table_input"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" onclick="fn_submit(); return false;" class="edit_bt">수정하기</button> 
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
</body>
</html>
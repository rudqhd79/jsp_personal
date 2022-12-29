<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Product"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Product> link = new ArrayList<>();
link = (ArrayList<Product>) request.getAttribute("linklist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑페이지</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<!-- 이미지는 for문을 통해 반복하여 출력한다 -->
	<%@ include file="header.jsp"%>
	<table>
		<%
		for (Product i : link) {
		%>
		<tr>
			<td>
				<div class="images">
					<!-- 추가하시겠습니까?? alert 띄우기 -->
					<a href="bucketinsert?product_id=<%=i.getProduct_id()%>" >
					<img src="<%=i.getImg_name()%>" width="200" height="250" />
					</a>
				</div>
				<div>
					<p><%=i.getProduct_name() %></p>
					<p><%=i.getProduct_price() %>원</p>
				</div>
			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
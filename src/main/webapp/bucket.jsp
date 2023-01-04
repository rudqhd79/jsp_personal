<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Bucket"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Bucket> buckets = new ArrayList<>();
buckets = (ArrayList<Bucket>) request.getAttribute("buckets");
Integer total = (Integer) (request.getAttribute("total"));
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 - 장바구니 페이지</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js">
</script>
</head>
<body>
	<form name="frm" action="delete" method="post">
		<!-- 이미지는 for문을 통해 반복하여 출력한다 -->
		<%@ include file="header.jsp"%>
		<div class="top">
			<h1>장바구니</h1>
		</div>
		<table class="bucket_table">
			<tr>
				<td colspan="2">
					<input type="submit" value="삭제" onclick="checkBoxConf(); return false;" class="bucket_top_bt" />
					<input type='button' name='all' value='전체선택' onclick="allselect(this); return false" class="bucket_top_bt" />
					<input type='button' name='reset' value='전체취소' onclick="initCheckbox()" class="bucket_top_bt" />
					<div class="total_price">총 금액 :  <%=total%>원</div>
				</td>
			</tr>
			<%
			for (Bucket b : buckets) {
			%>
			<tr class="bucket_list">
				<td rowspan="2"  class="colunm_bucket"><input type="checkbox" name="chk" value="<%=b.getProduct_id()%>"  class="bucket_chk_bt">
					<div class="images_bucket">
						<img src="<%=b.getImg_name()%>" width="200" height="250" />
					</div>
				</td>
				<td class="bucket_name_price"><%=b.getProduct_name()%> <br /> <%=b.getProduct_price()%>원</td>
			</tr>
			<%
			}
			%>
		</table>
	</form>
</body>
</html>
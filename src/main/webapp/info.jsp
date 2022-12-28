<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="title">홈쇼핑 회원 정보 수정</div>
		<form name="frm" action="update">
			<input type="hidden" id="GUBUN" value="update">
			<div class="wrapper">
				<table>
					<tr>
						<th>회원번호(자동발생)</th>
						<td><img src="" width="200" height="250" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
								onclick="fn_submit(); return false;">수정</button> <!-- onlick의 location은  -->
							<button class="btn" type="button" onclick="location='list'">삭제</button>
						</td>
					</tr>
				</table>
			</div>

		</form>
	</section>
</body>
</html>
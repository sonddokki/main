<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js">
	
</script>

</head>

<body>
	<div id="wrap">

		<!-- header&nav -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/gbc/addList">일반방명록</a></li>
					<li><a href="${pageContext.request.contextPath}/api/addList">ajax방명록</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>ajax방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestbookForm" action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">json등록</button></td>
								</tr>
							</tbody>

						</table>

					</form>

					<button id="btnDataSend">복잡한데이터 전송</button>

					<!-- //guestbook -->
					<div id="gbListArea"></div>
					<!-- 리스트출력 -->
				</div>

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
	<!-- //wrap -->

</body>


<script type="text/javascript">
	// json 형식으로 데이터 전송(요청)
	$("#guestbookForm").on("submit", function(event) {
		console.log("전송버튼 클릭");

		event.preventDefault();

		let name = ("#input-uname").val();
		let pw = ("#input-pass").val();
		let content = ("[name=content]").val();

		let guestbookVo = {
			name : name,
			password : pw,
			content : content
		}
		console.log(guestbookVo);

		$.ajax({

			url : "${pageContext.request.contextPath }/api/add2",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),

			dataType : "json",
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		return false;

	});

	// 복잡한데이터 전송
	$("#btnDataSend").on("click", function() {
		console.log("복잡한 전송");
		
		
		
		
		
	})
</script>



</html>
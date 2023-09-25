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
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>

					</form>

				<!--<button id="btnGetBotton">방명록 보기</button>-->

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
	// DOM이 완성되었을때 -- > 그리기 직전
	$(document).ready(function() {
		//console.log("ready");			
		//fetchList();	
		//console.log("요청후");			
	});

	// 화면을 그리고 난 후
	$(window).load(function() {
		console.log("load");
		fetchList();
		console.log("요청후");
	});

	// 임시버튼을 클릭했을때
	$("#btnGetBotton").on("click", function() {
		//console.log("버튼클릭");			
		//fetchList();		
	});	

	// 서버로부터 방명록 데이타만 받고 싶다.
	function fetchList() {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/list",
			type : "get",
			/* contentType : "application/json", */
			/* data : {name: "홍길동"}, */

			dataType : "json",
			success : function(guestbookList) {
				/*성공시 처리해야될 코드 작성*/
				console.log("json 입장");
				console.log(guestbookList);
				console.log(guestbookList[1].name);

				// 그리기
				for (let i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	// 등록버튼을 클릭했을때
	$("#guestbookForm").on("submit", function(e) {
		console.log("등록버튼클릭");
		e.preventDefault(); // 원래 from과 submit의 기능이 작동하지 않도록 한다
		
		// 데이터 수집
		let guestbookVo = {
			name: $("#input-uname").val(),
			password: $("#input-pass").val(),
			content: $("[name='content']").val()
		}

		console.log(guestbookVo);	
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/add",
			type : "post",
			data : guestbookVo,

			dataType : "json",
			success : function(gVo) {
				/*성공시 처리해야될 코드 작성*/	
				console.log(gVo);
				
				// 그리기
				render(gVo, "up");
				
				// 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	// 방명록 내용을 1개씩 그린다
	function render(guestbookVo, dir) {

		// 리스트 + html 그리기
		let str = '';
		str += ' <table id=t'+ guestbookVo.no +' class="guestRead"> ';
		str += '	<colgroup> ';
		str += '		<col style="width: 10%;"> ';
		str += '		<col style="width: 40%;"> ';
		str += '		<col style="width: 40%;"> ';
		str += '		<col style="width: 10%;"> ';
		str += '	</colgroup> ';
		str += '	<tr> ';
		str += '		<td>' + guestbookVo.no + '</td> ';
		str += '		<td>' + guestbookVo.name + '</td> ';
		str += '		<td>' + guestbookVo.regDate + '</td> ';
		str += '		<td><button class="btnDelForm" data-no='+ guestbookVo.no +'>삭제</button></td> ';
		str += '	</tr> ';
		str += '	<tr> ';
		str += '		<td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td> ';
		str += '	</tr> ';
		str += '</table> ';

		if(dir =="up"){
			$("#gbListArea").prepend(str);
		}else if(dir =="down"){
			$("#gbListArea").append(str);
		}else {
			console.log("잘못입력");
		}
	};
	
	
	
	
	
	// 삭제영역의 버튼 눌렀을때
	$("#gbListArea").on("click", ".btnDelForm" ,function(){
		console.log("삭제버튼 클릭");
		
		let password = prompt("비밀번호를 입력하시오");		
		console.log(password);
		
		// 패스워드, no
		let no = $(this).data("no");
		console.log(no);
		
		// ajax 요청 db에서 지운다
		$.ajax({
			url : "${pageContext.request.contextPath}/api/delete",
			type : "post",
			data : {no: no , password: password},

			dataType : "json",
			success : function() {
				/*성공시 처리해야될 코드 작성*/	
										
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		// 지운뒤 다시 지워진 리스트를 띄운다
		$("#t"+no).remove();		
		
	});
	
	
	
</script>



</html>
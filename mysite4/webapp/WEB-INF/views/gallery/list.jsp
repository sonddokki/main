<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"> </script>

</head>

<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //해더 네비 -->

		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<div id="aside">
				<h2>갤러리</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath }/fileupload/form">첨부파일연습</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery/list">갤러리</a></li>
				</ul>
			</div>
			<!-- //aside -->
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">
				
						
							<button id="btnImgUpload">이미지올리기</button>
							<div class="clear"></div>
	
				
						<ul id="viewArea">
							
							<!-- 이미지반복영역 -->
							<c:forEach items="${galList}" var="galleryVo" varStatus="status">
								<li>
									<div class="view" >
										<img class="imgItem" src="">
										<div class="imgWriter">작성자: <strong>${galleryVo.userName}</strong></div>
									</div>
								</li>
							</c:forEach>
							<!-- 이미지반복영역 -->
							
							
						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->



<!-- 이미지등록 팝업(모달)창 -->
<div class="modal fade" id="addModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지등록</h4>
			</div>
			
			<form method="" action="" >
				<div class="modal-body">
					<div class="form-group">
						<label class="form-text">글작성</label>
						<input id="addModalContent" type="text" name="content" value="" >
					</div>
					<div class="form-group">
						<label class="form-text">이미지선택</label>
						<input id="file" type="file" name="image" value="" >
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn" id="btnUpload">등록</button>
				</div>
			</form>
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- 이미지보기 팝업(모달)창 -->
<div class="modal fade" id="viewModal">
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지보기</h4>
			</div>
			<div class="modal-body">
				
				<div class="formgroup" >
					<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				
				<div class="formgroup">
					<p id="viewModelContent"></p>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->	




</body>

<script type="text/javascript"> 
// 이미지업로드 모달창 버튼 클릭했을때
	$("#btnImgUpload").on("click", function() {
		console.log("모달창 호출");
		
		// 열기
		$("#addModal").modal("show");
		
		// 닫기
		//$("#addModal").modal("hide");		
		
	});


</script>

</html>



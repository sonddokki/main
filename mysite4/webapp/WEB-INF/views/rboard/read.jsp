<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header&nav -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/brc/list">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/rbrc/list">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="read">
						<form action="${pageContext.request.contextPath}/rbrc/writeForm" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span> <span class="form-value">${boardRead.name}</span>
							</div>

							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span> <span class="form-value">${boardRead.hit}</span>
							</div>

							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span> <span class="form-value">${boardRead.regDate}</span>
							</div>

							<!-- 제목 -->
							<div class="form-group">
								<span class="form-text">제 목</span> <span class="form-value">${boardRead.title}</span>
							</div>

							<!-- 내용 -->
							<div id="txt-content">
								<span class="form-value"> ${boardRead.content} </span>
							</div>
							
							<td><input style="width: 20px; height: 20px" type="hidden" id="txt-title" name="groupNo" value="${boardRead.groupNo}"></td>
							<td><input style="width: 20px; height: 20px" type="hidden" id="txt-title" name="orderNo" value="${boardRead.orderNo}"></td>
							<td><input style="width: 20px; height: 20px" type="hidden" id="txt-title" name="depth" value="${boardRead.depth}"></td>

							<a id="btn_modify" href="${pageContext.request.contextPath}/rbrc/list">목록</a>

							<c:if test="${!(empty authUser)}">
								<button id="btn_modify">댓글</button>
							</c:if>

							<c:if test="${boardRead.userNo == authUser.no}">
								<a id="btn_modify" href="${pageContext.request.contextPath}/rbrc/modifyForm?no=${boardRead.no}">수정</a>
							</c:if>

						</form>
						<!-- //form -->
					</div>
					<!-- //read -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<!-- //footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
	<!-- //wrap -->

</body>

</html>

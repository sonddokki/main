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
					<li><a href="${pageContext.request.contextPath}/brc/list3">페이징게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/brc/list4">검색게시판</a></li>
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
					<div id="list">
						<form action="list4" method="get">
							<div class="form-group text-right">
								<input type="text" name="search" value="">
								<button type="submit" id=btn_search >검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>							
									
							<tbody>
							<c:forEach items="${pMap.pList}" var="boardVo" varStatus="status">
								<tr>
									<td>${boardVo.no}</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/brc/read?no=${boardVo.no}&hit=1">${boardVo.title}</a></td>
									<td>${boardVo.name}</td>
									<td>${boardVo.hit}</td>
									<td>${boardVo.regDate}</td>
									
									<c:if test="${boardVo.userNo == authUser.no}" >
									<td><a href="${pageContext.request.contextPath}/brc/delete?userNo=${boardVo.userNo}&no=${boardVo.no}">[삭제]</a></td>
									</c:if>							
									
								</tr>
							</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${pMap.prev}">
									<li><a href="${pageContext.request.contextPath}/brc/list4?crtPage=${pMap.startPageBtnNo-1}">◀</a></li>
								</c:if>
									<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
										<c:choose>
											<c:when test="${param.crtPage == page}">
												<li class="active"><a href="">${page}</a></li>
											</c:when>
											<c:otherwise>
												<li class=""><a href="${pageContext.request.contextPath}/brc/list4?crtPage=${page}">${page}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
								<c:if test="${pMap.next}">
									<li><a href="${pageContext.request.contextPath}/brc/list4?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${!(empty authUser)}">
						<a id="btn_write" href="${pageContext.request.contextPath}/brc/writeForm">글쓰기</a>
						</c:if>
					</div>
					<!-- //list -->
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

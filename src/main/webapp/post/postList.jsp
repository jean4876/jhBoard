<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>
<%@ include file="/commonJsp/basicLib.jsp"%>
<script>
	// 문서 로딩이 완료되고 나서
	$(document).ready(function() {
		// 사용자 정보 클릭시 이벤트 핸들러
		$(".postTr").on('click', function() {
			var dataValue = $(this).data("postid");
			$('#postId').val(dataValue);
			$('#frm').submit();
		});
	})
</script>

</head>

<body>
	<!-- header -->
	<%@ include file="/commonJsp/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<%@ include file="/commonJsp/left.jsp"%>
			</div>
	<form id="frm" action="${cp }/post" method="get">
		<input type="hidden" id="postId" name="postId" />
	</form>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


		<div class="row">
			<div class="col-sm-8 blog-main">
				<h2 class="sub-header">${board.boardNm}</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>게시글 번호</th>
							<th>제목</th>
							<th>작성자 아이디</th>
							<th>작성일시</th>
						</tr>

						<c:forEach items="${postList}" var="post">
							<tr class="postTr" data-postid="${post.postId}">
								<td>${post.postId}</td>
								<td>${post.postTitle}</td>
								<td>${post.userId}</td>
								<td>${post.postReg_dt}</td>

							</tr>
						</c:forEach>

					</table>
				</div>

				<div class="text-center">
					<ul class="pagination">
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
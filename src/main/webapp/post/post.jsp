<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="kor">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon -->
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basic</title>
<%@ include file="/commonJsp/basicLib.jsp"%>
</head>
<!--header  -->
<%@include file="/commonJsp/header.jsp"%>
<body>

   <div class="container-fluid">
      <div class="row">

         <div class="col-sm-3 col-md-2 sidebar">
            <!--left  -->
            <%@ include file="/commonJsp/left.jsp"%>
         </div>

         <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <form id ="frm" class="form-horizontal" role="form" action="${cp }/modifyPost" method="post" enctype="multipart/form-data">
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">제목 :</label>
                  <div class="col-sm-6">
                  <label class="control-label">${post.postTitle }</label>
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">글 내용 :</label>
                  <div class="col-sm-10">
                  <label class="control-label">${post.postContent }</label>

                  </div>
               </div>
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">첨부 파일 :</label>
                  <div class="col-sm-10">
                  <label class="control-label">첨부파일이 올 자리예용</label>
               </div>
               <input type="hidden" name="boardId" value="${post.boardId }">
               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                  <a href="${cp }/modifyPost?postId=${post.postId}" class="btn btn-default">글 수정</a>
                  <a href="${cp }/deletePost?postId=${post.postId}" class="btn btn-default">글 삭제</a>
                  <a href="${cp }/replyPost?postId=${post.postId}" class="btn btn-default">답글 쓰기</a>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</body>
</html>
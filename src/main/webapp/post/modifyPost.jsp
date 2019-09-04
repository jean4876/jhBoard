<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basic</title>
<%@ include file="/commonJsp/basicLib.jsp"%>
<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function(){

	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,
		}
	});
	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("수정하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
		$("#picture").change(function(){

		var fileInput = document.getElementById("picture");
		var files = fileInput.files;
		var uploadedFile = $(".deleteFile").length;




		if((files.length +uploadedFile) > 5){
			alert("파일은 5개까지 올릴수 있습니다.");
			$('#picture').val("");
		}
	})
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음.
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}
	return true;
}

function spanClick(fileId) {
	   var data = fileId
	   $('#uploadFile').val(data);
	   $('#deleteFileForm').submit();
}

</script>

</head>
<!--header  -->
<%@include file="/commonJsp/header.jsp"%>
<body>
	<form id="deleteFileForm" class="form-horizontal" role="form"
		action="${cp }/deleteFile" method="get">
		<input type="hidden" id="uploadFile" name="uploadFile"> <input
			type="hidden" id="postId" name="postId" value="${post.postId }">
	</form>


	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!--left  -->
				<%@ include file="/commonJsp/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<form id="frm" class="form-horizontal" role="form"
					action="${cp }/modifyPost" method="post"
					enctype="multipart/form-data">
					<h1>&nbsp;&nbsp;&nbsp;&nbsp;게시글 수정</h1>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="postTitle"
								name="postTitle" placeholder="제목" value="${post.postTitle }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">글 내용</label>
						<div class="col-sm-10">
							<textarea name="smarteditor" id="smarteditor" rows="10"
								cols="100" style="width: 766px; height: 412px;">
						${post.postContent }
					</textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-6">
							<c:forEach items="${uploadFileList}" var="uploadFile">
								<label class="control-label">${uploadFile.uploadFileNm}
									<%-- data-fileId ="${uploadFile.uploadId}" --%> <span
									onclick="spanClick(${uploadFile.uploadId})"
									class="deleteFile glyphicon glyphicon-remove"></span>
								</label>
							</c:forEach>
							<input type="file" maxlength="5" multiple="multiple"
								class="form-control" id="picture" name="picture">
						</div>
					</div>
					<input type="hidden" name="postId" value="${post.postId }">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" id="savebutton" value="수정완료" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
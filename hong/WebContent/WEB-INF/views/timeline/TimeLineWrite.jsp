<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="TimeLineAdd.tc" method="post" name="timeform">
			<input name="content_eng" type="text" value="" id="content_eng" class="today-input-en" placeholder="영문을 입력해 주세요"></input>
			<input name="content_kor" type="text" value="" id="content_kor" class="today-input-ko" placeholder="한글을 입력해 주세요"/>
		<!-- <input name="timelinenumber" type="text" value="" id="timelinenumber"/> -->
		<div class="today-btn-wrap">
		<a href="javascript:addtimeline()" class="enroll-btn">등록</a>
		<a href="javascript:history.go(-1)" class="cancel-btn">취소</a>
		</div>
	</form>
	
	<script type="text/javascript">
		function addtimeline(){
			timeform.submit();
		};
	</script>
</body>
</html>
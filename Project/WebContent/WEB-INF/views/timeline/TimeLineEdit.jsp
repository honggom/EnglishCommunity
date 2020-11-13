<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function update(){
		timeform.submit();
	};
</script>
</head>
<body>
	<c:set var="timeline" value="${requestScope.TimeLine}" ></c:set>
	
	<form action="TimeLineUpdate.tc" method="post" name="timeform">
	<div class="card-content">
		<table>
			<tr>
			<td>번호</td>
			<td><input name="timeLineNumber" type="text" value="${timeline.timeLineNumber}" id="timeLineNumber" readonly /></td>
			</tr>
			<tr>
			<td>영문</td>
			<td><input name="content_eng" type="text" value="" id="content_eng" /></td>
			</tr>
			<tr>
			<td>한글</td>
			<td><input style="width:1500px; height: 30px; padding:0px;" name="content_kor" type="text" value="" id="content_kor"/></td>
			</tr>
		</table>
		
		<a href="javascript:update()">[등록]</a>
		<a href="javascript:history.go(-1)">[취소]</a>
	</div>
	</form>	
</body>
</html>
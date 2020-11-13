<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 스크립트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>

<c:choose>
	<c:when test="${not empty sessionScope.userid}"><!-- 로그인 중 -->
		<script src="assets/js/logOut.js"></script>
	</c:when>
	<c:otherwise><!-- 로그인중이 아닐 때 -->
		<script src="assets/js/index.js"></script>
	</c:otherwise>
</c:choose>
<!-- //스크립트 -->

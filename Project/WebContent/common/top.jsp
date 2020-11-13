<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상위바 -->
<div class="top-menu">
	<!-- 좌측 네비게이션 -->
	<ul class="navbar-nav">
		<!-- 햄버거 버튼 -->
		<li class="nav-item">
			<a class="nav-link">
				<i class="fas fa-bars" onclick="collapseSidebar()"></i>
			</a>
		</li>
		<!-- //햄버거 버튼 -->
		<!-- 로고 -->
		<li class="nav-item">
			<a href="./Main.go">
				<img src="assets/img/logo.png" alt="ENGcom logo" class="logo">
			</a>
		</li>
		
		<!-- //로고 -->
	</ul>
	<!-- //좌측 네비게이션 -->

	<!-- 우측 네비게이션 -->
	<ul  class="navbar-nav nav-right" >
		<!-- 로그인 -->
		<c:if test="${not empty sessionScope.userid}">
			<p>${sessionScope.userid}님 환영합니다.</p>
		</c:if>
		<div class="avt dropdown" >
			<c:choose>
				<c:when test="${not empty sessionScope.userid}">
					
					<img src="assets/img/user.png" alt="User image" class="login" data-toggle="user-menu">
				</c:when>
				<c:otherwise>
					<img src="assets/img/user.png" alt="User image" class="trigger" data-toggle="user-menu">
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty sessionScope.userid}">
			
				<ul id="user-menu" class="dropdown-menu">
					<li  class="dropdown-menu-item">
						<a href="LogOut.go" class="dropdown-menu-link">
							<div>
								<i class="fas fa-sign-out-alt"></i>
							</div>
							<span>LogOut</span>
						</a>
					</li>
				</ul>
				
			</c:if>
		</div>
		<!-- //로그인 -->
	</ul>
	<!-- //우측 네비게이션 -->
</div>
<!-- //상위바 -->
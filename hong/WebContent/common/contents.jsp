<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 컨텐츠 -->
<div class="wrapper">
	<!-- 오늘의 회화 -->
	<div class="row">
		<div class="col-13 col-m-12 col-sm-12">
			<div class="card">
				<div class="card-header">
					<h1>오늘의 회화</h1>
					<!-- 오늘의 회화 드롭다운 메뉴 -->
						<c:if test="${sessionScope.userid == 'admin' }">
						<div id="today-dropdown">
							<div class="today-menu">
								<i class="fas fa-ellipsis-h" id="todayicon"></i>
							</div>
							<div class="dropdown" style="display: none">
								<ul>
									<li class="today-link"><a href="#" class="fas fa-pencil-alt" id="contentWrite">&nbsp&nbsp글쓰기</i></a></li>
									<li class="today-link"><a href="#" class="fas fa-clipboard-list" id="contentList">&nbsp&nbsp목록보기</a></li>
									<li class="today-link"><a href="#" class="fas fa-clipboard-list" id="contentMain">&nbsp&nbsp돌아가기</a></li>
								</ul>
							</div>							
						</div>
						</c:if>
						<!-- //오늘의 회화 드롭다운 메뉴 -->
					
				</div>
				<div class="card-content" id="TimeLine">
					<div class="card-content" id="TimeLineContent">
						<!-- <h3>Nothing can be so amusingly arrogant as a young man who has just discovered an old idea and thinks it is his own.</h3>
						<p class="todayenglishko">"오래된 생각을 이제 막 발견하고 그것이 자기 것이라고 생각하는 젊은 사내만큼 유쾌하게 거만한 존재는 없다."</p> -->
					</div>
					</div>
			</div>
		</div>
	</div>
	<!-- //오늘의 회화 -->

	<!-- 카테고리 바로가기 -->
	<div class="row">
		<div class="col-4 col-m-6 col-sm-6">
			<div class="card">
				<a href="./BoardList_Grammar.cate">
					<div class="counter diary">
						<p><i class="fas fa-spell-check"></i></p>
						<h3>Grammar</h3>
						<p>영어 문법 공부하기</p>
					</div>
				</a>
			</div>
		</div>

		<div class="col-4 col-m-6 col-sm-6">
			<div class="card">
				<a href="./BoardList_Reading.cate">
					<div class="counter certification">
						<p><i class="fas fa-book-reader"></i></p>
						<h3>Reading</h3>
						<p>영어 리빙 공부하기</p>
					</div>
				</a>
			</div>
		</div>

		<div class="col-4 col-m-12 col-sm-12">
			<div class="card">
				<a href="./BoardList_Listening.cate">
					<div class="counter qna">
						<p><i class="fas fa-comment-dots"></i></p>
						<h3>Listening</h3>
						<p>영어 발음 공부하기</p>
					</div>
				</a>
			</div>
		</div>

	</div>
	<!-- //카테고리 바로가기 -->

	<!-- 전체 게시판 -->
	<div class="row">
		<div class="col-8 col-m-12 col-sm-12">
			<div class="card">
				<div class="card-header">
					<h1>전체게시판</h1>
					<a href="./BoardList.bo">
						<i class="fas fa-ellipsis-h"></i>
					</a>

				</div>
				<div class="card-content">
					<div class="main-board ">
						<table>
							<thead>
								<tr>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="board" items="${requestScope.boardlist}"
									varStatus="status">
									<tr>
										<td class="leftAlign">
											<c:choose>
												<c:when test="${board.depth != 0}">
													<c:forEach var="depth" begin="0" end="${(board.depth)*2}">
														&nbsp;
													</c:forEach>
														▶
												</c:when>
												<c:otherwise>
														
												</c:otherwise>
											</c:choose>
											<a href="./BoardDetailService.bo?num=${board.contentNumber}">
												${board.contentTitle}
											</a>
										</td>
										<td>${board.id}</td>
										<td>${board.reportingDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> 
				</div>
			</div>
		</div>
		<!-- //전체 게시판 -->

		<!-- 통계 -->
		<div class="col-4 col-m-12 col-sm-12">
			<div class="card">
					<jsp:include page="/common/chart.jsp"></jsp:include>
			</div>
		</div>
		<!-- //통계 -->
	</div>
	
	<div class="row">
		<!-- 날씨 -->
		<div class="col-4 col-m-12 col-sm-12">
			<div class="card">
				<div class="card-header">
					<h1>날씨</h1>
					<i class="fas fa-ellipsis-h"></i>
				</div>
	
				<div class="card-content">
				</div>
				</div>
			</div>
			<!-- //날씨 -->
			
			<!-- 오늘의 단어퀴즈 -->
			<div class="col-8 col-m-12 col-sm-12">
				<div class="card">
					<div class="card-header" >
						<h1>
							오늘의 단어퀴즈
						</h1>
						<i class="fas fa-ellipsis-h" ></i>
						
					</div>
					<div class="card-content">
					<div id="game"></div>
				</div>
			</div>
		</div>
		<!-- //오늘의 단어퀴즈 -->
	</div>
	<!-- //컨텐츠 -->
</div>

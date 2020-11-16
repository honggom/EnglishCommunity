<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>MVC 게시판</title>
<jsp:include page="/common/summernote.jsp"></jsp:include>
<script language="javascript">
	function replyboard() {
		boardform.submit();
	}
	$(function(){
	    $('#summernote').summernote({
	        placeholder: '내용을 입력하세요',
	        tabsize: 2,
	        height: 120,
	        toolbar: [
					['style', ['style']
				],
				['font', 
					['bold', 'underline', 'clear']
				],
		        ['color', 
		        	['color']
		        ],
		        ['para', 
		        	['ul', 'ol', 'paragraph']
		        ],
		        ['table', 
		        	['table']
		        ],
		        ['insert', 
		        	['link', 'picture', 'video']
		        ],
		        ['view', 
		        	['fullscreen', 'codeview', 'help']
		        ]
	        ]
	      });
	});
</script>
</head>

<body class="wrap">
	<jsp:include page="/common/top.jsp"></jsp:include>
	<jsp:include page="/common/left.jsp"></jsp:include>
	<jsp:include page="/common/login.jsp"></jsp:include>
	<c:set var="userid" value="${sessionScope.userid}"/>
	<c:set var="board" value="${requestScope.boarddata}"/>
	<!-- 컨텐츠 -->
	<div class="wrapper">
		<!-- 전체 게시판 -->
		<div class="row">
			<div class="col-13 col-m-12 col-sm-12">
				<div class="card">
					<div class="card-header">
						<h1>글쓰기</h1>
						<i class="fas fa-ellipsis-h"></i>
					</div>
					<div class="card-content">
						<!-- 게시판 답변 -->
						<form action="./BoardReplyService.bo" method="post" name="boardform">
							<input type="hidden" name="contentNumber" value="${board.contentNumber}">
							<input type="hidden" name="boardName" value="${board.boardName}"> 
							<input type="hidden" name="refer" value="${board.refer}">
							<input type="hidden" name="depth" value="${board.depth}"> 
							<input type="hidden" name="step" value="${board.step}"> 
							<input type="hidden" name="id" value="${userid}">

							<table cellpadding="0" cellspacing="0">
								<tr align="center" valign="middle">
									<td colspan="5"></td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">글쓴이</div>
									</td>
									<td class="leftAlign">${userid}</td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">카테고리</div>
									</td>
									<td class="leftAlign">${board.boardName}</td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">제 목</div>
									</td>
									<td class="leftAlign">
										<input name="contentTitle" type="text" size="50"
										maxlength="100" style="width:100%;" value="Re: ${board.contentTitle}" />
									</td>
								</tr>
								<tr>
									<td class="name" style="font-family: 돋음; font-size: 12">
										<div align="center">내 용</div>
									</td>
									<td class="leftAlign">
									<textarea id="summernote" name="content"></textarea>
									</td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12">
										<div align="center">파일 첨부</div>
									</td>
									<td><input name="filename" type="file" /></td>
									
								</tr>
								<!-- <tr>
									<td style="font-family: 돋음; font-size: 12">
										<div align="center">비밀번호</div>
									</td>
									<td><input name="BOARD_PASS" type="password"></td>
								</tr> -->

								<tr bgcolor="cccccc">
									<td colspan="2" style="height: 1px;"></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>

								<tr align="center" valign="middle">
									<td colspan="5"><a href="javascript:replyboard()">[등록]</a>&nbsp;&nbsp;
										<a href="javascript:history.go(-1)">[뒤로]</a></td>
								</tr>

							</table>
						</form>
						<!-- 게시판 답변 -->
					</div>
				</div>
			</div>
			<!-- //전체 게시판 -->
		</div>
		<!-- //컨텐츠 -->
	</div>

		<jsp:include page="/common/footer.jsp"></jsp:include>
		<jsp:include page="/common/script.jsp"></jsp:include>
</body>
</html>
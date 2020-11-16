<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/common/summernote.jsp"></jsp:include>
<script language="javascript">
	function addboard() {
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

						<form action="./BoardModifyService.bo" method="post"
							enctype="multipart/form-data" name="boardform">
							<input type="hidden" name="id" value="${userid}">
							<input type="hidden" name="contentNumber" value="${board.contentNumber}">
							<table>
							
								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">글쓴이</div>
									</td>
									<td style="text-align:left; padding-left:20px;">${userid}</td>
								</tr>

								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">게시판 종류</div>
									</td>
									<td class="leftAlign">
									<!--<select name=boardCode>
									    <option value="1" selected>Grammer</option>
									    <option value="2">Reading</option>
									    <option value="3" >Listening</option>
									</select> -->
									${board.boardName}
									</td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12" height="16">
										<div align="center">제 목</div>
									</td>
									<td><input name="contentTitle" type="text" size="50"
										maxlength="100" value="${board.contentTitle}" style="width:100%;" /></td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12">
										<div align="center">내 용</div>
									</td>
									<td style="text-align:left;">
									<textarea id="summernote" name="content" >${board.content}</textarea>
									</td>
								</tr>
								<tr>
									<td style="font-family: 돋음; font-size: 12">
										<div align="center">파일 첨부</div>
									</td>
									<td class="leftAlign" style="font-family: 돋음; font-size: 12">
										<input name="filename" type="file" />
										<c:choose>
											<c:when test="${board.filename != null}">
												<a href="download.jsp?file_name=${board.filename}">
													${board.filename} </a>
											</c:when>
										</c:choose>
									</td>
									
								</tr>
								
								<tr bgcolor="cccccc">
									<td colspan="2" style="height: 1px;"></td>
									
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr align="center" valign="middle">
									<td colspan="5"><a href="javascript:addboard()">[수정]</a>&nbsp;&nbsp;
										<a href="javascript:history.go(-1)">[뒤로]</a></td>
								</tr>
							</table>
						</form>
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
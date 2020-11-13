<%@page import="java.util.List"%>
<%@page import="kr.co.engcom.dto.CMBoardDTO"%>
<%@page import="kr.co.engcom.dto.CMReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
    CMBoardDTO board = (CMBoardDTO)request.getAttribute("CMBoardDTO");//CM글 객체
	List reply = (List)request.getAttribute("replyList");//댓글 객체
	String msg = (String)session.getAttribute("userid"); //현재 로그인 한 사용자 아이디 
	boolean check = (boolean)request.getAttribute("check");//정답 확인 boolean 만약에  정답이 해당 게시물에 있으면 true 없으면 false
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function(){
	let count = 0;
	if(<%=reply.size()%>>15){
		 count = 15;
	}
	console.log(count);
	$("#moreBtn").click(function() {
							count += 15;
							console.log(count)
								$.ajax({
									url : "GetMoreReplyServlet",
									async: false,
									type : "POST",
									dataType : "json",
									data : {
										data : count,
										CMBoardNumber : <%=board.getCMBoardNumber()%>
										},
										success : function(responsedata) {
										console.log(responsedata);
										$.each(responsedata,function(index,reply){
											$("#tb").append(
												"<tr><td>"+reply.CMReplyId+"</td><td>"+reply.CMReplyContent+"</td><td>"
												+reply.CMReplyStringDate+ "</td></tr>"
												//for문 돌면서 append 해주고 
												//false를 만나면 버튼을 지워줌 	
											);
										});
										$.ajax({
											url : "HaveMoreReplyServlet",
											async: false,
											type : "POST",
											dataType : "html",
											data : {
												data : count,
												CMBoardNumber : <%=board.getCMBoardNumber()%>
												},
												success : function(responsedata) {
													console.log(responsedata);
												if(responsedata==="false"){
													//responsedata가 false면 더이상 받을 댓글이 없다는 뜻이므로 
													//더보기 버튼을 지워준다
													$("#moreBtn").hide();
												}
												}
										}); 
										}
								});
						});
				});

				function check(){
					let answer = document.getElementById("hi");
					console.log(answer);
					
					if(answer.value===""){
						alert("정답을 입력하세요");
						return false;
					}
}
</script>
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="wrap">
<jsp:include page="/common/top.jsp"></jsp:include>
<jsp:include page="/common/left.jsp"></jsp:include>
<jsp:include page="/common/login.jsp"></jsp:include>
<!-- 
		[글 제목] [            ] 
		[그림]   --------------------------
		 	   |						 |
		 	   |						 |
		 	   |						 |
		 	   |						 |
		 	   |						 |
			   |						 |
			   |						 |
			   |						 |
			   |						 |
			   ---------------------------
									[글 삭제] (만약 글 ID가 작성자 ID와 동일하면 보여주고 삭제 가능)
									
		댓글 --------------------------------------
		
		[댓글 작성자] 	[댓글 내용]		[댓글 작성일]
		cavok699	마카롱		2020-12-31 14:33  		
		sehoo		기린빵		2020-12-31 12:11	
		seeya		붕어빵		2020-12-31 11:59	

 -->
 <div class="wrapper">
 <div class="row">
 <div>
 <div class="card">
 [글 번호]<%=board.getCMBoardNumber() %><br>
 [글 제목]<%=board.getCMBoardSubject() %><br>
 [글 작성일]<%=board.getCMBoardDate() %><br>
 [작성자 ID]<%=board.getCMBoardId() %><br>
 
 <img src = "<%=board.getCMBoardFile() %>" id = "img">
 </div>
 </div>
 <div class="col-6 col-m-6 col-sm-6">
 <div class="card">
 
 <!-- 현재 글에 정답이 있으면 댓글 작성 가능 없으면 불가능 -->
 <c:set var = "check" value = "<%=check%>"/>
 <c:if test="${!check}">
 <form id = "checkForm" action="<%=request.getContextPath()%>/replyWrite.cm" method="post" onsubmit ="return check()">
 [정답] : <input type = "text" name = "answer" id ="hi">
 		 <!-- 댓글 정보를 보내기 위한 hidden -->
 		 <input type = "hidden" name ="CMReplyId" value="<%=msg%>">
 		 <input type = "hidden" name ="CMBoardNumber" value="<%=board.getCMBoardNumber()%>">
 		 <input type = "submit" value = "작성">
 </form>
 </c:if>
 
 <c:set var = "sessionId" value = "<%=msg%>"/>
 <c:set var = "userid" value = "<%=board.getCMBoardId() %>"/>
 
 <!-- 현재 로그인한 사용자 ID와 해당 글 작성자 ID와 일치하면 삭제 버튼 생성 -->
 <c:if test="${sessionId == userid}">
 <form action="<%=request.getContextPath()%>/CMBoardDeleteCheck.cm" method="post">
 <input type = "hidden" name ="CMBoardNumber" value="<%=board.getCMBoardNumber()%>">
 <input type = "submit" value ="글 삭제">
 </form>
 </c:if>
 <hr>
 <br>
 <table id ="tb">
 <tr>
 <td>
 [작성자]
 </td>
 <td>
 [내용]      
 </td>
 <td>
 [작성 시간]
 </td>
 </tr>
 <!-- 여기서 for 문 -->
 <c:set var ="replyList" value="<%=reply%>"/>
 <c:forEach var="list" items="<%=reply%>" varStatus="status" begin="0" end="14" step="1">
 <c:choose>
 <c:when test="${status.index==0 and check}">
 <tr bgcolor="yellow">
 <td>
 <!-- 정답구역 -->
 ${list.CMReplyId}
 </td>
 <td>
 ${list.CMReplyContent}
 </td>
 <td>
 ${list.CMReplyDate}
 <!-- 정답구역 -->
 </td>
 </tr>
 </c:when>	
 			  		
 <c:otherwise>
 <tr>
 <td>
 <!-- 여기서 ID -->
 ${list.CMReplyId}
 </td>
 <td>
 <!-- 여기서 댓글 내용 -->
 ${list.CMReplyContent}
 </td>
 <td>
 <!-- 여기서 작성 시간 -->
 ${list.CMReplyDate}
 </td>
 </tr>
 </c:otherwise>
 </c:choose>
 </c:forEach>
 </table>
 <table>
 <c:if test="${fn:length(replyList) > 15}">
 <tr>
 <td>
 </td>
 <td>
 <!-- 비동기 버튼 -->
 <input type = "button" value ="더보기" id ="moreBtn">
 </td>
 <td>
 </td>
 </tr>
 </c:if>
 </table>
 </div>
 </div>
 </div>
 </div>
<jsp:include page="/common/footer.jsp"></jsp:include>	
<jsp:include page="/common/script.jsp"></jsp:include>	 
</body>
</html>
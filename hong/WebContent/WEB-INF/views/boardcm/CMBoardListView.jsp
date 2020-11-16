<%@page import="kr.co.engcom.dto.CMBoardDTO"%>
<%@page import="kr.co.engcom.dto.CMBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String id=null;
	if(session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
	}
	List CMBoardList = (List)request.getAttribute("CMBoardList");
	int listcount = ((Integer)request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer)request.getAttribute("page")).intValue();
	int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer)request.getAttribute("startpage")).intValue();
	int endpage = ((Integer)request.getAttribute("endpage")).intValue();
%>
<html>
<head>
	<title>게시판</title>
	<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="wrap">

		<jsp:include page="/common/top.jsp"></jsp:include>
		<jsp:include page="/common/left.jsp"></jsp:include>
		<jsp:include page="/common/login.jsp"></jsp:include>
		
		<div class="wrapper">
 		<div class="row">
 		<div class="card">

<table width=570 border="0" cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="4">CM 게시판</td>
		<td align=right>
			<font size=2>글 개수 : ${listcount}</font>
		</td>
	</tr>
	<tr align="center" valign="middle" bordercolor="#333333">
		<td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26">
			<div align="center">번호</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="50%">
			<div align="center">제목</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="14%">
			<div align="center">작성자</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="17%">
			<div align="center">날짜</div>
		</td>
	</tr>
	<%
		for(int i=0; i<CMBoardList.size(); i++){
			CMBoardDTO bl= (CMBoardDTO)CMBoardList.get(i);
	%>
	<tr align="center" valign="middle" bordercolor="#333333"
		onmouseover="this.style.backgroundColor='F8F8F8'"
		onmouseout="this.style.backgroundColor=''">
		<td height="23" style="font-family:Tahoma;font-size:10pt;">
			<%=bl.getCMBoardNumber()%>
		</td>
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="left">
			<a href="./CMBoardDetailService.cm?num=<%=bl.getCMBoardNumber()%>">
				<%=bl.getCMBoardSubject()%>
			</a>
			</div>
		</td>
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center"><%=bl.getCMBoardId() %></div>
		</td>
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center"><%=bl.getCMBoardDate() %></div>
		</td>	
	</tr>
	<%} %>
	<tr align=center height=20>
		<td colspan=7 style=font-family:Tahoma;font-size:10pt;>
			<%if(nowpage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="./list.cm?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
			<%} %>
			
			<%for(int a=startpage;a<=endpage;a++){
				if(a==nowpage){%>
				[<%=a %>]
				<%}else{ %>
				<a href="./list.cm?page=<%=a %>">[<%=a %>]</a>
				&nbsp;
				<%} %>
			<%} %>
			
			<%if(nowpage>=maxpage){ %>
			[다음]
			<%}else{ %>
			<a href="./list.cm?page=<%=nowpage+1 %>">[다음]</a>
			<%} %>
		</td>
	</tr>
	<tr align="right">
		<td colspan="5">
	   		<a href="writeView.cm">[글쓰기]</a>
		</td>
	</tr>
</table>
		</div>
 		</div>
 		</div>
		<jsp:include page="/common/footer.jsp"></jsp:include>	
		<jsp:include page="/common/script.jsp"></jsp:include>	
		
</body>
</html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<section class="panel">				
		<div class="table-responsive">
			<table class="table">
				<tbody>
					
				
				 	<c:forEach var="timeline" items="${requestScope.timelinelist}" varStatus="status">
		
						<c:if test="${status.last}">
						<%-- <c:choose>
						<c:when test="${status.last}"><input type="button" id="prev" value="이전" style="float:left"></c:when>						
						<c:when test="${!status.last}"><input type="button" id="prev" value="이전" style="float:left">
													   <input type="button" id="next" value="다음" style="float:right"></c:when>
						
						<c:when test="${status.first}"><input type="button" id="next" value="다음" style="float:right"></c:when>
						</c:choose> --%>
					<tr>										
						<td><h3 style="margin:0px">${timeline.content_eng}</h3></td>
					</tr>
					<tr>
						<td><b>${timeline.content_kor}</b></td>						
					</tr>										
					</c:if>				 	
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</section>

</body>
</html>









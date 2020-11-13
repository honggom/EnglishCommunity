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
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	 -->
<script type="text/javascript">
var a = $(".Up").val();

$(function(){
$('.Update').click(function(){
	console.log($(this).val())
$.ajax({
    url : "TimeLineEdit.tc",
    dataType : "html",
    type : "post",  // post 또는 get
    data : { timeLineNumber:$(this).val() }, 
    success : function(result){
        $("#Edit").html(result);
    }
});	
});
});

</script>

</head>
<body>


	<c:set var="timelinelist" value="${requestScope.timelinelist}" />
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="currpage" value="${requestScope.currpage}" />

	<div class="wrapper">
		<!-- 오늘의 회화 -->
		<div class="row" id="Edit">
			<div class="form-group" id="List">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						<label for="" style="margin-bottom: 0">출력 개수 설정</label>
						<div class="form-group d-flex align-items-center">
							<div class="col-sm-2" style="padding-left: 0">
								<form name="list">
									<!-- form = list -->
									<!-- select -->
									<select name="ps" class="form-control" onchange="submit()">
										<c:forEach var="count" begin="5" end="20" step="5">
											<c:choose>
												<c:when test="${pagesize == count}">
													<option value="${count}" selected>${count}</option>
												</c:when>
												<c:otherwise>
													<option value="${count}">${count}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<section class="panel">
								<div class="table-responsive" >
									<table class="table table-bordered">
										<tbody>
											<c:forEach var="timeline"
												items="${requestScope.timelinelist}" varStatus="status">
												<tr>
													<td style="whidt:500px"><b>${timeline.timeLineNumber}</b>:${timeline.content_eng}
														<!-- <a href="#" class="Update" id="Update" style="float: right">수정</a> -->
														<button type="" class="Update" id="Up" value="${timeline.timeLineNumber}">수정</button>
														<br> 
														<b>${timeline.content_kor}
															<a href="TimeLineDelete.tc?timeLineNumber=${timeline.timeLineNumber}" style="float: right">삭제</a>
													</b></td>
											
												</tr>
												
											</c:forEach>
										</tbody>
									</table>
								</div>
							</section>
						</div>
					</div>
					<div class="d-flex justify-content-between mt-0">
						<ul class="pagination pagination-lg" style="margin: 0 auto">
							<c:if test="${currpage>1}">
								<!-- 이전 버튼 생성 조건 -->
								<li class="page-item"><a
									href="TimeLineList.tc?cp=${currpage-1}&ps=${pagesize}"
									class="page-link">Previous</a></li>
							</c:if>

							<!-- 페이지 번호 -->
							<c:forEach var="pnum" begin="1" end="${pagecount}" step="1">
								<c:choose>
									<c:when test="${currpage == pnum}">
										<li class="page-item"><a href="" class="page-link">${pnum}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a
											href="TimeLineList.tc?cp=${pnum}&ps=${pagesize}"
											class="page-link">${pnum}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<!-- 다음 버튼 -->
							<c:if test="${currpage < pagecount}">
								<li class="page-item"><a
									href="TimeLineList.tc?cp=${currpage+1}&ps=${pagesize}"
									class="page-link">Next</a></li>
							</c:if>
						</ul>
					</div>

				</div>
			</div>

		</div>
	</div>


</body>
</html>


<%@page import="kr.co.engcom.dao.UserTableDao"%>
<%@page import="kr.co.engcom.dao.CMBoardDAO"%>
<%@page import="kr.co.engcom.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	BoardDao bd = new BoardDao();
    	int bdcount = bd.getListCount();
    	int gcount = bd.getGrammarListCount();
    	int rcount = bd.getReadingListCount();
    	int lcount = bd.getListeningListCount();
    	
    	CMBoardDAO cm = new CMBoardDAO(); 
    	int cmcount = cm.getCMBoardListCount();
    	
    	UserTableDao ud = new UserTableDao();
    	int usercount = ud.getCMUserListCount();
    	
    			
    	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
<!-- SELECT count(*) FROM cmreply;
SELECT count(*) FROM board;
SELECT count(*) FROM usertable; -->
<canvas id="myChart" width="400" height="400"></canvas>
<script>
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['전체 게시물 수', 'Grammar 게시물 수', 'Reading 게시물 수', 'Listening 게시물 수', '캐치마인드 게시물 수', '회원 수'],
        datasets: [{
            label: 'count',
            data: [<%=bdcount%>, <%=gcount%>, <%=rcount%>, <%=lcount%>, <%=cmcount%>, <%=usercount%>],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});
</script>
</body>
</html>
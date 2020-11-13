<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/game2.css">
<style type="text/css">

</style>
<script src="assets/js/game2.js" defer>
    </script>
</head>
<body class="game">
 <div class='container word_' id="main">
    <div class="word_board">
      <ul id="box_1" class="word_box">
      <li id="A" class="word_item" draggable="true"><i>A</i></li>
      </ul>

      <ul id="box_2" class="word_box">
      <li id="E" class="word_item" draggable="true"><i>E</i></li>
      </ul>
    
      <ul id="box_3" class="word_box">
        <li id="P" class="word_item" draggable="true"><i>P</i></li>
      </ul>
   
      <ul id="box_4" class="word_box">
        <li id="L" class="word_item" draggable="true"><i>L</i></li>
      </ul>
    
      <ul id="box_5" class="word_box">
        <li id="P" class="word_item" draggable="true"><i>P</i></li>
      </ul>
 
    <!-- 
      <ul id="box_7" class="word_box">
        <li id="P" class="word_item" draggable="true"><i>P</i></li>
      </ul>
     
      <ul id="box_8" class="word_box">
        <li id="H" class="word_item" draggable="true"><i>H</i></li>
      </ul>
   
      <ul id="box_9" class="word_box">
        <li id="P" class="word_item" draggable="true"><i>P</i></li>
      </ul> -->
    </div>
    <H3 class="h3">오늘의 단어</H3>
    <ul style="padding-left: 0px;" class="result"><i class="result">사과</i></ul>
    <div id="answer" class="answer_board">
      <div id="answer_inner" class="answer_inner">
        <ul id="A" class="word_answer" ></ul>
        <ul id="P" class="word_answer" ></ul>
        <ul id="P" class="word_answer" ></ul>
        <ul id="L" class="word_answer" ></ul>
        <ul id="E" class="word_answer" ></ul>
     
      
      
      </div>
    </div>
  </div>
  <div id="message"></div>
</body>

</html>
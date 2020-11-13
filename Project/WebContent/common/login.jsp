<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- 팝업 될 레이어 --> 
<div class="modal"> 
   <div id="form-wrap">
      <span class="close-button">&times;</span>
      <h1 id="login_title" class="title">Log in</h1>
      <!-- 버튼 -->
       <div class="button-wrap">
           <div id="btn"></div>
           <button type="button" class="togglebtn1" onclick="login()">로그인</button>
           <button type="button" class="togglebtn2" onclick="register()">회원가입</button>
       </div>
       <!-- //버튼 -->
   
       <!-- 로그인 -->
       <form id="login" action="LogIn.go" class="input-group" method="post">
           <input type="text" id="sign_id" name="sign_id" class="input-field" placeholder="아이디">
           <input type="password" id="sign_pwd" name="sign_pwd" class="input-field" placeholder="비밀번호">
           <button class="submit">로그인</button>
       </form>
       <!-- //로그인 -->
   
      <h1 id="register_title" class="title">Sign Up</h1>
       <!-- 회원가입 -->
       <form id="register" action="SignUp.go" class="input-group" method="post">
            <input type="text" name="userid" id="userid" class="input-field" placeholder="아이디">
            <span id="guide"></span>
            <input type="password" name="pwd" id="pwd" class="input-field" placeholder="비밀번호">
            <input type="password" id="pwdcheck" class="input-field" placeholder="비밀번호 확인">
            
            <p class="birthday">생년월일:</p>
            <select id="birthYYYY" name="birthYYYY" >
               <option value="empty">연도 입력</option>
            </select>
            <label for="birthYYYY">년</label>
            
            <select id="birthMM" name="birthMM" disabled>
               <option value="empty">월 입력</option>
            </select>
            <label for="birthMM">월</label>
            
            <select id="birthDD" name="birthDD" disabled>
            </select>
            <label for="birthDD">일</label>
            
            <input type="text" name="email" id="email" class="input-field" placeholder="이메일">
            <input type="submit" class="submit" value="회원가입">
       </form>
       <!-- //회원가입 -->
   </div>
</div>
<!-- //팝업 될 레이어 --> 
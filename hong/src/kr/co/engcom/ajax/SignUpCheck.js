/* 
	파일명: SignUp.js
	설명: 회원가입 시 정규표현식 및 유효성 검사
	작성일: 2020-11-09
	작성자: 백희승
*/
	var totalCheck = false;
	$(function() {
		// form submit 전 유효성 검사 함수 삽입
		
		$('#register').attr('onsubmit', 'return checkValues()');
		
		$('#login').attr('onsubmit', 'return checkInfo()');
		
		// 선택 가능한 날짜 범위 : 현재 년도 - 120년 ~ 현재 년도
		const currentYear = new Date().getFullYear();
		const rangeOfYear = currentYear - 120;

		for (let yr = currentYear; yr > rangeOfYear; yr--) {	// 년도 삽입
			$("#birthYYYY").append(
					"<option value='" + yr + "'>" + yr + "</option>");
		}

		for (let mth = 1; mth <= 12; mth++) {						// 월 삽입
			$("#birthMM").append(
					"<option value='" + mth + "'>" + mth + "</option>");
		}

		$("#birthYYYY").change(function(){
			$("#birthMM").prop("disabled",false);
			setDay();
		});
		
		$("#birthMM").change(function() {
			$("#birthDD").prop("disabled",false);
			setDay();
		});
		
		// ID 중복 유무 비동기로 확인하는 함수
		$("#userid").keyup(function() {
				$.ajax({
					url : "CheckId",
					type : "POST",
					dataType : "html",
					data : {
						data : $(this).val()
					},
					success : function(responsedata) {
						if (responsedata == "true") {
							$('#guide').empty();
							$('#guide').text("이미 존재하는 아이디입니다.");
							$('#guide').css({
								"color":"red",
								"font-size":"12px"
							});
							totalCheck = false;
						}else if($('#userid').val() == ''){
							$('#guide').empty();
							$('#guide').text("사용하실 아이디를 입력해주세요.");
							$('#guide').css({
								"color":"green",
								"font-size":"12px"
							});
							totalCheck = false;
						}else if(isValid($('#userid')) == false){
							$('#guide').empty();
							$('#guide').text("6~12자리 영 대소문자 + 숫자로 입력해 주세요.");
							$('#guide').css({
								"color":"red",
								"font-size":"12px"
							});
							totalCheck = false;
						}else {
							$('#guide').empty();
							$('#guide').text("사용 가능한 Id입니다.");
							$('#guide').css({
								"color":"green",
								"font-size":"12px"
							});
							totalCheck = true;
						}
					}
				});
			}
		);
	});
	
	// 윤년 2월 구분하여 일자 삽입 함수 setDay()
	const setDay = () => { 
		$("#birthDD").empty();
		
		let year = Number($("#birthYYYY").val());
		let month = Number($("#birthMM").val());
		
		let totalDay = 0;
		
		switch (month) {
			case 2:
				if( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {	// 윤년 2월
					totalDay = 29;
				}else {	// 평년 2월
					totalDay = 28;
				}
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				totalDay = 31;
				break;
			default:
				totalDay = 30;
		}
		
		for(let day = 1; day <= totalDay; day++){
			$("#birthDD").append("<option value='" + day + "'>" + day + "</option>");
		}
	}
	
	// 정규표현식 검사 함수 isValid(targetEle)
	const isValid = targetEle => {
		let checkFlag = false;
		let regex = "";
		
		switch(targetEle.prop("id")){
			case "userid":
			case "pwd":	
				// id & pwd : 6~12자리 영문자 + 숫자
				regex = /^[a-zA-Z0-9]{6,12}$/;
				break;
			case "email":
				// email 형식
				regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				break;
		}
		
		if(regex.test(targetEle.val())) {	//정규표현식 통과
			checkFlag = true;
		}
		
		return checkFlag;
	}
	
	// 빈 문자열, pwd pwdcheck 동일 여부, 정규표현식여부 검사 함수 checkValues()
	const checkValues = () => {
		let submitFlag = false;
		
		if($('#userid').val() == ''){ 
			swal("ID를 입력해주세요.", "공백은 허용되지 않습니다.", "error");
			
		}else if(!isValid($('#userid'))){
			swal("ID를 다시 입력해주세요.", "6~12자리 영문자 + 숫자 조합이 가능합니다.", "error");
		
		}else if($('#pwd').val() == ''){
			swal("설정하실 비밀번호를 입력해주세요.", "공백은 허용되지 않습니다.", "error");
			
		}else if($('#pwdcheck').val() == ''){
			swal("패스워드 확인란을 입력해주세요.", "공백은 허용되지 않습니다.", "error");
			
		}else if(!isValid($('#pwd'))){
			swal("패스워드를 다시 입력해주세요.", "6~12자리 영문자 + 숫자 조합이 가능합니다.", "error");
			
		}else if($('#pwd').val() !== $('#pwdcheck').val()){
			swal("패스워드 입력을 확인해주세요.", "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "error");
			
		}else if($('#birthYYYY').val() == 'empty' || $('#birthYYYY').val() == ''){
			swal("생년월일을 입력해주세요.", "연도를 입력하지 않으셨어요.", "error");
			
		}else if($('#birthMM').val() == 'empty' || $('#birthMM').val() == ''){
			swal("생년월일을 입력해주세요.", "월을 입력하지 않으셨어요.", "error");
			
		}else if($('#email').val() == ''){
			swal("이메일을 입력해주세요.", "공백은 허용되지 않습니다.", "error");
			
		}else if(!isValid($('#email'))){
			swal("이메일 형식에 맞게 입력해주세요.", "예) EngCom12@naver.com", "error");
		
		}else if(totalCheck){
			submitFlag = true;
		}
		return submitFlag;
	}
	
	const checkInfo = () => {
		let logInFlag = false;
		
		if($('#sign_id').val() == ''){
			swal("ID를 입력해주세요.", "공백은 허용되지 않습니다.", "error");
		}else if($('#sign_pwd').val() == ''){
			swal("패스워드를 입력해주세요.", "", "error");
		}else {
			logInFlag = true;
		}
		return logInFlag;
	}
	
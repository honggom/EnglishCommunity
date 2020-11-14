/*left 메뉴*/
const body = document.getElementsByTagName('body')[0]

function setCookie(cname, cvalue, exdays) {
  var d = new Date()
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
  var expires = "expires="+d.toUTCString()
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/"
}

function collapseSidebar() {
   body.classList.toggle('sidebar-expand')
}
/*//left 메뉴*/

/*팝업창*/
var modal = document.querySelector(".modal"); //전체 화면
var trigger = document.querySelector(".trigger"); //버튼
var closeButton = document.querySelector(".close-button"); //창닫기
//console.log(modal);

function toggleModal() { 
    modal.classList.toggle("show-modal"); 
}

function windowOnClick(event) { 
    if (event.target === modal) { 
        toggleModal(); 
    } 
}
/*//팝업창*/

/*로그인, 회원가입*/
trigger.addEventListener("click", toggleModal); 
closeButton.addEventListener("click", toggleModal); 
window.addEventListener("click", windowOnClick);

var ltitle = document.getElementById("login_title");    // login타이틀
var rtitle = document.getElementById("register_title");   // register 타이틀

var lform = document.getElementById("login");       //전체 화면
var rform = document.getElementById("register");    //버튼
var tgbtn = document.getElementById("btn");       //창닫기

var size = document.getElementById("form-wrap")    //화면크기

ltitle.style.left = "65px";
rtitle.style.left = "1000px";
rtitle.style.top = "10px";

function login(){
   ltitle.style.left = "65px";
   rtitle.style.left = "1000px";
   
    lform.style.left = "50px";
    rform.style.left = "1000px";

    tgbtn.style.left = "0";

    size.style.height = "450px";
}

function register(){
   
   ltitle.style.left = "-1000px";
   rtitle.style.left = "75px";
   
    lform.style.left = "-1000px";
    rform.style.left = "50px";

    tgbtn.style.left = "110px";

    size.style.height = "650px";
}
/*//로그인, 회원가입*/

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
const result = confirm("정말 삭제 하시겠습니까?");
if(result){
    alert("삭제 완료");
    location.href="CMBoardDelete.cm?num=${param.CMBoardNumber}";
}else{
    alert("삭제 취소");
    location.href="CMBoardDetailService.cm?num=${param.CMBoardNumber}";
}
</script>
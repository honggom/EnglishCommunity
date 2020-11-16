
	$(function(){
		$('#contentWrite').click(function(){
			$('#TimeLineContent').slideUp('slow',function(){
		});
			$('#TimeLineContent').slideDown('slow',function(){
			$("#TimeLineContent").load("TimeLineWrite.tc");	
			});
		});
	});
	
	$(function(){
		$('#contentList').click(function(){
			$('#TimeLineContent').slideUp('slow',function(){
		});
			$('#TimeLineContent').slideDown('slow',function(){
			$("#TimeLineContent").load("TimeLineList.tc");	
			});
		});
	});
	$(function(){
		$('#contentMain').click(function(){
			$('#TimeLineContent').slideUp('slow',function(){
		});
			$('#TimeLineContent').slideDown('slow',function(){
			$("#TimeLineContent").load("TimeLineListMain.tc");	
			});
		});
	});
	
	$(document).ready(function(){
		$("#TimeLineContent").load("TimeLineListMain.tc");
	})
	$(document).ready(function(){
		$("#game").load("game2.jsp");
	})
	/* 오늘의 회화 드롭다운 메뉴 */
	$(document).ready(function(){
		$("#today-dropdown > .today-menu").click(function(){
			$("#today-dropdown > .dropdown").fadeToggle(function(){
				if($(this).css('display') == "none")
					$("#today-dropdown > .today-menu").removeClass("today-menu");
				else
					$("#today-dropdown > .today-menu").addClass("today-menu");
			});
		});
	});
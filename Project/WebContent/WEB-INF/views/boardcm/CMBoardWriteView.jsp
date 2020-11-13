<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CM Board Write View</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
canvas {
	border: 1px solid blue;
}

.jb_table {
	display: table;
}

.row {
	border-radius: 10px;
	display: table-row;
}

.cell {
	display: table-cell;
	vertical-align: top;
}

textarea {
	background-color: #fcf3cf;
}
</style>
<script language="JavaScript">
	// Date: 2019.04.24

	var textareaList = [ "history" ];

	function clearText(idOfTextArea) {
		document.getElementById(idOfTextArea).value = "";
	}
	function SaveAsTxt() {
		var fileName = document.getElementById("title").value;
		if (fileName.length == 0) {
			fileName = "image";
		}
		fileName += ".txt";
		var preData = 'version: V0.617a1\n';
		var postData = preData + document.getElementById("history").value;
		var link = document.createElement("a");
		link.setAttribute("download", fileName);
		link.setAttribute("href", "data:" + "application/[txt]"
				+ ";charset=utf-8," + encodeURIComponent(postData));
		link.click();
	}
	function SaveAsJson() {
		console.log("SaveAsJson");
		var fileName = document.getElementById("title").value;
		if (fileName.length == 0) {
			fileName = "imsge";
		}
		fileName += ".json";
		var preData = {
			'version' : 'V0.617a1'
		};
		textareaList.forEach(function(e) {
			preData[e] = document.getElementById(e).value;
		});
		var jsonData = JSON.stringify(preData);
		var link = document.createElement("a");
		var file = new Blob([ jsonData ], {
			type : "text/plain"
		});
		link.href = URL.createObjectURL(file);
		link.download = fileName;
		link.click();
	}
	function isJsonFile(filename) {
		var ridx = filename.lastIndexOf(".");
		var extension = filename.substring(ridx + 1);

		console.log(extension);

		if (extension.length != 4 || extension.toLowerCase() != "json") {
			return false;
		}
		return true;
	}

	function isTextFile(filename) {
		var ridx = filename.lastIndexOf(".");
		var extension = filename.substring(ridx + 1);

		console.log(extension);

		if (extension.length != 3 || extension.toLowerCase() != "txt") {
			return false;
		}
		return true;
	}

	function loadFile() {
		var loadFile = document.getElementById("load_filename");
		var file = loadFile.files[0];

		if (!file) {
			return;
		}

		var fileName = document.getElementById("load_filename").value;
		var ridx = fileName.lastIndexOf("\\");

		fileName = fileName.substring(ridx + 1);

		if (isJsonFile(fileName)) {
			LoadJson(file, fileName);
		} else if (isTextFile(fileName)) {
			LoadText(file, fileName);
		}
	}
	function LoadJson(file, fileName) {
		document.getElementById("title").value = fileName;
		var reader = new FileReader();
		reader.onload = function(e) {
			var contents = e.target.result;
			displayLoadJsonData(contents);
		};
		reader.readAsText(file);
	}
	function displayLoadJsonData(contents) {
		var noteData = JSON.parse(contents);
		var version = noteData['version'];
		console.log(version);
		document.getElementById('history').value = noteData['history'];
		reDrawCanvas();
	}

	function LoadText(file, fileName) {
		document.getElementById("title").value = fileName;

		var reader = new FileReader();
		reader.onload = function(e) {
			var contents = e.target.result;
			displayLoadTextData(contents);
		};
		reader.readAsText(file);
	}

	function displayLoadTextData(contents) {
		var noteData = contents.split('\n');
		var history = "";
		noteData.forEach(function(e) {
			if (e[0] != 'v') {
				history += e + "\n";
			}
		});
		document.getElementById('history').value = history;
		reDrawCanvas();
	}
</script>
<script>

	$(function() {
	
		$("#imageForm").submit(function(event) {//서브밋 버튼을 눌렀을 때 실행되는 함수 
			let CMBoardSubject = document.getElementById("CMBoardSubject");
			let CMBoardAnswer = document.getElementById("CMBoardAnswer");
			
			if(CMBoardSubject.value===""){
				alert("글 제목을 입력하세요.")
				return false;
				
			}else{
				if(CMBoardAnswer.value===""){
					alert("정답을 입력하세요.")
					return false;
				}else{
					const image = document.getElementById("canvas").toDataURL("image/png");//캔버스 그림을 base64 데이터 타입으로 변환 / base64는 이미지를 코드화 한 것  
					console.log(image);
					$("#CMImage").val(image);//숨은 hidden에 밸류값을  base64로 설정 후 service로 넘김 
					console.log($("#CMImage").val());
				}
			}
		});
	});
	
</script>
</head>
<body class="wrap">

		<jsp:include page="/common/top.jsp"></jsp:include>
		<jsp:include page="/common/left.jsp"></jsp:include>
		<jsp:include page="/common/login.jsp"></jsp:include>
		<div class="wrapper">
 		<div class="row">
 		<div class="card">

<h1>캐치 마인드</h1>
	<form id="imageForm" method="post" 
		action="<%=request.getContextPath()%>/write.cm">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		[글 제목]<input type="text" name="CMBoardSubject" id ="CMBoardSubject"> [글 정답]<input
			type="text" name="CMBoardAnswer" id = "CMBoardAnswer">
			

		<!-- 
		[글 제목] [                       ]
		[글 정답] [                       ]
		[그림판 ] --------------------------
		[][][] |						 |
		[][][] |						 |
		[][][] |						 |
		[][][] |						 |
		[][][] |						 |
		[][][] |						 |
			   |						 |
			   |						 |
			   |						 |
			   ---------------------------
				[글 작성] [이전으로]
	 	-->
	 	
	 	
		<div class="jb_table">
			<div class="row">
				<span class="cell" width="82">
					<div>
						<div class="jb_table">
							<div class="row">
								<span class="cell"> <img src="src/img/red.png"
									onclick="selectColor('red')" /> <img src="src/img/orange.png"
									onclick="selectColor('orange')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/yellow.png"
									onclick="selectColor('yellow')" /> <img
									src="src/img/green.png" onclick="selectColor('green')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/blue.png"
									onclick="selectColor('blue')" /> <img
									src="src/img/lightblue.png" onclick="selectColor('lightblue')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/lightgreen.png"
									onclick="selectColor('lightgreen')" /> <img
									src="src/img/brown.png" onclick="selectColor('brown')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/purple.png"
									onclick="selectColor('purple')" /> <img src="src/img/pink.png"
									onclick="selectColor('pink')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/gray.png"
									onclick="selectColor('gray')" /> <img
									src="src/img/lightgray.png" onclick="selectColor('lightgray')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/black.png"
									onclick="selectColor('black')" /> <img src="src/img/white.png"
									onclick="selectColor('white')" />
								</span>
							</div>
							<div class="row">
								<span class="cell"> <img src="src/img/pencil.png"
									onclick="selectTool('pencil')" /> <img src="src/img/line.png"
									onclick="selectTool('line')" />
								</span>
							</div>
						</div>
					</div>
				</span> <span class="cell">
					<div>
						<canvas id="canvas" width="720" height="720"></canvas>
					</div>
				</span> <span class="cell"> <INPUT type="hidden" id="load_filename"
					value="Load" onChange="loadFile()" />
					<div>
						<INPUT type="hidden" value="Save"  /> <INPUT
							type="button" value="Clear" onClick="initPage()" />
					</div>
					<div>
						<input type="hidden" value="Save as Json" onClick="SaveAsJson()" />
						<input type="hidden" value="Save as Txt" onClick="SaveAsTxt()" />
					</div>
					<div>
						<textarea id="history" cols="40" rows="37" style="display: none;"></textarea>
					</div>
					<div id="command"></div> <INPUT type="hidden" value="Redraw"
					onClick="reDrawCanvas()" />
					<div>
						<input type="hidden" value="" name="CMImage"
							id="CMImage">
					</div>
				</span>
			</div>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="글 작성"> 
		<a href="#"><input type="button" value="이전 페이지"></a>
	</form>
	
	
	
	</div>
 		</div>
 		</div>
		<jsp:include page="/common/footer.jsp"></jsp:include>	
		<jsp:include page="/common/script.jsp"></jsp:include>	
	
	<script src="src/painter.js"></script>
	<script src="src/drawengine.js"></script>
</body>
</html>
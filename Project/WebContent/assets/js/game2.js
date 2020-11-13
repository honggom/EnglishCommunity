$(document).ready(function(){
  var cdrag = 'li';
  var cobj = 'ul';
  var dragid;
  
  $(cdrag).bind('dragstart', function(event) {
    event.originalEvent.dataTransfer.setData("text/plain",  event.target.getAttribute('id'));
    console.log("evDragStart: " + event.target.getAttribute("id"));
    dragid=event.target.getAttribute("id");
	
  });
  $(cobj).bind('dragover', function(event) {
    event.preventDefault();
  });
  $(cobj).bind('dragenter', function(event) {
    console.log("evDragEnter: " + $(this).attr("id"));
    $(this).addClass("over");
  });
  $(cobj).bind('dragleave drop', function(event) {
    $(this).removeClass("over");
  });
  $(cdrag).bind('drop', function(event) {
    return false;
  });
  $(cobj).bind('drop', function(event) {
    var listitem = event.originalEvent.dataTransfer.getData("text/plain");
	console.log(event.target)  
	var box1 = document.getElementById("answer_inner").children[0];
	var box2 = document.getElementById("answer_inner").children[1];
	var box3 = document.getElementById("answer_inner").children[2];
	var box4 = document.getElementById("answer_inner").children[3];
	var box5 = document.getElementById("answer_inner").children[4];

if($(this).attr("id") === dragid){
	event.target.appendChild(document.getElementById(listitem));
	event.target.classList.add("dropped");
    event.preventDefault();
    var tID = $(this).attr("id");  //dropid
    var tClass = $(this).attr("class");
    //debugger;
    console.log("evDrop: item dropped: " + listitem 
                + " to id: " + tID + " (class: " + tClass + ")"
               );
	

    event.originalEvent.dataTransfer.clearData();
}

if(box1.classList.contains("dropped")&&box2.classList.contains("dropped")&&box3.classList.contains("dropped")
&&box4.classList.contains("dropped")&&box5.classList.contains("dropped")){
	console.log("완성");
	$(function(){
			$('#answer').slideUp('slow',function(){})
			$('#message').slideDown('slow',function(){
			//$('#message').html("<p style='color:green;font-weight:bold'>정답입니다</p>");	
			$('#message').html("<img src='assets/img/aaa.jpg'/>");
			});
		});
};
/*console.log(box)
	if(box.classList.contains("dropped")){
		console.log("완성");
	}*/
  });
});



/*function drop(event) {
  event.preventDefault(); // This is in order to prevent the browser default handling of the data
  event.target.classList.remove("droppable-hover");
  const draggableElementData = event.dataTransfer.getData("text"); // Get the dragged data. This method will return any data that was set to the same type in the setData() method
  const droppableElementData = event.target.getAttribute("data-draggable-id");
  const isCorrectMatching = draggableElementData === droppableElementData;
  if(isCorrectMatching) {
    const draggableElement = document.getElementById(draggableElementData);
    event.target.classList.add("dropped");
    // event.target.style.backgroundColor = draggableElement.style.color; // This approach works only for inline styles. A more general approach would be the following: 
    event.target.style.backgroundColor = window.getComputedStyle(draggableElement).color;
    draggableElement.classList.add("dragged");
    draggableElement.setAttribute("draggable", "false");
    event.target.insertAdjacentHTML("afterbegin", `<i class="fas fa-${draggableElementData}"></i>`);
  }
}*/
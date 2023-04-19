/**
 * Board Form 유효성 검사
 */

//공란 검사
/*const submitButton = document.getElementById('submitButton');

submitButton.addEventListener('click', function(){
	if(!document.getElementById('title').value) {
		alert("제목을 입력하세요.")
		return;
	} else if(!document.getElementById('writer').value) {
		alert("작성자를 입력하세요.")
		return;
	} else if(!document.getElementById('contents').value) {
		alert("내용을 입력하세요.")
		return;
	} else {
		document.getElementById('contactForm').submit();
	}
});*/

$('#submitButton').click(function(){
	if(!$('#title').val()) {
		alert("제목을 입력하세요.")
		return;
	} else if(!$('#writer').val()) {
		alert("작성자를 입력하세요.")
		return;
	} else if(!$('#contents').val()) {
		alert("내용을 입력하세요.")
		return;
	} else {
		$('#contactForm').submit();
	}
})

//파일 폼 추가
let idx = 1;
let count = 1;
let max = 5;

$('#fileAdd').click(function(){
	if(count >= max) {
		alert("파일은 최대 " + max + "개까지 업로드 가능합니다.")	
		return;
	};
	
	count++;
	
	let child = '<div class="input-group mb-3" id="file'+idx+'">';
		child = child + '<input class="form-control" type="file" name="boardFiles" aria-describedby="fileAdd">'
		child = child + '<button class="btn btn-outline-danger delete" type="button" id="fileDelete">Delete</button>'
		
	$('#fileList').append(child);
	idx++;
})

//파일 폼 삭제
$('#fileList').on('click', '.delete', function(e){
	$(this).parent().remove();
	count--;
})
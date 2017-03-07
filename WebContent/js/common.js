//input 태그 공란 체크
//input이 비었을 때 형제 클래스인 .error가 나타나도록
function checkInputEmpty(getInput){
	var count = 0;
	
	getInput.each(function(i, obj) {
		if($(obj).val() == ""){
			var next = $(obj).next(".error");
			next.css("display", "block");
			count++;
		}
	});
	
	if (count > 0) return false;
	else return true;
}
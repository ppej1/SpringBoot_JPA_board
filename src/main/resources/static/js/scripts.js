$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e){
	e.preventDefault();
	
	var queryString = $(".answer-write").serialize();
	var url = $(".answer-write").attr("action");
	$.ajax({
			type :'POST',
			url: url,
			data: queryString,
			dataType : 'json',
			error : onError,
			success: onSuccess});
}

function onError(){
	
}
function onSuccess(data, status){
	console.log("data : " +data);

}
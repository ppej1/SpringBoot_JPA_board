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
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.name, data.formattedCreateDate, data.contents, data.question.QuestId, data.id);
	$(".qna-comment-slipp-articles").prepend(template)
	$(".answer-write textarea").val('');
	var comment = $(".qna-comment-count strong").html();
	var cnt = parseInt(comment)+1;
	$(".qna-comment-count strong").html(cnt);
}

$(".answer-delete-article").click(deleteAnswer);

function deleteAnswer(e){
	e.preventDefault();
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log("url : "+ url);
	
	$.ajax({
		type: 'post',
		url : url,
		dataType : 'json',
		error : function(xhr, status){
			console.log("error");
		},
		success : function(data,status){
			var comment = $(".qna-comment-count strong").html();
			var cnt = parseInt(comment)-1;
			$(".qna-comment-count strong").html(cnt);	
			console.log(data);
			if(data.valid){
				deleteBtn.closest("article").remove();
			}else{
				alert(data.errorMessage);
			}

			//var cnt = parseInt(a)-1;
			//$(".qna-comment-count strong").html(cnt);
		}
	});
	
}





String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};
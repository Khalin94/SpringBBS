<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="h3 mb-2 text-gray-800">게시글 읽기</h1>
	</div>
</div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">게시글</h6>
	</div>
	<div class="card-body">
			<div class="form-group">
				<label>#</label> <input class="form-control" name="bno" value='<c:out value="${board.bno }" />' readonly="readonly">
			</div>
			<div class="form-group">
				<label>제목</label> <input class="form-control" name="title" value="<c:out value='${board.title }' />"	readonly="readonly">
			</div>	
			
			<div class="form-group">
				<label>내용</label> <textarea class="form-control" name="content" rows="5" readonly="readonly"><c:out value='${board.content }' /></textarea>
				 
			</div>
			
			<div class="form-group">
				<label>작성자</label>	<input class="form-control" name="writer" value="<c:out value='${board.writer }' />" readonly="readonly">
			</div>
			
			<button data-oper="modify" class="btn btn-light">수정 하기</button>
			<button data-oper="list" class="btn btn-success">목록</button>
			
			<hr>
			<div class="w-100"></div>

			<div class="row">
				<div class="col-lg-12">
					<div class="card card-default">
						<div class="card-heading">
							<i class="fa fa-comments fa-fw"></i>Reply
							<button id="addReplyBtn" class="btn btn-primary btn-sm float-right">댓글 달기</button>
						</div>	
						
						<div class="card-body">
							<ul class="chat">
								<li class="left clearfix" data-rno="10">
									<div>
										<div class="header">
											<strong class="primary-font">user</strong>	
											<small class="float-right text-muted">2019-06-20 05:08</small>
										</div>
										<p>Hello World!</p>
									</div>		
								</li>
							</ul>
						</div>
					</div>	
				</div>	
			</div>
			
			<form id="operForm" action="/freeBoard/modify" method="get">
				<input type="hidden" id="bno" name="bno" value="<c:out value='${board.bno }'/>">
				<input type="hidden" id="pageNum" name="pageNum" value="<c:out value='${cri.pageNum }' />">
				<input type="hidden" id="amount" name="amount" value="<c:out value='${cri.amount }' />">
				<input type="hidden" name="type" value="${cri.type }">
				<input type="hidden" name="keyword" value="${cri.keyword }">
			</form>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id = "myModalLabel">Reply Modal</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label>	
					<input class="form-control" name="reply" value = "new reply">
				</div>	
				<div class="form-group">
					<label>Replyer</label>	
					<input class="form-control" name = "replyer" value= "replyer">
				</div>
				
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name = "replyDate" value = "">
				</div>
			</div>
			<div class="modal-footer">
				<button id = "modalModBtn" type="button" class="btn btn-warning">수정</button>
				<button id= "modalRemoveBtn" type='button' class = "btn btn-danger">삭제</button>
				<button id ="modalRegisterBtn" type="button" class="btn btn-primary">등록</button>
				<button id= "modalCloseBtn" type="button" class="btn">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/resources/js/freeReply.js" ></script>

<script type="text/javascript">
$(document).ready(function() {
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);

	
	function showList(page){
		replyService.getList({bno:bnoValue, page : page||1}, function(list){
			var str="";
			if(list == null || list.length == 0){
				replyUL.html("");
				return;
			}
			
			for(var i = 0, len = list.length || 0; i < len; i++){
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
				str += " <div> <div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
				str += "  <small class='float-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str += "   <p>"+list[i].reply+"</p></div></li>";
			}
			
			replyUL.html(str);
		});
	}

	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
		
		modal.modal("hide");
	});
	
	modalRegisterBtn.on("click", function(e){
		var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue
		};
		
		replyService.add(reply, function(result){
			alert(result);
			
			modal.find("input").val();
			modal.modal("hide");
			
			showList(-1);
		});
	});
	
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
	
	modalModBtn.on("click", function(e){
		var reply = {rno:modal.data("rno"), reply : modalInputReply.val()};
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(1);
		});
	});

	modalRemoveBtn.on("click", function(e){
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
			alert(result);
			modal.modal("hide");
			showList(1);
		});
	});

/*
	replyService.add(
		{reply: "js test", replyer : "js test", bno : bnoValue},
		function(result){
			alert("result : " + result);
		});
	
	replyService.getList({bno:bnoValue, page:1}, function(list){
		for(var i = 0, len = list.length||0; i < len; i++){
			console.log(list[i]);
		}
	});
	
	replyService.remove(12, function(count){
		console.log(count);
		
		if(count === "success"){
			alert("removed");
		}
	}, function(err){
		alert("error");
	});
	
	replyService.update({
		rno : 13,
		bno : bnoValue,
		reply : "modify reply"
	}, function(result){
		alert("modifid");
	});
	
	replyService.get(10, function(data){
		console.log(data);
	});
	*/
});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		var operForm = $("#operForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/freeBoard/modify").submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/freeBoard/list").submit();
		});
	});
</script>


<%@ include file="../includes/footer.jsp"%>
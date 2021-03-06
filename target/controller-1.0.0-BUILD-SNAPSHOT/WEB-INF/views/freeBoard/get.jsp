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
			
			<div class="bigPictureWrapper">
				<div class="bigPicture">
				</div>	
			</div>
			
			<style>
				.uploadResult{
					width : 100%;
					background-color : gray;
				}	
				
				.uploadResult ul{
					display :flex;
					flex-flow : row;	
					justify-content : center;
					align-items : center;
				}
				
				.uploadResult ul li{
					list-style : none;
					padding : 10px;
					align-content : center;
					text-align : center;
				}
				
				.uploadResult ul li img{
					width : 100px;
				}
				
				.uploadResult ul li span{
				 color : white;
				}
				
				.bigPictureWrapper{
					position : absolute;
					display : none;
					justify-content : center;
					align-items : center;
					top : 0%;
					width: 100%;	
					height : 100%;
					background-color : gray;
					z-index : 100;
					background : rgba(255,255,255,0.5);
				}
				
				.bigPicture{
					position : relative;
					display :flex;
					justify-content : center;
					align-items : center;
				}
			</style>
			
			<div class = "row">
				<div class ="col-lg-12">
					<div class="card card-default">
						<div class="card-header">첨부 파일</div>	
						<div class="card-body">
							<div class="uploadResult">
								<ul>
								
								</ul>	
							</div>
						</div>
					</div>	
				</div>	
			</div>
			
			<hr>
			<div class="w-100"></div>

			<div class="row">
				<div class="col-lg-12">
					<div class="card card-default">
						<div class="card-heading">
							<i class="fa fa-comments fa-fw"></i>Reply
							<button id="addReplyBtn" class="btn btn-primary btn-sm float-right">댓글 달기</button>
						</div>	
					<!--  	
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
						-->
						<div class="card-body">
							<ul class="chat">
							
							</ul>
						</div>
						
						<div class="card-footer">
						
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

<script>
$(document).ready(function(){
	(function(){
		var bno = "<c:out value='${board.bno}'/>";
		
		$.getJSON("/freeBoard/getAttachList", {bno:bno}, function(arr){
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+ "/s_"+attach.uuid+"_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename= '"+attach.fileName+ "' data-type='"+attach.fileType+"'><div>";
					str += "<img src = '/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				}else{
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+ attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
					str += "<span>"+attach.fileName+"</span><br/>";
					str += "<img src = '/resources/img/attach.png'>";
					str += "</div>";
					str += "</li>";
				}
			});
			
			$(".uploadResult ul").html(str);
		});
	})();
});

$(".uploadResult").on("click", "li", function(e){
	console.log("view image");
	
	var liObj = $(this);
	
	var path = encodeURIComponent(liObj.data("path")+"/"+ liObj.data("uuid")+"_"+liObj.data("filename"));
	
	if(liObj.data("type")){
		showImage(path.replace(new RegExp(/\\/g), "/"));
	}else{
		self.location = "/download?fileName="+path;
	}
		
});

function showImage(fileCallPath){
	alert(fileCallPath);
	
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture").html("<img src = '/display?fileName="+fileCallPath+"'>").animate({width : '100%', height : '100%'}, 1000);
}

$(".bigPictureWrapper").on("click", function(e){
	$(".bigPicture").animate({width : '0%', height : '0%'}, 1000);
	setTimeout(function(){
		$('.bigPictureWrapper').hide();
	}, 1000);
});
</script>

<script type="text/javascript" src="/resources/js/freeReply.js" ></script>

<script type="text/javascript">
$(document).ready(function() {
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	var pageNum = 1;
	var replyPageFooter = $(".card-footer");
	
	function showReplyPage(replyCnt){
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt / 10.0);
		}
		
		if(endNum * 10 < replyCnt ){
			next = true;
		}
		
		var str = "<ul class = 'pagination float-right'>";
		
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+(startNum - 1)+"'>Previous</a></li>";
		}
		
		for(var i = startNum; i <=endNum; i++){
			var active = pageNum == i ? "active" : "";
			
			str += "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
		}
		
		str += "</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
	}

	
	function showList(page){
		replyService.getList({bno:bnoValue, page : page||1}, function(replyCnt, list){
			
			console.log("replyCnt : " + replyCnt);
			console.log("list : " + list);

			if(page == -1){
				pageNum = Math.ceil(replyCnt /10.0);
				showList(pageNum);
				return;
			}

			var str="";
			if(list == null || list.length == 0){
				return;
			}
			
			for(var i = 0, len = list.length || 0; i < len; i++){
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
				str += " <div> <div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
				str += "  <small class='float-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str += "   <p>"+list[i].reply+"</p></div></li>";
			}
			
			replyUL.html(str);
			
			showReplyPage(replyCnt);
		});
	}
	
	replyPageFooter.on("click", "li a", function(e){
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		console.log("targetPageNum : " + targetPageNum);
		
		pageNum = targetPageNum;
		
		showList(pageNum);
	});

	
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
			showList(pageNum);
		});
	});

	modalRemoveBtn.on("click", function(e){
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
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
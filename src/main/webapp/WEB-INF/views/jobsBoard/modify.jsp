<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="h3 mb-2 text-gray-800">게시글 수정</h1>
	</div>
</div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">게시글</h6>
	</div>
	<div class="card-body">
		<form role="form" action="/jobsBoard/modify" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		
			<input type="hidden" name="pageNum" value="${cri.pageNum }">
			<input type="hidden" name="amount" value="${cri.amount }">
			<input type="hidden" name="type" value="${cri.type }">
			<input type="hidden" name="keyword" value="${cri.keyword }">
			
			<div class="form-group">
				<label>#</label> <input class="form-control" name="bno" value='<c:out value="${board.bno }" />' readonly="readonly">
			</div>
			<div class="form-group">
				<label>제목</label> <input class="form-control" name="title" value="<c:out value='${board.title }' />">
			</div>	
			
			<div class="form-group">
				<label>내용</label> <textarea class="form-control" name="content" rows="5"><c:out value='${board.content }' /></textarea>
				 
			</div>
			
			<div class="form-group">
				<label>작성자</label>	<input class="form-control" name="writer" value="<c:out value='${board.writer }' />" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label>regDate</label> <input class="form-control" name="regDate" value='<fmt:formatDate value="${board.regDate }" pattern="yyyy/MM/dd"/>' readonly="readonly">
			</div>
			
			<div class="form-group">
				<label>updateDate</label> <input class="form-control" name="updateDate" value='<fmt:formatDate value="${board.updateDate }" pattern = "yyyy/MM/dd"/>' readonly="readonly">
			</div>
			<sec:authentication property="principal" var="pinfo"/>
			<sec:authorize access="isAuthenticated()">
			<c:if test="${pinfo.username eq board.writer }">
				<button type="submit" data-oper="modify" class="btn btn-outline-success">수정</button>
				<button type="submit" data-oper="remove" class="btn btn-outline-danger">삭제</button>
			</c:if>
			</sec:authorize>
				<button type="submit" data-oper="list" class="btn btn-outline-dark">목록</button>
		</form>
	</div>
	
	<style>
		.uploadResult{
					width : 100%;
					background-color : gray;	
				}	
				
				.uploadResult ul{
					display : flex;
					flex-flow : row;
					justify-content : center;
					align-items : center;	
				}
				
				.uploadResult ul li{
					list-style : none;
					padding : 10px;
					align-content :center;
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
					align-item : center;
					top : 0%;
					width : 100%;
					height : 100%;
					background-color : gray;
					z-index : 100;
					background : rgba(255,255,255, 0.5);
				}
				
				.bigPicture{
					position : relative;
					display : flex;
					justify-content : center;
					align-items : center;
				}
				
				.bigPicture img{
					width : 600px;
				}			
	</style>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="card card-default">
				<div class="card-header">
				첨부 파일
				</div>	
				
				<div class="card-body">
					<div class="form-group uploadDiv">
						<input type="file" name="uploadFile" multiple>		
					</div>
					
					<div class="uploadResult">
						<ul>
						
						</ul>
					</div>
				</div>
			</div>	
		</div>	
	</div>
</div>

<script>
$(document).ready(function(){
	(function(){
		var bno = "<c:out value='${board.bno}' />";
		
		$.getJSON("/jobsBoard/getAttachList", {bno:bno}, function(arr){
			var str = "";
			
			
			$(arr).each(function(i, attach){
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-type='"+attach.fileType+"' data-filename='"+attach.fileName+"'>";
					str += "<div>";
					str += "<span>"+attach.fileName+"</span>";
					str += "<button type='button' class='btn btn-warning btn-circle' data-file=\'"+fileCallPath+"\' data-type='image'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div></li>";
				}else{
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'>";
					str += "<div>";
					str += "<span>"+attach.fileName+"</span>";
					str += "<button type='button' class='btn btn-warning btn-circle' data-file=\'"+fileCallPath+"\' data-type='file'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'>";
					str += "</div></li>";
				}	
			});
			$(".uploadResult ul").html(str);
		});
	})();	
	
	$(".uploadResult").on("click", "button", function(e){
		if(confirm("지우시겠습니까?")){
			var targetLi = $(this).closest("li");
			targetLi.remove();
		}
	});

	var regEx = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 크기 초과");
			return false;
		}
		
		if(regEx.test(fileName)){
			alert("해당 확장자의 파일을 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";

	$("input[type='file']").change(function(e){
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : "/uploadAjaxAction",
			processData : false,
			contentType : false,
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			data : formData,
			type : 'POST',
			dataType : 'json',
			success : function(result){
				console.log(result);
				
				showUploadResult(result);
			}
		});
	});
	
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length == 0){
			return;
		}
		
		var uploadUl = $(".uploadResult ul");
		
		var str = "";
		
		$(uploadResultArr).each(function(i, obj){
			if(obj.fileType){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);	
				console.log("fileCallPath : "+fileCallPath);
				str += "<li data-path='"+obj.uploadPath+"' data-filename='"+obj.fileName+"' data-uuid='"+obj.uuid+"' data-type='"+obj.fileType+"'";
				str += "><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+obj.fileCallpath+"\' data-Type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div></li>";
			}else{
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"/"+obj.fileName);	
				console.log("fileCallPath : "+fileCallPath);	
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				console.log("fileLink : "+fileLink);	
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+ obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'";
				str += "><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+obj.fileCallPath+"\' data-Type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/resources/img/attach.png'>";
				str += "</div></li>";
			}
		});

		uploadUl.append(str);
	}	
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form");
	
	$("button").on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if(operation === 'remove'){
			formObj.attr("action", "/jobsBoard/remove");
		}else if(operation ==='list'){
			formObj.attr("action", "/jobsBoard/list").attr("method", "get");
			var pageNum = $("input[name='pageNum']").clone();
			var amount = $("input[name='amount']").clone();
			var type = $("input[name='type']").clone();
			var keyword = $("input[name='keyword']").clone();

			formObj.empty();
			formObj.append(pageNum);
			formObj.append(amount);
			formObj.append(type);
			formObj.append(keyword);
		}else if(operation ==='modify'){
			var str ="";
			
			$(".uploadResult ul li").each(function(i, obj){
				var jobj = $(obj);
				
				str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data('uuid')+"'>";
				str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data('filename')+"'>";
				str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data('type')+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data('path')+"'>";
			});
			formObj.append(str).submit();
		}
		
		formObj.submit();
	});
});

</script>

<%@ include file="../includes/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<form role="form" action="/freeBoard/modify" method="post">
		
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
				<button type="submit" data-oper="modify" class="btn btn-outline-success">수정</button>
				<button type="submit" data-oper="delete" class="btn btn-outline-danger">삭제</button>
				<button type="submit" data-oper="list" class="btn btn-outline-dark">목록</button>
		</form>
	</div>
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
							<div class="form-group uploadDiv">
								<input type="file" name = "uploadFile" multiple="multiple">
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
	var regEx = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 크기 초과");
			return false;
		}	
		
		if(regEx.test(fileName)){
			alert("해당 파일 확장자를 업로드할 수 없습니다.");
			return false;
		}
		
		return true;
	}
	
	$("input[type='file']").change(function(e){
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size))	{
				return false;
			}
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'post',
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
		
		var uploadUL = $(".uploadResult ul");
		
		var str = "";
		
		$(uploadResultArr).each(function(i, obj){
			console.log(obj.uuid);
			console.log(obj.fileType);

			if(obj.fileType){
				var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
				str += "<li data-path='"+obj.uploadPath+"'";
				str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'";
				str +" ><div>";
				str += "<span> "+ obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' "
				str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div>";
				str +"</li>";
			}else{
				var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
			    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
			      
				str += "<li "
				str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"' ><div>";
				str += "<span> "+ obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
				str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "</div>";
				str +"</li>";
			}

	    });
	    uploadUL.append(str);
	}

   </script>
<script type="text/javascript">
$(document).ready(function(){
	(function(){
		var bno = '<c:out value="${board.bno}"/>';
		
		$.getJSON("/freeBoard/getAttachList", {bno: bno}, function(arr){
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
					
					str +="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
					str += "<span>"+attach.fileName+"</span>";
					str+= "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' ";
					str += "class='btn btn-warning btn-circle'> <i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				}else{
					str +="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
					str += "<span>" + attach.fileName + "</span><br/>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'> <i class='fa fa-times'></i></button><br>";
					str += "<img src = '/resources/img/attach.png'></a>";
					str += "</div>";
					str += "</li>";
				}
			});
			
			$(".uploadResult ul").html(str);
		});
	})();
	
	$(".uploadResult").on("click", "button", function(e){
		console.log("delete file!");
		
		if(confirm("지우시겠습니까?")){
			var targetLi = $(this).closest("li");
			targetLi.remove();
		}
	});
	
	
});


</script>

<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form");
	
	$("button").on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if(operation === 'delete'){
			formObj.attr("action", "/freeBoard/delete");
		}else if(operation ==='list'){
			formObj.attr("action", "/freeBoard/list").attr("method", "get");
			var pageNum = $("input[name='pageNum']").clone();
			var amount = $("input[name='amount']").clone();
			var type = $("input[name='type']").clone();
			var keyword = $("input[name='keyword']").clone();

			formObj.empty();
			formObj.append(pageNum);
			formObj.append(amount);
			formObj.append(type);
			formObj.append(keyword);
		}else if(operation === 'modify'){
			console.log("submit clicked");
			var str ="";
			
			$(".uploadResult ul li").each(function(i, obj){
				var jobj = $(obj);
				console.dir(jobj);
				
				str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+ jobj.data("filename")+"'>";
				str += "<input type='hidden' name = 'attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				str += "<input type='hidden' name = 'attachList["+i+"].uploadPath' value = '"+ jobj.data("path")+"'>";
				str += "<input type = 'hidden' name ='attachList["+i+"].fileType' value = '"+jobj.data("type")+"'>";
			});
			formObj.append(str).submit();
		}
		
		formObj.submit();
	});
});

</script>

<%@ include file="../includes/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="h3 mb-2 text-gray-800">게시글 쓰기</h1>
	</div>
</div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">게시글</h6>
	</div>
	<div class="card-body">
		<form role="form" action="/devBoard/register" method="post">
			<div class="form-group">
				<label>제목</label> <input class="form-control" name="title">	
			</div>	
			
			<div class="form-group">
				<label>내용</label> <textarea class="form-control" name="content" rows="5"> </textarea>
				 
			</div>
			
			<div class="form-group">
				<label>작성자</label>	<input class="form-control" name="writer">
			</div>
			
			<button type="submit" class="btn btn-primary">등록</button>
			<button type="reset" class="btn btn-danger">다시 쓰기</button>
		</form>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="card card-defualt">
			<div class="card-header bg-dark text-white">
				첨부파일	
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

<script>
$(document).ready(function(e){
	var formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		
		console.log("submit click!");
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
				console.log("fileCallPath="+fileCallPath+"=");
				str += "<li><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div></li>";
			}else{
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"/"+obj.fileName);	
				console.log("fileCallPath="+fileCallPath+"=");	
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				console.log("fileLink="+fileLink+"=");	
				str += "<li><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/resources/img/attach.png'>";
				str += "</div></li>";
			}
		});
		
		uploadUl.append(str);
	}
});

</script>

<%@ include file="../includes/footer.jsp"%>
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
		<form role="form" action="/jobsBoard/register" method="post">
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
<div class="row">
	<div class="col-lg-12">
		<div class="card card-default">
			<div class="card-header bg-dark text-white">첨부 파일</div>	
			
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
		console.log("submit click");
		
		var str = "";
		
		$(".uploadResult ul li").each(function(i, obj){
			var jobj = $(obj);
			
			console.dir(jobj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		});
		
		formObj.append(str).submit();
	});
	
	var regEx = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize= 5242880;
	
	function checkExtension(fileName, fileSize){
		if(regEx.test(fileName)){
			return false;		
		}
		
		if(fileSize >= maxSize){
			return false;
		}
		
		return true;
	}
	
	$("input[type='file']").change(function(e){
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		for(var i=0; i <files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}	
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : '/uploadAjaxAction',
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
		var uploadUl = $(".uploadResult ul");
		var str ="";
		
		$(uploadResultArr).each(function(i, obj){
			if(obj.fileType){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);	
				
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'";
				str += "><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div></li>";
			}else{
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");	

				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'";
				str += "><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src=/resources/img/attach.png>";
				str += "</div></li>";
			}
		});
		
		uploadUl.append(str);
	}
	
	$(".uploadResult").on("click", "button", function(e){
		console.log("remove click");
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url : "/deleteFile",
			data : {fileName : targetFile, type : type},
			dataType : 'text',
			type : 'post',
			success : function(result){
				alert(result);
				targetLi.remove();
			}
		});
	});
});
</script>


<%@ include file="../includes/footer.jsp"%>
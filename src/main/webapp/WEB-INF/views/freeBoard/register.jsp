<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="h3 mb-2 text-gray-800">게시글 쓰기</h1>
	</div>
</div>


<div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div>

<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

<style>
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">게시글</h6>
	</div>
	<div class="card-body">
		<form role="form" action="/freeBoard/register" method="post">
			<div class="form-group">
				<label>제목</label> <input class="form-control" name="title">	
			</div>	
			
			<div class="form-group">
				<label>내용</label> <textarea class="form-control" name="content" rows="5"> </textarea>
				 
			</div>
			
			<div class="form-group">
				<label>작성자</label>	<input class="form-control" name="writer" value="<sec:authentication property='principal.username' />" readonly="readonly">
			</div>
			
			<button type="submit" class="btn btn-primary">등록</button>
			<button type="reset" class="btn btn-danger">다시 쓰기</button>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="card card-default">
			<div class="card-header bg-dark text-white">첨부파일</div>	
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

<script type="text/javascript">
$(document).ready(function(e){
/*	var formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		
		console.log("submit clicked");
		
		var str = "";
		
		$(".uploadResult ul li").each(function(i, obj){
			var jobj = $(obj);	
			
			console.dir(jobj);
			
			str += "<input type='hidden' name='attachList["+i+".fileName' value = '"+ jobj.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+i+".uuid' value= '"+ jobj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+".uploadPath' value = '"+ jobj.data("path")+"'>";
			str += "<input type='hidden' name='attachList["+i+".fileType' value = '"+ jobj.data("type")+"'>";
		});
		
		formObj.append(str).submit();
	});
*/	
	 var formObj = $("form[role='form']");
	  
	  $("button[type='submit']").on("click", function(e){
	    
	    e.preventDefault();
	    
	    console.log("submit clicked");
	    
	    var str = "";
	    
	    $(".uploadResult ul li").each(function(i, obj){
	      
	      var jobj = $(obj);
	      
	      console.dir(jobj);
	      console.log("-------------------------");
	      console.log(jobj.data("filename"));
	      
	      
	      str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
	      str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
	      str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
	      str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
	      
	    });
	    formObj.append(str).submit();
	  });
	
	$(".uploadResult").on("click", "button", function(e){
		console.log("delete");	
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url : '/deleteFile',
			data : {fileName : targetFile, type : type},
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			dataType : 'text',
			type : 'post',
			success : function(result){
				alert(result);
				targetLi.remove();
			}
		});
	});
	
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
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
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
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
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

});
</script>

<%@ include file="../includes/footer.jsp"%>
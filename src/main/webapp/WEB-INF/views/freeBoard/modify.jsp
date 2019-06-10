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
</div>

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
			formObj.arrt("action", "/freeBoard/list");
			form.Obj.empty();
		}
		
		formObj.submit();
	});
});

</script>

<%@ include file="../includes/footer.jsp"%>
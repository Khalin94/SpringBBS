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
			
			<form id="operForm" action="/jobsBoard/modify" method="get">
				<input type="hidden" id="bno" name="bno" value="<c:out value='${board.bno }'/>">
				<input type="hidden" id="pageNum" name="pageNum" value="<c:out value='${cri.pageNum }' />">
				<input type="hidden" id="amount" name="amount" value="<c:out value='${cri.amount }' />">
				<input type="hidden" name="type" value="${cri.type }">
				<input type="hidden" name="keyword" value="${cri.keyword }">
			</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var operForm = $("#operForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/jobsBoard/modify").submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/jobsBoard/list").submit();
		});
	});
</script>


<%@ include file="../includes/footer.jsp"%>
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
		<form role="form" action="/freeBoard/register" method="post">
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


<%@ include file="../includes/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">자유 게시판</h1>
<p class="mb-4">자유롭게 이야기 할 수 있는 게시판입니다.</p>

<!-- DataTales Example -->
<!--        <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">게시글</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th style="width: 5%">#</th>
                      <th style="width: 50%">Title</th>
                      <th style="width: 25%">Name</th>
                      <th style="width: 15%">Date<th>
                      <th style="width: 5%">조회수</th>
                    </tr>
                  </thead>
                  
                <c:forEach items="${list }" var = "board">
                  <tr>
                  	<td><c:out value="${board.bno }"></c:out></td>
                  	<td><c:out value="${board.title }"></c:out><td>
                  	<td><c:out value="${board.content }"></c:out><td>
                  	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"></fmt:formatDate></td>
                  	<td><c:out value="${board.hits }"></c:out></td>
                  </tr>
				</c:forEach>
                </table>
              </div>
            </div>
          </div>
         -->


<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">
			게시글
			<button id="regBtn" type="button" class="btn btn-sm btn-dark">글쓰기</button>
		</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<td>#</td>
						<td>title</td>
			<!--  		<td>content</td> -->
						<td>writer</td>
						<td>date</td>
						<td>조회수</td>
					</tr>
				</thead>

				<c:forEach items="${list }" var="board">
					<tr>
						<td><c:out value="${board.bno }" /></td>
						<td><a href="/freeBoard/get?bno=<c:out value='${board.bno }' />">
						<c:out value="${board.title }" /></a></td>
			<!--  		<td><c:out value="${board.content }" /></td> -->
						<td><c:out value="${board.writer }" /></td>
						<td><fmt:formatDate value="${board.regDate }"
								pattern="yyyy-MM-dd" /></td>
						<td><c:out value="${board.hits }" /></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</div>


<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel">등록완료</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">등록이 완료되었습니다.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {
				var result = "<c:out value='${result}' />";

				checkModal(result);
				
				history.replaceState({}, null, null);

				function checkModal(result) {
					if (result === '' || history.state) {
						return;
					}
					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + "번이 등록되었습니다.");
					}

					$("#myModal").modal("show");
				}

				$("#regBtn").on("click", function() {
					self.location = "/freeBoard/register";
				});
			});
</script>

<%@ include file="../includes/footer.jsp"%>
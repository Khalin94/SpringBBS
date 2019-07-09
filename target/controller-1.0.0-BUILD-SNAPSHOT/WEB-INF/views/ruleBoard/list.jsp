<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">이용 수칙</h1>
<p class="mb-4"></p>

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
			<button id="regBtn" type="button" class="btn btn-dark float-right">글쓰기</button>
		</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>#</th>
						<th class="w-50">title</th>
			<!--  		<th>content</th> -->
						<th>writer</th>
						<th>date</th>
						<th>조회수</th>
					</tr>
				</thead>

				<c:forEach items="${list }" var="board">
					<tr>
						<td><c:out value="${board.bno }" /></td>
						<td><a class="pageMove" href="<c:out value='${board.bno }' />">
						<c:out value="${board.title }" />
						<b>[  <c:out value="${board.replyCnt }" />  ]</b>	
						</a></td>
			<!--  		<td><c:out value="${board.content }" /></td> -->
						<td><c:out value="${board.writer }" /></td>
						<td><fmt:formatDate value="${board.regDate }"
								pattern="yyyy-MM-dd" /></td>
						<td><c:out value="${board.hits }" /></td>
					</tr>
				</c:forEach>

			</table>

		<div class="row mb-4">
			<form id="searchForm" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="/ruleBoard/list" method="get">
            <div class="input-group">
           		<select name="type" class="form-control">
           			<option value="TC">제목+내용</option>
           			<option value="T">제목</option>
           			<option value="C">내용</option>
           			<option value="W">작성자<option>
           		</select>
              <input type="text" class="form-control bg-light border-0 small" name="keyword" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <input type="hidden" name="pageNum" value="${page.cri.pageNum }">
              <input type="hidden" name="amount" value="${page.cri.amount }">
              <div class="input-group-append">
                <button class="btn btn-primary">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>
       </div>
		
			<div class = "float-right">
				<ul class="pagination">

					<c:if test="${page.prev }">
					 <li class="page-item"><a class="page-link" href="${page.startPage -1 }">Previous</a></li>
					</c:if>	
					
					<c:forEach var="num" begin="${page.startPage}" end="${page.endPage }">
			  		 <li class="page-item ${page.cri.pageNum == num ? 'active' : '' }"><a class="page-link" href="${num }">${num }</a></li>
					</c:forEach>

					<c:if test="${page.next }">
			 		 <li class="page-item"><a class="page-link" href="${page.endPage +1 }">Next</a></li>
					</c:if>

				</ul>
				
				<form id="actionForm" action="/freeBoard/list" method="get">
					<input type="hidden" name="pageNum" value="${page.cri.pageNum }">
					<input type="hidden" name="amount" value="${page.cri.amount }">
					<input type="hidden" name="type" value="${page.cri.type }">
					<input type="hidden" name="keyword" value="${page.cri.keyword }">
				</form>
			</div>

	</div>
</div>

</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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

					if(result == "successRemove"){
						$(".modal-body").html("삭제되었습니다.");
					}else if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + "번이 등록되었습니다.");
					}

					$("#myModal").modal("show");
				}

				$("#regBtn").on("click", function() {
					self.location = "/ruleBoard/register";
				});
				
				var actionForm = $("#actionForm");
				
				$(".page-item a").on("click", function(e){
					e.preventDefault();
					
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					actionForm.submit();
				});
				
				$(".pageMove").on("click", function(e){
					e.preventDefault();
					actionForm.append("<input type='hidden' name='bno' value ='"+$(this).attr("href")+"'>");
					actionForm.attr("action", "/ruleBoard/get");
					actionForm.submit();
				});
				
				var searchForm = $("searchForm");
				
				$("#searchForm button").on("click", function(e){
					searchForm.find("input[name='pageNum']").val(1);
					e.preventDefault();
					
					searchForm.submit();
				});
			});
</script>

<%@ include file="../includes/footer.jsp"%>
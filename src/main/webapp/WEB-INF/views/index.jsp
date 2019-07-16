<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<%@ include file="./includes/header.jsp"%>
<!--         End of Topbar -->

<!-- Begin Page Content -->


<!-- Page Heading -->



<sec:authorize access="isAnonymous()">
<div class="row">
	<div class="col-xl-3 col-md6- mb-4">
		<a href="/user/login"><button class="btn btn-primary btn-lg"
				type="button">로그인하러 가기</button></a>
	</div>
</div>
</sec:authorize>

<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-2 text-gray-800">게시판</h1>
</div>

<div class="row">

	<!-- DataTales Example -->


	<div class="card shadow mb-4 mr-3 ml-4 col-5">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">이용 수칙</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered table-sm" id="dataTable"
					width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ruleList}" var="board" begin="0" end="4">
							<tr>
								<td><c:out value="${board.bno }" /></td>
								<td><a
									href="/ruleBoard/get?bno=<c:out value="${board.bno }" />">
										<c:out value="${board.title }" />
								</a></td>
								<td><c:out value="${board.writer }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="card shadow mb-4 mr-3 ml-4 col-5">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">자유 게시판</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered table-sm" id="dataTable"
					width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${freeList }" var="board" begin="0" end="4">
							<tr>
								<td><c:out value="${board.bno }" /></td>
								<td><a
									href="/freeBoard/get?bno=<c:out value='${board.bno }' />">
										<c:out value="${board.title }" />
								</a></td>
								<td><c:out value="${board.writer }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="w-100"></div>

	<div class="card shadow mb-4 mr-3 ml-4 col-5">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">개발자 게시판</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered table-sm" id="dataTable"
					width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${devList }" var="board" begin="0" end="4">
							<tr>
								<td> <c:out value="${board.bno }"></c:out></td>							
								<td><a href="/devBoard/get?bno=<c:out value='${board.bno }' />">
								<c:out value="${board.title }" />
								</a>
								</td>
								<td> <c:out value="${board.writer }"></c:out> </td>
							</tr>	

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="card shadow mb-4 ml-4 col-5">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">구인 구직</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered table-sm" id="dataTable"
					width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jobsList }" var="board" begin="0" end="4">
							<tr>
								<td> <c:out value="${board.bno }"></c:out> </td>
								<td><a href="/jobsBoard/get?bno="<c:out value="${board.bno }" />">
									<c:out value="${board.title }"></c:out>	
								</a>
								</td>
								<td><c:out value="${board.writer }"></c:out>
								</td>
							</tr>	
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- Content Row -->


<!-- End of Page Wrapper -->
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">Select "Logout" below if you are ready
				to end your current session.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
				<a class="btn btn-primary" href="login.html">Logout</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="./includes/footer.jsp"%>


</html>

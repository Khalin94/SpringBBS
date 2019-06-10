<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="./includes/header.jsp" %>
 <!--         End of Topbar -->

        <!-- Begin Page Content -->
		

          <!-- Page Heading -->
          
          
          <div class="row">
	          <div class="col-xl-3 col-md6- mb-4">
	       		<a href="login"><button class="btn btn-primary btn-lg" type="button">로그인하러 가기</button></a>   
	          </div> 
          </div>

		<div class="d-sm-flex align-items-center justify-content-between mb-4">
       		<h1 class="h3 mb-2 text-gray-800">Tables</h1>
       	</div>

          <div class="row">

          <!-- DataTales Example -->
          	<div class="card shadow mb-4 mr-3">
          	  <div class="card-header py-3">
          	    <h6 class="m-0 font-weight-bold text-primary">자유 게시판</h6>
          	  </div>
          	  <div class="card-body">
          	    <div class="table-responsive">
          	      <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0">
                	<thead>
                		<tr>
                			<th>#</th>
                			<th>제목</th>
                			<th>작성자</th>
                		</tr>	
                	</thead>
                	<tbody>
                	<c:forEach items="${list }" var="board" begin="0" end="4">
                		<tr>
                			<td><c:out value="${board.bno }" /></td>
                			<td><a href="/freeBoard/get?bno=<c:out value='${board.bno }' />"> 
                			<c:out value="${board.title }" /> </a>
                			</td>
                			<td><c:out value="${board.writer }" /></td>
                		</tr>
					</c:forEach>
                	</tbody>
                </table>
          	</div>
          	</div>
          </div>
          
          <div class="card shadow mb-4">
          	  <div class="card-header py-3">
          	    <h6 class="m-0 font-weight-bold text-primary">second bbs</h6>
          	  </div>
          	  <div class="card-body">
          	    <div class="table-responsive">
          	      <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0">
                	<thead>
                		<tr>
                			<th>Name</th>
                			<th>age</th>
                			<th>location</th>
                			<th>update</th>
                		</tr>	
                	</thead>
                	<tbody>
                		<tr>
                			<th>testUser</th>
                			<th>20</th>
                			<th>seoul</th>
                			<th>2019/06/05</th>
                		</tr>
                	</tbody>
                </table>
          	</div>
          	</div>
          </div>
          
          <div class="w-100"></div>
          
          <div class="card shadow mb-4 mr-3">
          	  <div class="card-header py-3">
          	    <h6 class="m-0 font-weight-bold text-primary">third bbs</h6>
          	  </div>
          	  <div class="card-body">
          	    <div class="table-responsive">
          	      <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0">
                	<thead>
                		<tr>
                			<th>Name</th>
                			<th>age</th>
                			<th>location</th>
                			<th>update</th>
                		</tr>	
                	</thead>
                	<tbody>
                		<tr>
                			<th>testUser</th>
                			<th>20</th>
                			<th>seoul</th>
                			<th>2019/06/05</th>
                		</tr>
                		<tr>
                			<th>testUser</th>
                			<th>20</th>
                			<th>seoul</th>
                			<th>2019/06/05</th>
                		</tr>
                		<tr>
                			<th>testUser</th>
                			<th>20</th>
                			<th>seoul</th>
                			<th>2019/06/05</th>
                		</tr>
                	</tbody>
                </table>
          	</div>
          	</div>
          </div>
          
          <div class="card shadow mb-4">
          	  <div class="card-header py-3">
          	    <h6 class="m-0 font-weight-bold text-primary">fourth bbs</h6>
          	  </div>
          	  <div class="card-body">
          	    <div class="table-responsive">
          	      <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0">
                	<thead>
                		<tr>
                			<th>Name</th>
                			<th>age</th>
                			<th>location</th>
                			<th>update</th>
                		</tr>	
                	</thead>
                	<tbody>
                		<tr>
                			<th>testUser</th>
                			<th>20</th>
                			<th>seoul</th>
                			<th>2019/06/05</th>
                		</tr>
                	</tbody>
                </table>
          	</div>
          	</div>
          </div>
          </div>

          <!-- Content Row -->

          
          <!-- End of Page Wrapper -->
	  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

 <%@ include file="./includes/footer.jsp" %>


</html>

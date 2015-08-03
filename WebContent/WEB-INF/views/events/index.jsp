<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Tracker | Events</title>
	<link rel="icon" type="image/png" href="http://paulferrett.com/fontawesome-favicon/generate.php?icon=rocket">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Le styles -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link data-require="bootstrap-css" data-semver="3.3.1" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
    <link data-require="bootstrap@*" data-semver="3.3.2" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<link rel="stylesheet" href="/tracker/public/css/home-styles.css">
	<link rel="stylesheet" href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ include file="../static/layout.jsp" %>
	
	<div class="container">
		<div class="page-header text-center">
		  <h1>Event List <small>This Week</small></h1>
		</div>
		
		<nav class="text-center">
		  <ul class="pagination">
		  	<c:if test="${currentPage>1 }">
		  		<li>
			      <a href='?page=<c:out value="${currentPage-1}" /> ' aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
		  	</c:if>
		  	<c:forEach var="page" begin="${currentPage}" end="${currentPage+5}">
		  		<c:if test="${page<=pageCount }">
		  			<c:choose>
			  			<c:when test="${currentPage==page }">
			  				<li class="active"><a href='?page=<c:out value="${page}" /> '><c:out value="${page}" /> </a>
			  			</c:when>
			  			<c:otherwise>
			  				<li><a href='?page=<c:out value="${page}" /> '><c:out value="${page}" /> </a>
			  			</c:otherwise>
			  		</c:choose>
		  		</c:if>
		  	</c:forEach>
		  	<c:if test="${currentPage+5<pageCount }">
		  		<li>
			      <a href='?page=<c:out value="${currentPage+1}" /> ' aria-label="Previous">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		  	</c:if>
		  </ul>
		</nav>
		
		<table class="table table-striped table-hover">
			<thead>
				<th>Event Name</th> <th>Environment</th> <th>Type</th> <th>Expected Start</th> <th>Expected End</th> <th>Created By</th> <th>Executed By</th>
			</thead>
			<tbody>
				<c:forEach items="${events}" var="event">
					<tr>
						<td><a href='events/<c:out value="${event.getId() }" />'> <c:out value="${event.getName() }" /> </a></td>
						<td><c:out value="${event.getEnvironment() }"></c:out></td>
						<td><c:out value="${event.getEvent_type() }"></c:out></td>
						<td><c:out value="${event.getExpected_start().toString('yyyy-MM-dd HH:mm:ss') }"></c:out></td>
						<td><c:out value="${event.getExpected_end().toString('yyyy-MM-dd HH:mm:ss') }"></c:out></td>
						<td>lokesh cherukuri</td>
						<td>lokesh cherukuri</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(function()
	{
		$('#events-table').dataTable();
	});
</script>
</html>
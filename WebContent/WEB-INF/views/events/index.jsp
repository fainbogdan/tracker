<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<script src="/tracker/public/Js/eventsIndex.js"></script>
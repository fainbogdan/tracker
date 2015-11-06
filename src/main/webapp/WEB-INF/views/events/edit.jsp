<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
	<div class="row">
	    <div class="col-md-4">
	        <h3>Setup phase</h3>
	        <div id="sortable1" class="sortable connectedSortable"  data-phase='setup' data-event_id='<c:out value="${event.getId() }" />'>
	        	<c:forEach items="${event.sortedChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='setup' }">
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}">
		        							<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
		        						</c:when>
		        						<c:otherwise>
		        							<strike><a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a></strike>
		        						</c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h pointer"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
	        				</div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	    <div class="col-md-4">
	        <h3>execute phase</h3>
	        <div id="sortable2" class="sortable connectedSortable"  data-phase="execute" data-event_id='<c:out value="${event.getId() }" />'>
	            <c:forEach items="${event.sortedChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='execute' }">
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />' > 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}">
		        							<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
		        						</c:when>
		        						<c:otherwise>
		        							<strike><a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a></strike>
		        						</c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h pointer"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
	        				</div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	    <div class="col-md-4">
	        <h3>teardown phase</h3>
	        <div id="sortable3" class="sortable connectedSortable"  data-phase="teardown" data-event_id='<c:out value="${event.getId() }" />'>
	           <c:forEach items="${event.sortedChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='teardown' }">
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />' > 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}">
		        							<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
		        						</c:when>
		        						<c:otherwise>
		        							<strike><a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a></strike>
		        						</c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o pointer"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h pointer"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
	        				</div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	</div>
	
	<div class="done-editing text-center">
		<a href='../<c:out value="${event.getId() }" />' class="btn btn-primary">Done Editing Checklist</a>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" id="save" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
<script src="/tracker/public/Js/eventsEdit.js"></script>
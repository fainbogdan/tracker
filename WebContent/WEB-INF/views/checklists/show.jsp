<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<div class="page-header">
		<ul id="nestedlist" class="list-group">
		    <li> <h2>
		    		<a href='/tracker/events/<c:out value="${checklist.getEvent().getId() }"></c:out>' class="link"> <i class="fa fa-caret-down"></i> <c:out value="${checklist.getEvent().getName() }"></c:out> </a>
				    <ul>
				        <li> <small> <i class="fa fa-caret-right"></i> <c:out value="${checklist.getName() }"></c:out> </small> </li>
				    </ul>
			    </h2>
		    </li>
		</ul>
	</div>
	
	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
			<dl>
				<dt><i class="fa fa-cubes"></i> Environment</dt> 
				<dd><c:out value="${checklist.getEvent().getEnvironment() }"></c:out> </dd>
				
				<dt><i class="fa fa-arrow-right fa-fw"></i> Phase</dt> 
				<dd><c:out value="${checklist.getPhase() }"></c:out> </dd>
			</dl>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
			<dl>
				<dt><i class="fa fa-user fa-fw"></i> Created By</dt> 
				<dd><c:out value="${checklist.getCreator().fullname() }"></c:out></dd>
				
				<dt><i class="fa fa-circle-o-notch fa-fw"></i> Completion status</dt> 
				<dd>
					<c:choose>
						<c:when test="${empty checklist.getCompleted() }">
							Not started
						</c:when>
						<c:when test="${checklist.getCompleted() eq 'Y' }">
							Completed
						</c:when>
						<c:otherwise>
							Skipped
						</c:otherwise>
					</c:choose>
				</dd>
			</dl>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
			<dl>
				<dt><i class="fa fa-user fa-fw"></i> Completed On</dt> 
				<dd><c:out value="${checklist.getCompleted_on() }"></c:out> </dd>
				
				<dt><i class="fa fa-user fa-fw"></i> Completed By</dt> 
				<dd><c:out value="${checklist.getFinisher().fullname() }"></c:out> </dd>
			</dl>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<dl>
				<dt><i class="fa fa-info-circle fa-fw"></i> Details</dt>
				<dd><c:out value="${checklist.getDetails() }"></c:out></dd>
				
				<dt><i class="fa fa-info fa-fw"></i> Execution Note</dt> 
				<dd><c:out value="${checklist.getSkipped_note() }"></c:out></dd>
			</dl>
		</div>
	</div>
	<hr/>
	
	<div class="row">
		<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
			<button class="btn btn-primary btn-block" id="attachments">Have something to attach?</button>
		</div>
		
		<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
		</div>
	</div>
</div>

<div class="row">
	<form role="form" action='/tracker/checklist/<c:out value="${checklist.getId() }" />' method="post" enctype="multipart/form-data">
		<input type="file" name="attachedFiles" id="attachedFiles" />
		<input type="submit" id="submitFiles" />
	</form>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body" style="min-height: 400px;">
      </div>
    </div>
  </div>
</div>
<!-- Modal -->

<script src="/tracker/public/Js/checklistShow.js"></script>
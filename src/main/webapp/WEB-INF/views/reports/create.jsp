<div class="container">
	<div class="page-header">
        <h1>Emergency <small> Create an Emergency event</small></h1>
    </div>
	<div class="row default-filter-group">
		<div class="col-sm-12">
			<div class="btn-group btn-group-justified" role="group" aria-label="...">
				<div class="btn-group">
			    	<button type="button" class="btn btn-default date-range-filter">All Events</button>
			    </div>
			    <div class="btn-group">
			      	<button type="button" class="btn btn-default date-range-filter">This Month</button>
			    </div>
			    <div class="btn-group">
			    	<button type="button" class="btn btn-default date-range-filter">Last 2 Weeks</button>
			    </div>
			    <div class="btn-group">
			      	<button type="button" class="btn btn-default date-range-filter">Last Week</button>
			    </div>
			    <div class="btn-group">
			      	<button type="button" class="btn btn-default date-range-filter">This Week</button>
			    </div>
			    <div class="btn-group">
			      	<button type="button" class="btn btn-default date-range-filter">Future Events</button>
			    </div>
			</div>
		</div>
		<div class="col-sm-8 col-sm-offset-2 top-buffer">
			<form role="form">
		    	<div class="input-group">
				   <input type="text" class="form-control" id="keywords" placeholder="Search by keywords in title or description">
				   <span class="input-group-btn">
				        <button class="btn btn-primary" type="button" id="search">Search Events</button>
				   </span>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="/tracker/public/Js/createReport.js"></script>
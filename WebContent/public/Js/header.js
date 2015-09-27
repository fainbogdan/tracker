/**
 * 
 */

(function(){
	
	 $(".dropdown-toggle").dropdown();
	 
	 $('body').on('click', function (e) {
	    if (!$('#notificationItems,#notification-model').is(e.target) 
	        && $('#notificationItems,#notification-model').has(e.target).length === 0 
	        && $('.open').has(e.target).length === 0
	    ) {
	        $('#notificationItems').removeClass('open');
	    }
	 });
	 
	 $('.notification-bell').on('click',function(){
		 if(! $(this).parent().hasClass('open'))
			 getEventsToApprove();
		 
		 $(this).parent().toggleClass('open');
	 });
	 
	 
	 //notification action
	 $(document).on('click','.notificationAction',function()
	 {
		 var item=$(this).parent().parent();
		 var icon=$(this);
		 
		 /*show event details*/
		 $.ajax({
                url:'/tracker/events/'+$(this).attr('data-eventId')+'/json', 
                method:'GET',
                dataType: 'json',
                success:function(data, status, jqxhr) 
                {
                	var modal_title='<span>Do you want to '+$(icon).attr('data-action')+' this event</span>';
        	        $('#notification-model .modal-title').html(modal_title);
    		        
    		        var modal_body='<table class="table table-bordered">'+
                                '<tr><td><b>Event Name</b></td> <td><a href="/tracker/events/'+data.id+'">'+data.name+'</a></td></tr>'+
                                '<tr><td><b>Event description</b></td> <td>'+data.description+'</td></tr>'+
                                '<tr><td><b> Event type</b></td> <td>'+data.event_type+'</td></tr>'+
                                '<tr><td><b> Environment</b></td> <td>'+data.environment+'</td></tr>'+
                                '<tr><td><b> Expected start</b></td> <td>'+data.expected_start+'</td></tr>';
                 if(data.expected_end!=null)
                    	modal_body+='<tr><td><b> Expected end</b></td> <td>'+data.expected_end+'</td></tr>';
                 if(data.actual_start!=null)
     				modal_body+='<tr><td><b> Actual start</b></td> <td>'+data.actual_start+'</td></tr>';
                    if(data.actual_end!=null)
                    	modal_body+='<tr><td><b> Actual end</b></td> <td>'+data.actual_end+'</td></tr>';
                    	modal_body+='</table>';
                    
                    $('#notification-model .modal-body').html(modal_body);
                    $('#notification-model.modal').modal('show');
                }
         });
		
		 
		 //Confirm modal
	        $('#notification-model.modal').on('click', '#notification-save', function (e) {
	        	
	        	 $('#notification-model.modal').modal('hide');
	        	 
		   		 if($(icon).hasClass('fa-check-circle-o'))
		   			 $(icon).removeClass('col-sm-6 fa-check-circle-o').addClass('col-sm-12 fa-spinner fa-spin').siblings().remove();
		   		 else if($(icon).hasClass('fa-times-circle-o'))
		   			 $(icon).removeClass('col-sm-6 fa-times-circle-o').addClass('col-sm-12 fa-spinner fa-spin').siblings().remove();
		   		 
		   		 var id=$(icon).attr('data-eventId');
		   		 var action=$(icon).attr('data-action');
		   		 
		   		 $.ajax({
		   			 url:'/tracker/events/'+id+'/approve',
		   			 method:'put',
		   			 contentType:'application/json',
		   			 dataType:'json',
		   			 beforeSend: function (xhr) {
		   	    		 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		   	    	 },
		   			 data:JSON.stringify({'id':id,'action':action}),
		   			 success:function(data){
		   				$(item).remove();
		   				if($('.notificationItem').length>0){
		   					$('.badge').text($('.notificationItem').length);
		   				}
		   				else{
		   					$('.badge').remove();
		   					$('#notificationItems .dropdown-menu').append('<li><h5>No pending events</h5></li>');
		   				}
		   			 }
		   		 });
	        	
	        });//modal confirmed
		 	
	 });//notification action completed
	 
	 
	 setInterval(function(){ 
		 
		 getEventsToApprove();
	 
	 }, 180000);
	 
	 function getEventsToApprove() {
		 var username=$('input[name="username"]').val();
		 $.ajax({
			 url:'/tracker/events/eventsToApprove?user='+username,
			 method:'get',
			 dataType:'json',
			 beforeSend: function (xhr) {
	    		 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	    	 },
			 success:function(events){
				 $('#notificationItems .dropdown-menu').empty();
				 $('.badge').remove();
				 if(events.length>0){
					 $('.notification-bell').after('<span class="badge badge-notify">'+events.length+'</span>');
					 $(events).each(function(index,event) {
						 var x='<li class="notificationItem">'+							 
								 	'<h4> <a href="/tracker/events/'+event.id+'" target="_blank">'+event.name+'</a> </h4>'+
								 	'<span>'+event.description+'</span>'+
								 	'<div class="options row">'+
								 		'<i data-eventId="'+event.id+'" data-action="approve" class="col-sm-6 text-center fa fa-check-circle-o fa-2x text-success pointer notificationAction"></i>'+ 
								 		'<i data-eventId="'+event.id+'" data-action="reject" class="col-sm-6 text-center fa fa-times-circle-o fa-2x text-danger pointer notificationAction"></i>'+ 
								 	'</div>'+
							 	'</li>'+
						      	'<li class="divider"></li>';
						 
						 $('#notificationItems .dropdown-menu').append(x);
					})
				 }
				 else{
					 $('#notificationItems .dropdown-menu').append('<li><h5>No pending events</h5></li>');
				 }
			 }
		 });
	}
	 
	 
	 $(document).on('hidden.bs.modal','#notification-model.modal', function (e) {
		 $(this).unbind();               // removing modal from DOM to avoid caching.
	 });
	 
})();

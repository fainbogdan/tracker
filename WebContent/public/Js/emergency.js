/**
 * 
 */


(function()
{
	 $('#datetimepicker1').datetimepicker();
	 
	 $('li.event').on('click',function(event)
	 {
		 var event_id=$(this).attr('event_id');
		 event.preventDefault();
		 $.ajax({
			url:'/tracker/events/'+event_id+'/json' ,
			method:'get',
			contentType:'application/json',
			dataType:'json',
			success:function(data)
			{
				var modal_title='';
				if(data.event_type=="emergency")
            		modal_title+='<i class="fa fa-exclamation-triangle emergency-type"></i>';
        		else
        			modal_title+='<i class="fa fa-clock-o planned-type"></i>';
        			
				modal_title+=' <span>'+data.name+'</span>';
		        $('.modal-title').html(modal_title);
		        var modal_body='<table class="table table-bordered">'+
					                '<tr><td><b>Event Name</b></td> <td><a href="/tracker/events/'+data.id+'">'+data.name+'</a></td></tr>'+
					                '<tr><td><b>Event description</b></td> <td>'+data.description+'</td></tr>'+
					                '<tr><td><b> Event type</b></td> <td>'+data.event_type+'</td></tr>'+
					                '<tr><td><b> Environment</b></td> <td>'+data.environment+'</td></tr>'+
					                '<tr><td><b> expected start</b></td> <td>'+data.expected_start+'</td></tr>';
			    
                if(data.expected_end!=null)
			    	modal_body+='<tr><td><b> Expected end</b></td> <td>'+data.expected_end+'</td></tr>';
				if(data.actual_start!=null)
			    	modal_body+='<tr><td><b> Actual start</b></td> <td>'+data.actual_start+'</td></tr>';
			    if(data.actual_end!=null)
			    	modal_body+='<tr><td><b> Actual end</b></td> <td>'+data.actual_end+'</td></tr>';
			    modal_body+='</table>';

		        $('.modal-body').html(modal_body);
		        $('#myModal').modal('show')
			}
		 });
	 });
})();

/**
 * 
 */

(function(){
	
	$('#datetimepicker1').datetimepicker();
    $('#datetimepicker2').datetimepicker();
    $("#datetimepicker1").on("dp.change", function (e) {
        $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker2").on("dp.change", function (e) {
        $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
    });
    
    var events=[];
    /****** Ajax call starts **********/
    $.ajax(
    {        //get start time for an event
        url:'/tracker/events/month',
        method:'GET',
    	dataType: 'json',
    	success: function(data) 
    	{
    		$(data).each(function(index,obj)
	        {
	            var event=new Object();
	            event.id=obj.id;
	            event.start=getStartDate(obj);
	            event.end=getEndDate(obj);
	            event.title=obj.name;
	            if(obj.event_type=="emergency")
	                event.color="red";
	            else
	                event.color="light blue";
	
	            if(obj.actual_end!=null)
	                event.color="grey";
	
	            events.push(event);
	        });
	
    		var date = new Date(), y = date.getFullYear(), m = date.getMonth();
	        $('#events-calendar').fullCalendar(
	        {
	            // put your options and callbacks here
	            timezone:"local",
	            lang: 'es',
	            fixedWeekCount:false,
	            header: {
	                left: 'prev,next today',
	                center: 'title',
	                right: 'month,agendaWeek,agendaDay'
	            },
	            slotEventOverlap:false,
	            allDay:true,
	            firstDay:new Date(y, m, 1).getDay(),
	            events:events,
	            eventLimit: true, // for all non-agenda views
		            views: {
		                agenda: {
		                    eventLimit: 1 // adjust to 6 only for agendaWeek/agendaDay
		                },
		            month: {
	                    eventLimit: 1 // adjust to 6 only for agendaWeek/agendaDay
	                }
	            },
	            eventRender: function(event, element)
	            {
	                $(element).css({'text-align':'center','cursor':'pointer'});
	            },
	            /****** Event click starts **********/
	            eventClick: function(calEvent, jsEvent, view) 
	            {
	                var table="";
	                $.ajax(
	                {
		                url:'/tracker/events/'+calEvent.id+'/json', 
		                method:'GET',
		                dataType: 'json',
		                success:function(data, status, jqxhr) 
		                {
		                	var modal_title='';
		    				if(data.event_type=="emergency")
		                		modal_title+='<i class="fa fa-exclamation-triangle emergency-type"></i>';
		            		else
		            			modal_title+='<i class="fa fa-clock-o planned-type"></i>';
	                			
		                	modal_title+='<span> '+data.name+'</span>';
		    		        $('.modal-title').html(modal_title);
		    		        
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
		                    
		                    $('.modal-body').html(modal_body);
		    		        $('#myModal').modal('show');
		                }
	              });
	            }
	            /****** Event click ends **********/
        	});
    	}
    });
    /****** Ajax call ends **********/  
    
    getStartDate=function(obj)
	{
    	if(obj.expected_end==null)
    		return obj.expected_start;
		var start = new Date(obj.expected_start);
		var end = new Date(obj.expected_end);
		if((Math.abs(start - end) / 36e5)>=8)
		{
			var formattedStart = start.getFullYear() +"-"+ ("0" + (start.getMonth() + 1)).slice(-2) +"-"+ ("0" + start.getDate()).slice(-2);
			return formattedStart;
		}
		else
			return obj.expected_start;
	};
	
	getEndDate=function(obj)
	{
		if(obj.expected_end==null)
    		return null;
		var start = new Date(obj.expected_start);
		var end = new Date(obj.expected_end);
		if((Math.abs(start - end) / 36e5)>=8)
		{
			var formattedEnd = end.getFullYear() +"-"+ ("0" + (end.getMonth() + 1)).slice(-2) +"-"+ ("0" + end.getDate()).slice(-2);
			return formattedEnd;
		}
		else
			return obj.expected_end;
	};
    
})();

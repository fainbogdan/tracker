(function() {
	$('.date-range-filter,#search').click(function() {
		$('.default-filter-group').removeClass('default-filter-group');
		
		$.ajax({
			url:'/tracker/reports/create/search',
			method:'post',
			contentType:'application/json',
			dataType:'json',
			data:JSON.stringify({keyword:$('#keywords').val()}),
			beforeSend: function (xhr) {
	    		 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	    	},
			success:function(data){
				console.log(data);
			}
		});
	});


})();
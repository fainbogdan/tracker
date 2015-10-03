/**
 * 
 */

(function()
{
	$('#example').DataTable();

    $("#datetimepicker1").on("dp.change", function (e) {
        $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker2").on("dp.change", function (e) {
        $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
    });
    
    $("#PopS").popover({
        html: true
    }).on('shown.bs.popover', function () {
        $('#datetimepicker1').datetimepicker();
        $('#datetimepicker2').datetimepicker();
    });
    
})();
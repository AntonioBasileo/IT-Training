//inizializza slider
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.slider');
	var instances = M.Slider.init(elems, true, 400, 500, 5000);
});   

//dropdown - home
$( document ).ready(function () {
	$(".dropdown-trigger").dropdown();
})

//badges

$(document).ready(function(){
  $('.collapsible').collapsible();
});

//date picker
$(document).ready(function(){
  $('.datepicker').datepicker({
	  format: 'dd/mm/yyyy',
	  maxDate: new Date()
  });
});

//select
$(document).ready(function(){
  $('select').formSelect();
});
//inizializza slider
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.slider');
	var instances = M.Slider.init(elems, true, 400, 500, 5000);
});

//badges
$(document).ready(function(){
  $('.collapsible').collapsible();
});


//select
$(document).ready(function(){
  $('select').formSelect();
}); 

//toast  
function mostraToast(testo) {
	if(testo == "") {
		return;
	}
		  
	$(document).ready(function() {
		M.toast({html: testo});
	});
}
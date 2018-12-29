//inizializza slider
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.slider');
	var instances = M.Slider.init(elems, true, 400, 500, 5000);
});


//sidenav
$(document).ready(function(){
    $('.sidenav').sidenav();
 });


//select
$(document).ready(function(){
  $('select').formSelect();
}); 


//toast
M.toast({html: 'I am a toast!'})
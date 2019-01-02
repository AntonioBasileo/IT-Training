//inizializza slider
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.slider');
	var instances = M.Slider.init(elems, true, 400, 500, 5000);
});

//badges
$(document).ready(function(){
    $('.collapsible').collapsible();
  });


//sidenav
$(document).ready(function(){
    $('.sidenav').sidenav();
 });


//select
$(document).ready(function(){
  $('select').formSelect();
}); 


//modals
$(document).ready(function(){
  $('.modal').modal();
  
  console.log("ciao");
});

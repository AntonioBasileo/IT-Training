
  
  $(document).ready(function() {
	var toast = document.getElementById("toast");
	
	setTimeout(function() {
		if(toast.value == "")
			return
		
		M.toast({html: toast.value});
	}, 500);

  });
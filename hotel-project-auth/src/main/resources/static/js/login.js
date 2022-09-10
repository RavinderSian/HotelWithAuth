'use strict';

const btnLogin = document.querySelector('.btn-login');


btnLogin.addEventListener('click', function(e){
	e.preventDefault();
	document.querySelector('.login-box').classList.toggle('hidden');
});

// this is the id of the form
$(".login-form").submit(function(e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var actionUrl = form.attr('action');
    
    var xhr = new XMLHttpRequest();
    
    $.ajax({
        type: "POST",
        url: actionUrl,
        data: form.serialize(), // serializes the form's elements.
         xhr: function() {
         return xhr;
    	},
        success: function(){
			if (!xhr.responseURL.includes('/failedlogin')){
				document.forms["loginForm"].submit();
			}
			else {
				document.querySelector('.login-error-text').classList.toggle('hidden');
			}
		},
    });
    
});
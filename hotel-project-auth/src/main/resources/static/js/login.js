'use strict';

const btnLogin = document.querySelector('.btn-login');

const body = document.body;

const loginBox = document.querySelector('.login-box');

btnLogin.addEventListener('click', (e) => {
	e.preventDefault();
	
	loginBox.classList.toggle('hidden');
	
});

document.addEventListener("click", (e) => {
  //If the login button also triggers the hidden class to be added the box never appears
  //So a second condition is needed to ensure that does not happen
  const isClickInside = loginBox.contains(e.target) || btnLogin.contains(e.target);

  if (!isClickInside) {
	loginBox.classList.add('hidden');
  }
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
				document.querySelector('.login-error-text').classList.remove('hidden');
			}
		},
    });
    
});
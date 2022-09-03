'use strict';

const btnLogin = document.querySelector('.btn-login');


btnLogin.addEventListener('click', function(e){
	e.preventDefault();
	document.querySelector('.login-box').classList.toggle('hidden');
	console.log(document.querySelector('.login-box').classList);

	btnLogin.element
	
});
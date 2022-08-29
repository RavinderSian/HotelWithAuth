'use strict';

const submitBtn = document.querySelector(".btn-submit");
let codeToSubmit = document.querySelector(".code-submit");

//clears field when a code is submitted 
codeToSubmit.value = '';


submitBtn.addEventListener('click', function(e){
	
	if (!/[A-Za-z]{8}/.test(codeToSubmit.value) || codeToSubmit.value.length === 0){
		e.preventDefault();
		alert("Please enter a valid discount code");
		codeToSubmit.value = '';
	}
	
});
"use strict";

const slotButton = document.querySelector(".btn-slot");
const slot1 = document.querySelector(".slot--1");
const slot2 = document.querySelector(".slot--2");
const slot3 = document.querySelector(".slot--3");

let slotIcon = 1;

var i = 1; //  set your counter to 1


const changeSlotDelay = function() {
	
	let slotIcon = 1;
	
	(function loopIt(i) {
	  setTimeout(function (){
		
		  //roll slots
	      slot1.src = `/images/slot-item-${Math.trunc(Math.random() * 3 + 1)}.svg`;
	      slot2.src = `/images/slot-item-${Math.trunc(Math.random() * 3 + 1)}.svg`;
	      slot3.src = `/images/slot-item-${Math.trunc(Math.random() * 3 + 1)}.svg`;

	      slotIcon+=1;
	      i+=1;
	      
	      //console.log(i);
	      if (slotIcon > 4)slotIcon=1;
	      if(i < 20)  loopIt(i);
	    }, 100);
	})(0)
	
}

const checkWinner = function () {
	
	(function winner() {
	  setTimeout(function (){
		
		  console.log("check winner"); 
		  console.log(slot1.src); 
  		  console.log(slot2.src);
  		  
  		  if (slot1.src === slot2.src){
			 console.log("winner");
		  }
	      
	      //console.log(i);
	    }, 10000);
	})()
}

slotButton.addEventListener("click", function () {
  	
  	console.log("clicked");
  	
	changeSlotDelay();
	
//	console.log("delay");
//	console.log(slot1.src); 
//  	console.log(slot2.src);
//	
//	if (slot1.src === slot2.src){
//	  console.log("winner");
//  	}

//  console.log("out of loop");
//  console.log(slot1.src); 
//  console.log(slot2.src);

	checkWinner();

});

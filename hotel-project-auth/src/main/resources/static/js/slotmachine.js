"use strict";

const slotButton = document.querySelector(".btn-slot");
const slot1 = document.querySelector(".slot--1");
const slot2 = document.querySelector(".slot--2");
const slot3 = document.querySelector(".slot--3");

let slotIcon = 1;

console.log(slotButton);

const changeSlot = function (count) {
  console.log("clicked");
  slot1.src = `/images/slot-item-${count}.svg`;
};

var i = 1; //  set your counter to 1


const changeSlotDelay = function() {
	
	let slotIcon = 1;
	//const slotTest = document.querySelector(`.slot--${slot}`);
	//console.log(slotTest);
	
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

slotButton.addEventListener("click", function () {
  let roll = true;
  let count = 0;

  while (roll) {

	var i = 0;
	
	changeSlotDelay();

    count += 1;
    if (count === 4){
		roll = false;
		//clearInterval(intervalSet);
	} 

  }
  
	if (slot1.src === slot2.src && slot2.src === slot3.src){
		console.log("winner");
	}
  
});

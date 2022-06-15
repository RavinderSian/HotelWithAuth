"use strict";

const slotButton = document.querySelector(".btn-slot");
const slot1 = document.querySelector(".slot--1");
let slotIcon = 1;

console.log(slotButton);

slotButton.addEventListener("click", function () {
  console.log("clicked");
  console.log("button clicked");
  slotIcon += 1;
  slot1.src = `/images/slot-item-${slotIcon}.svg`;
});

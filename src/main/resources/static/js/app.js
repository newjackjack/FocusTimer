/**
 * 
 */


let date = new Date();
let year = date.getFullYear();
let month = date.getMonth();

const day = document.querySelector(".calendar-dates");

const currdate = document
	.querySelector(".calendar-current-date");

const prenexIcons = document
	.querySelectorAll(".calendar-navigation span");

// Array of month names
const months = [
	"January",
	"February",
	"March",
	"April",
	"May",
	"June",
	"July",
	"August",
	"September",
	"October",
	"November",
	"December"
];

// Function to generate the calendar
const manipulate = () => {

	// Get the first day of the month
	let dayone = new Date(year, month, 1).getDay();

	// Get the last date of the month
	let lastdate = new Date(year, month + 1, 0).getDate();

	// Get the day of the last date of the month
	let dayend = new Date(year, month, lastdate).getDay();

	// Get the last date of the previous month
	let monthlastdate = new Date(year, month, 0).getDate();

	// Variable to store the generated calendar HTML
	let lit = "";

	// Loop to add the last dates of the previous month
	for (let i = dayone; i > 0; i--) {
		lit +=
			`<li class="inactive">${monthlastdate - i + 1}</li>`;
	}

	// Loop to add the dates of the current month
	for (let i = 1; i <= lastdate; i++) {

		// Check if the current date is today
		let isToday = i === date.getDate()
			&& month === new Date().getMonth()
			&& year === new Date().getFullYear()
			? "active"
			: "";
		lit += `<li class="${isToday}">${i}</li>`;
	}

	// Loop to add the first dates of the next month
	for (let i = dayend; i < 6; i++) {
		lit += `<li class="inactive">${i - dayend + 1}</li>`
	}

	// Update the text of the current date element 
	// with the formatted current month and year
	currdate.innerText = `${months[month]} ${year}`;

	// update the HTML of the dates element 
	// with the generated calendar
	day.innerHTML = lit;
}

manipulate();

// Attach a click event listener to each icon
prenexIcons.forEach(icon => {

	// When an icon is clicked
	icon.addEventListener("click", () => {

		// Check if the icon is "calendar-prev"
		// or "calendar-next"
		month = icon.id === "calendar-prev" ? month - 1 : month + 1;

		// Check if the month is out of range
		if (month < 0 || month > 11) {

			// Set the date to the first day of the 
			// month with the new year
			date = new Date(year, month, new Date().getDate());

			// Set the year to the new year
			year = date.getFullYear();

			// Set the month to the new month
			month = date.getMonth();
		}

		else {

			// Set the date to the current date
			date = new Date();
		}

		// Call the manipulate function to 
		// update the calendar display
		manipulate();
	});
});

//-------------------------- countdown timer --------------------------//
let hrs = document.getElementById("hours");
let mins = document.getElementById("minutes");
let secs = document.getElementById("seconds");
let display = document.getElementById("display");
let start = document.getElementById("start");
let timer;

      function displaymin() {
        display.innerHTML =
          (hrs.value > 1 ? hrs.value : "0" + hrs.value) +s
          ":" +
          (mins.value > 9 ? mins.value : "0" + mins.value) +
          ":" +
          (secs.value > 9 ? secs.value : "0" + secs.value);
      }

      function watch() {
        start.disabled = true;//the start button will be disabled once the timer starts
        hrs.disabled = true;
        mins.disabled = true;
        secs.disabled = true;
        
        var date1 = new Date();//today's date
        
        var countDownDate = new Date(); //time from which the countdown will start
        
        countDownDate.setTime(
          date1.getTime() + hrs.value * 60 * 60 * 1000 + 
            mins.value * 60 * 1000 +
            secs.value * 1000 +
            1 * 1000
        );

        // Update the count down every 1 second
        var x = setInterval(function () {
          // Get today's date and time
          var now = new Date().getTime();

          // Find the distance between now and the count down date
          var distance = countDownDate - now;

          // Time calculations for minutes and seconds
          var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))
          var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
          var seconds = Math.floor((distance % (1000 * 60)) / 1000);

          // Display the result in the element with id="display"
          display.innerHTML =
            (hours.value > 1 ? hours : "0" + hours) +
            ":" +
            (minutes > 9 ? minutes : "0" + minutes) +
            ":" +
            (seconds > 9 ? seconds : "0" + seconds);
          
          var audio = new Audio("timer.mp3");

          // If the count down is finished, play audio file
          if (display.innerHTML == "00:00:00") {
            clearInterval(x);
            audio.play()
            start.disabled = false;
            hrs.disabled = false;
        	mins.disabled = false;
        	secs.disabled = false;
          }
        }, 1000);
      }

const container = document.querySelector(".container");
const seats = document.querySelectorAll(".row .seat:not(.sold)");
const count = document.getElementById("count");
const total = document.getElementById("total");
const tripSelect = document.getElementById("trip");

populateUI();

let ticketPrice = +tripSelect.value;

// Save selected trip index and price
function settripData(tripIndex, tripPrice) {
  localStorage.setItem("selectedtripIndex", tripIndex);
  localStorage.setItem("selectedtripPrice", tripPrice);
}

// Update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".row .seat.selected");

  const seatsIndex = [...selectedSeats].map((seat) => [...seats].indexOf(seat));

  localStorage.setItem("selectedSeats", JSON.stringify(seatsIndex));

  const selectedSeatsCount = selectedSeats.length;

  count.innerText = selectedSeatsCount;
  total.innerText = selectedSeatsCount * ticketPrice;

  settripData(tripSelect.selectedIndex, tripSelect.value);
}


// Get data from localstorage and populate UI
function populateUI() {
  const selectedSeats = JSON.parse(localStorage.getItem("selectedSeats"));

  if (selectedSeats !== null && selectedSeats.length > 0) {
    seats.forEach((seat, index) => {
      if (selectedSeats.indexOf(index) > -1) {
        console.log(seat.classList.add("selected"));
      }
    });
  }

  const selectedtripIndex = localStorage.getItem("selectedtripIndex");

  if (selectedtripIndex !== null) {
    tripSelect.selectedIndex = selectedtripIndex;
    console.log(selectedtripIndex)
  }
}
console.log(populateUI())
// trip select event
tripSelect.addEventListener("change", (e) => {
  ticketPrice = +e.target.value;
  settripData(e.target.selectedIndex, e.target.value);
  updateSelectedCount();
});

// Seat click event
container.addEventListener("click", (e) => {
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("sold")
  ) {
    e.target.classList.toggle("selected");

    updateSelectedCount();
  }
});

// Initial count and total set
updateSelectedCount();
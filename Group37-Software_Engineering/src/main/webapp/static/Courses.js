// Initially hide the alert box
var alertBox = document.querySelector('.alert-danger');
alertBox.classList.add('d-none');

document.getElementById('filterForm').addEventListener('submit', function(event) {
    var category = document.getElementById('category');
    var alertText = alertBox.querySelector('strong');
    if (category.value === 'Select Category') {
        event.preventDefault();
        alertText.textContent = 'Please select a category before submitting the form';
        alertBox.classList.remove('d-none');
    } else {
        alertBox.classList.add('d-none');
        alertText.textContent = '';
    }
});

document.getElementById('durationForm').addEventListener('submit', function(event) {
    var duration = document.getElementById('duration');
    var alertText = alertBox.querySelector('strong');
    if (duration.value === 'Select Duration') {
        event.preventDefault();
        alertText.textContent = 'Please select a duration before submitting the form';
        alertBox.classList.remove('d-none');
    } else {
        alertBox.classList.add('d-none');
        alertText.textContent = '';
    }
});


document.getElementById('searchForm').addEventListener('submit', function(event) {
    var search = document.getElementById('searchTerm');
    var alertText = alertBox.querySelector('strong');
    if (search.value.trim() === '') {
        event.preventDefault();
        alertText.textContent = 'Please enter a search term before submitting the form';
        alertBox.classList.remove('d-none');
    } else {
        alertBox.classList.add('d-none');
        alertText.textContent = '';
    }
});
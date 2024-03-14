var alertBox = document.querySelector('.alert-danger');
alertBox.classList.add('d-none');

var closeButton = alertBox.querySelector('.btn-close');

closeButton.removeAttribute('data-bs-dismiss');

closeButton.addEventListener('click', function() {
    alertBox.classList.add('d-none');
});

function showAlert(formId, selectId, errorMessage) {
    document.getElementById(formId).addEventListener('submit', function(event) {
        var select = document.getElementById(selectId);
        var alertText = alertBox.querySelector('strong');
        alertText.innerHTML = '';
        if (select.value === 'Select Category' || select.value === 'Select Duration' || select.value.trim() === '') {
            event.preventDefault();
            var icon = document.createElement('i');
            icon.className = 'bi bi-exclamation-triangle-fill';
            alertText.appendChild(icon);
            alertText.appendChild(document.createTextNode(' ' + errorMessage));
            alertBox.classList.remove('d-none');
        } else {
            alertBox.classList.add('d-none');
        }
    });
}

showAlert('filterForm', 'category', 'Please select a category before submitting the form');
showAlert('durationForm', 'duration', 'Please select a duration before submitting the form');
showAlert('searchForm', 'searchTerm', 'Please enter a search term before submitting the form');
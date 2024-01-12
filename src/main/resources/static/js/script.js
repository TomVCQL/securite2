document.addEventListener('DOMContentLoaded', function () {
    // Récupérer l'élément input de type date par son ID
    var datePicker = document.getElementById('datePicker');

    // Définir la date maximale sur la date actuelle
    datePicker.max = new Date().toISOString().split('T')[0];
});

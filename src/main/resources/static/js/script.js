document.addEventListener('DOMContentLoaded', function () {
    // Récupérer l'élément input de type date par son ID
    var datePicker = document.getElementById('datePicker');

    // Définir la date maximale sur la date actuelle
    datePicker.max = new Date().toISOString().split('T')[0];

    var numberInput = document.getElementById('prixTransport');

    // Ajouter un écouteur d'événements pour intercepter les changements de valeur
    numberInput.addEventListener('input', function () {
        // Valider la valeur par rapport à la limite maximale (300)
        if (parseInt(numberInput.value) > 300) {
            // Si la valeur est supérieure à 300, ajustez-la à 300
            numberInput.value = 300;
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    /**********************************************************/
    if(datePicker = document.getElementById('datePicker'))
    {
        datePicker.max = new Date().toISOString().split('T')[0];
    }
    
    
/**************************************************************/
    if(numberInput = document.getElementById('prixTransport'))
    {
        numberInput.addEventListener('input', function () {
            if (parseInt(numberInput.value) > 300) {
                numberInput.value = 300;
            }
        });
    }

    if(iban = document.getElementById('ibanId'))
    {
        var monFormulaire = document.getElementById('monForm');

        monFormulaire.addEventListener('submit', function(event) {
            if (iban.value.length !== 27) {
                event.preventDefault();
                alert("L'IBAN doit être rempli");
            }
        });
    }
});

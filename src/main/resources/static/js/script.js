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
    
    var iban = document.getElementById('ibanId');
    var submitIban = document.getElementById('submitIban');

    submitIban.disabled = true;
        iban.addEventListener('input', function() {
            if(iban.value.length == 27) {
                submitIban.disabled = false;
            }
            else{
                submitIban.disabled = true;
            }
        });
});

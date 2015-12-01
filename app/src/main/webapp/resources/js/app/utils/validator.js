var Validator = function()  {
    return {
        FIELD_VALIDATE_PATTERN : new RegExp("^[A-Za-z0-9]+[A-Za-z0-9\\s]+[A-Za-z0-9]+$"),

        onblur: function(field, text) {
            if (!this.FIELD_VALIDATE_PATTERN.test(field.value)) {
                field.value = text;
                field.style.color = 'red';
            }
        },

        onfocus: function(field, text) {
            if (field.value == text) {
                field.value = '';
                field.style.color = 'black';
            }
        }
    }
}




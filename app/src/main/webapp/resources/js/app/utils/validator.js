var Validator = function()  {
    return {
        FIELD_VALIDATE_PATTERN : new RegExp("^[A-Z]([a-z0-9\\s]){3,64}$"),
        CATEGORY_VALIDATE_PATTERN : new RegExp("^([A-Za-z\\s])([a-z0-9\\s]){3,64}$"),
        USER_NAME_VALIDATE_PATTERN : new RegExp("^[A-Za-z0-9]+[A-Za-z0-9\\s]+[A-Za-z0-9]+$"),
        USER_LOGIN_VALIDATE_PATTERN : new RegExp("^[A-Za-z0-9_.-]+$"),
        USER_EMAIL_VALIDATE_PATTERN : new RegExp("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"),
        USER_PASSWORD_VALIDATE_PATTERN : new RegExp("^([A-Za-z\\s])([a-z0-9\\s]){3,64}$"),
        //([A-Za-z0-9\\s]){4,64}
        //^([A-Za-z\s])([a-z0-9\s]){3,64}$
        FILL_FORM_MESSAGE : "Please fill the form correctly!",
        ERROR_FILL_FORM_CSS :"{'color': 'red', 'textAlign': 'center', 'marginTop': '10px'}",
        ERROR_FILL_FIELD_CSS : "{'color', 'red'}",
        WRONG_NAME : 'Wrong name!',
        WRONG_CATEGORY : 'Wrong category!',
        WRONG_DESCRIPTION : 'Wrong description!',

        onblur: function(field, text) {
            if (!validator.FIELD_VALIDATE_PATTERN.test(field.value)) {
                field.value = text;
                field.style.color = 'red';
            }
        },

        onblurPattern: function(field, text, pattern) {
            if (!pattern.test(field.value)) {
                field.value = text;
                field.style.color = 'red';
            }
        },

        onCategoryBlur: function(field, text) {
            if (!validator.CATEGORY_VALIDATE_PATTERN.test(field.value)) {
                field.value = text;
                field.style.color = 'red';
            }
        },

        onfocus: function(field, text) {
            if (field.value == text) {
                field.value = '';
                field.style.color = 'black';
            }
        },

        testField : function(field, error) {
            if (!validator.FIELD_VALIDATE_PATTERN.test(field.val())) {
                field.val = '';
                error.html(validator.FILL_FORM_MESSAGE);
                return false;
            }
            return true;
        },

        testFieldPattern : function(field, error, pattern) {
            if (!pattern.test(field.val())) {
                field.val = '';
                error.html(validator.FILL_FORM_MESSAGE);
                return false;
            }
            return true;
        },

        testCategoryField : function(field, error) {
            if (!validator.CATEGORY_VALIDATE_PATTERN.test(field.val())) {
                field.val = '';
                error.html(validator.FILL_FORM_MESSAGE);
                return false;
            }
            return true;
        }
    }
}




 define([ 'underscore', 'backbone', 'model/UserModel','text!templates/UserRegistration.html', 'text!templates/NotificationTemplate.html' ],
		 function(_, Backbone, UserModel, RegistrationTemplate, NotificationTemplate) {

             var that = null;
             var WRONG_NAME = "Wrong name!";
             var WRONG_LOGIN = "Wrong login!";
             var WRONG_EMAIL = "Wrong email!";
             var WRONG_PASSWORD = "Wrong password!";

     var UserRegistrationView = Backbone.View.extend({

         template: _.template(RegistrationTemplate),
         model : null,
         notificationTemplate: _.template(NotificationTemplate),
                 
         initialize: function () {
             $('.signUp.modal').empty();
             $('.signUp.modal').append(this.template);

         },
         events: {
             'click #signUp' : 'showUserRegForm',
             'click .panel #reg_btn-close' : 'hideUserRegForm',
             'click #signUpBox #reg_submit': 'onSubmit'
         },

         onSubmit: function (e) {

             var that = this;
             e.preventDefault();
             if(validator.testFieldPattern( $('#reg_name'), $('#error-add-user'), validator.USER_NAME_VALIDATE_PATTERN) && validator.testFieldPattern($('#reg_login'), $('#error-add-user'), validator.USER_LOGIN_VALIDATE_PATTERN)
                 &&  validator.testFieldPattern($('#reg_email'), $('#error-add-user'), validator.USER_EMAIL_VALIDATE_PATTERN) &&  validator.testFieldPattern($('#reg_password'), $('#error-add-user'), validator.USER_PASSWORD_VALIDATE_PATTERN)) {

                 this.model = this.getModel();
                 this.model.save({}, {
                     success: function (model, response) {
                         if ($('#notificationModal')) {
                             $('#notificationModal').remove();
                         }
                         $(".signUp.modal").modal("hide");
                         that.$el.append(that.notificationTemplate({'data': response}));
                         $('#notificationModal').modal();
                     },
                     error: function () {
                         if ($('#notificationModal')) {
                             $('#notificationModal').remove();
                         }
                         that.$el.append(that.notificationTemplate({'data': {'message': 'Error!'}}));
                         $('#notificationModal').modal();
                     }
                 });
             }

         },

         getModel: function(){
             return new UserModel( $("#signUpForm").serializeJSON() );
         },

         showUserRegForm: function(){
             this.render();
             router.navigate("user-reg", {trigger: true});
         },  
         
          hideUserRegForm: function(){         
             $(".signUp.modal").modal('hide');
					
         },

         render: function () {
             $(".signUp.modal").modal("show");
             this.cleanFields();

             var userName = $('#reg_name');
             var userLogin = $('#reg_login');
             var userEmail = $('#reg_email');
             var userPassword = $('#reg_password');

             userName.on({
                 blur : function() {
                     validator.onblurPattern(this, WRONG_NAME, validator.USER_NAME_VALIDATE_PATTERN);
                 },
                 focus : function() {
                     validator.onfocus(this, WRONG_NAME);
                 }});

             userLogin.on({
                 blur: function() {
                     validator.onblurPattern(this, WRONG_LOGIN, validator.USER_LOGIN_VALIDATE_PATTERN);
                 },
                 focus: function() {
                     validator.onfocus(this, WRONG_LOGIN);
                 }});

             userEmail.on({
                 blur: function() {
                     validator.onblurPattern(this, WRONG_EMAIL, validator.USER_EMAIL_VALIDATE_PATTERN);
                 },
                 focus : function() {
                     validator.onfocus(this, WRONG_EMAIL);
                 }});

             userPassword.on({
                 blur: function() {
                     validator.onblurPattern(this, WRONG_PASSWORD, validator.USER_PASSWORD_VALIDATE_PATTERN);
                 },
                 focus : function() {
                     validator.onfocus(this, WRONG_PASSWORD);
                 }});
             return this;
         },

         cleanFields: function(){
             $('#reg_name').val("");
             $('#reg_login').val("");
             $('#reg_email').val("");
             $('#reg_password').val("");
         }

     });
        return UserRegistrationView ;
});  
            
 define([ 'underscore', 'backbone', 'model/UserModel','text!templates/UserRegistration.html', 'text!templates/NotificationTemplate.html' ],
		 function(_, Backbone, UserModel, RegistrationTemplate, NotificationTemplate) {

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
             'click #reg_btn-close' : 'hideUserRegForm',
             'click #reg_submit': 'onSubmit'
         },


         onSubmit: function (e) {

             var that = this;
             e.preventDefault();

             this.model = this.getModel();
             this.model.save( {}, {
                 success: function(model, response) {
                     if($('#notificationModal')) {
                         $('#notificationModal').remove();
                     }
                     $(".signUp.modal").modal("hide");
                     that.$el.append(that.notificationTemplate( { 'data': response } ));
                     $('#notificationModal').modal();
                 },
                 error: function() {
                     if($('#notificationModal')) {
                         $('#notificationModal').remove();
                     }
                     that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
                     $('#notificationModal').modal();
                 }
             } );



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
             return this;
         }




     });
        return UserRegistrationView ;
});  
            
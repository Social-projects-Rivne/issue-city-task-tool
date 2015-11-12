define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/ViewUserHistory.html'  ],
    function($, _, Backbone,  UserModel, ViewUserHistoryTemplate) {

        var ViewUserHistory = Backbone.View.extend({

            events: {
                'click #profile' : 'editProfile',
                'click #admin_log_out'	: 'logOut',
            },

            viewUserProfileTemplate: _.template(ViewUserHistoryTemplate),


            initialize: function() {

            },

            render: function(id) {
                this.model = loginView.currentUser;
                this.$el.html(this.viewUserHistoryTemplate(this.model.toJSON()));
                console.log(this.model);
            },

            editProfile: function() {
                router.navigate('#profile', {trigger: true});
            },
            logOut: function(){
                $.ajax('auth/logout');
                loginView.currentUser = null;
                router.navigate('', {trigger:true});
                if($('#notificationModal'))
                    $('#notificationModal').remove();
                this.$el.append(that.notificationTemplate( { 'data': { 'message': "You have been successfully logged out!" }} ));
                $('#notificationModal').modal();
                loginView.buttonsManage();
            }



        });

        return ViewUserProfile;
    })

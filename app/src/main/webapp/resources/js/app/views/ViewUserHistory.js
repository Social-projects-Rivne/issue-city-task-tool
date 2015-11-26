define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/ViewUserHistory.html'  ],
    function($, _, Backbone,  UserModel, ViewUserHistoryTemplate) {

        var ViewUserHistory = Backbone.View.extend({

            events: {
                'click #profile' : 'editProfile',
                'click #admin_log_out'	: 'AppController.logout'
            },

            viewUserProfileTemplate: _.template(ViewUserHistoryTemplate),


            initialize: function() {

            },

            render: function(id) {
                this.model = loginView.currentUser;
                this.$el.html(this.viewUserProfileTemplate(this.model.toJSON()));
                console.log(this.model);
            },

            editProfile: function() {
                router.navigate('#profile');
            }


        });

        return ViewUserProfile;
    })

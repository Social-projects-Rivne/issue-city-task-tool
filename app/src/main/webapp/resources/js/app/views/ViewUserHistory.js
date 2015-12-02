define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/ViewUserHistory.html'  ],
    function($, _, Backbone,  UserModel, ViewUserHistoryTemplate) {

        var ViewUserHistory = Backbone.View.extend({

            events: {
                'click #admin_log_out'	: 'AppController.logout',
            },

            viewUserViewTemplate: _.template(ViewUserHistoryTemplate),


            initialize: function() {

            },

            render: function() {
                1
            },



        });

        return ViewUserHistory;
    })

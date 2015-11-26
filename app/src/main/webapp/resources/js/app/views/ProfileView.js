define(['jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/UserProfile.html', 'text!templates/NotificationTemplate.html'],
    function ($, _, Backbone, UserModel, UserProfileTemplate, NotificationTemplate) {

        var that = null;
        return Backbone.View.extend({

            events: {},

            userProfileTemplate: _.template(UserProfileTemplate),

            initialize: function () {
                that = this;
            },

            render: function (id) {
                $.ajax({ contentType:'application/json',
                    url: 'users/current',
                    success: function(data){
                        that.model = new UserModel(data);
                        that.$el.html(that.userProfileTemplate(that.model.toJSON()));
                    }
                });
            }
        });

    })

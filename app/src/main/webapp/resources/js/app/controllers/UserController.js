var UserController = function(app) {
    return {

        viewprofile: function () {
            viewUserProfile.render();
        },

        userReg: function () {
            userRegView.render()
        },

        emailConfirm: function (link) {
            loginView.confirmEmail(link);
        },

        subConfirm: function (link) {
            loginView.confirmSubscription(link);
        },
        subDelete: function (link) {
            loginView.deleteSubscription(link);
        },

    }
}
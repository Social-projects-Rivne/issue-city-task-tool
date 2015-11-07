var UserController = function(app) {
    return {
        profile: function () {
            profileView.render();
        },

        viewprofile: function () {
            viewUserProfile.render();
        },


        userReg: function () {
            userRegView.render();
        },

        emailConfirm: function (link) {
            loginView.confirmEmail(link);
        }
    }
}
var AppController = function(app) {
    return {
        home: function () {
            mapView.render();
        },

        login: function () {
            loginView.render();
        },

        logout: function () {
            loginView.currentUser = null;
            $.ajax({
                url: 'auth/logout',
                type: 'POST',
                success: function () {
                    loginView.currentUser = null;
                    $('.navbar  #admin').hide();
                    $('.navbar  #manager').hide();
                    $('.navbar  #cry-out').hide();
                    $('.navbar  #filter').hide();
                    $('.navbar  #stat').hide();
                    $('.navbar  #signUp').show();
                    $('.navbar  #login').show();
                    $('.navbar  #logout').hide();
                    $('.navbar  #profile').hide();
                }
            });
            mapView.render();
        },

    }
}
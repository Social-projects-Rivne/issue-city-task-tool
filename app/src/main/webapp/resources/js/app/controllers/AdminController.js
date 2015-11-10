var AdminController = function(AdminView, ManagerView) {
    return {
        admin: function () {
            if (!_.isNull(loginView.currentUser) && _.isNull(adminView)) {
                adminView.render();
            } else {
                if (_.isNull(adminView)) {
                    adminView = new AdminView({el: "#container"});
                    managerView = new ManagerView({el: "#container"})
                    adminView.render();
                }
            }
        },

        manager: function () {
            if (!_.isNull(loginView.currentUser) && !_.isNull(managerView)) {
                managerView.render();
            } else {
                if (_.isNull(managerView)) {
                    managerView = new ManagerView({el: "#container"});
                    managerView.render();
                }
            }
        },

        search: function (name) {
            //alert('you serch ' + name);
            //adminView.search(name);
        }

    }
}
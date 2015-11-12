var AdminController = function(AdminView, ManagerView, CategoryManageView) {
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

        categories: function(){
            categoryManageView = new CategoryManageView();
            categoryManageView.render();
        },

        search: function (name) {
            //alert('you serch ' + name);
            //adminView.search(name);
        }

    }
}
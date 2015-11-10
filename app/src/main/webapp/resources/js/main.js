require(['jquery', 'underscore', 'backbone', 'router', 'backbone_route_control',
        'view/MapView', 'view/AddIssueView', 'view/ManagerView', 'view/AdminView',
        'view/LoginView', 'view/IssueFilterView', 'view/StatisticView', 'view/ProfileView',
        'view/ViewUserProfile', 'view/ImageEditorView', 'view/UserRegistrationView', 'view/HistoryView'],
function ($, _, Backbone, Router, BackboneRouteControl,
                MapView, AddIssueView, ManagerView, AdminView,
                LoginView, IssueFilterView, StatisticView, ProfileView,
                ViewUserProfile, ImageEditorView, UserRegistrationView, HistoryView) {

        router = null;
        adminView = null;
        managerView = null;
        userRegView = null;
        historyView = null;
        USER_NOT_CONFIRMED = 0;
        USER = 1;
        MANAGER = 2;
        ADMIN = 3;


        jQuery(document).ready(function ($) {
            $.ajaxSetup({
                statusCode: {
                    401: function () {
                        router.navigate('login', {trigger: true});
                    }
                }
            });

            mapView = new MapView({el: "body"});
            issueFilterView = new IssueFilterView({el: "#container"});
            loginView = new LoginView({el: "body"});
            userRegView = new UserRegistrationView({el: "body"});
            issueFilterView = new IssueFilterView({el: "#container"});
            statisticView = new StatisticView({el: 'body'});
            profileView = new ProfileView({el: "#container"});
            viewUserProfile = new ViewUserProfile({el: "#container"});
            imageEditorView = new ImageEditorView({el: 'body'});
            historyView = new HistoryView();

            router = new Router({
                controllers: {
                    login: new AppController(mapView),
                    issues: new IssueController(),
                    users: new UserController(),
                    admins: new AdminController(AdminView, ManagerView)
                }
            });

        });


    });

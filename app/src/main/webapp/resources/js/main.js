require(['jquery', 'underscore', 'backbone', 'router', 'backbone_route_control',
      'view/MapView', 'view/AddIssueView', 'view/ManagerView', 'view/AdminView', 'view/CategoryManageView',
      'view/LoginView', 'view/IssueFilterView', 'view/StatisticView', 'view/ProfileView',
      'view/UserProfileView', 'view/ImageEditorView', 'view/UserRegistrationView', 'view/HistoryView'],
    function ($, _, Backbone, Router, BackboneRouteControl,
              MapView, AddIssueView, ManagerView, AdminView, CategoryManageView,
              LoginView, IssueFilterView, StatisticView, ProfileView,
              UserProfileView, ImageEditorView, UserRegistrationView, HistoryView) {

      router = null;
      adminView = null;
      managerView = null;
      categoryManageView = null;
      userRegView = null;
      historyView = null;

      USER_NOT_CONFIRMED = 0;
      USER = 1;
      MANAGER = 2;
      ADMIN = 3;
      SUBSCRIBER = 4;
      CATEGORY_NEW = "New";
      MAX_IMG_SIZE = 5000000;
      CATEGORY_DELETED = "Deleted"


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
        viewUserProfile = new UserProfileView({el: "#container"});
        imageEditorView = new ImageEditorView({el: 'body'});
        historyView = new HistoryView();

        router = new Router({
          controllers: {
            login: new AppController(mapView),
            issues: new IssueController(),
            users: new UserController(),
            admins: new AdminController(AdminView, ManagerView, CategoryManageView)
          }
        });

      });
    });

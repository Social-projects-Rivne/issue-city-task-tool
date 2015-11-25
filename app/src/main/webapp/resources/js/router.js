define(['underscore', 'backbone', 'backbone_route_control',
        'controllers/AppController', 'controllers/IssueController',
        'controllers/UserController','controllers/AdminController'],
function (_, Backbone, BackboneRouteControl,
          AppController, IssueController, UserController, AdminController) {

    return BackboneRouteControl.extend({

            routes: {
                "":         "login#home",
                "login":    "login#login",
                "logout":   "login#logout",

                "admin":    "admins#admin",
                "admin/search/:name": "admins#search",
                "manager": "admins#manager",
                "categories": "admins#categories",

                "cry-out": "issues#cryOut",
                "issue/:id": "issues#issue",
                "filter": "issues#filter",

                "user-reg": "users#userReg",
                "email-confirm/*link": "users#emailConfirm",
                "profile": "users#profile",
                "viewprofile": "users#viewprofile"
            },

            initialize: function() {
                Backbone.history.start();
            }

        });

    });

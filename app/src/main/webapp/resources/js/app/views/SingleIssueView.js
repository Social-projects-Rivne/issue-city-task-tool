define(['jquery', 'underscore', 'backbone'], function ($, _, Backbone) {

      return Backbone.View.extend({

        tagName: 'li',
        className: 'class_history',
        id: 'tab2',

        template: _.template('<strong><%= date %>: </strong> <%= userTitle%> <span><%= username%> </span><%= message%>'),

        initialize: function () {},

        render: function () {

          var userTitle = this.model.get("roleName");
          var status = this.model.get("status");
          const elem = "message";

          if (!_.isEmpty(userTitle)) {
            this.model.set("userTitle", userTitle);
          }

          switch (status) {
            case "NEW" : this.model.set(elem, " created this issue"); break;
            case "APPROVED" : this.model.set(elem, " approved this issue"); break;
            case "RESOLVED" : this.model.set(elem, " resolved this issue"); break;
            case "DELETED" : this.model.set(elem, " deleted this issue"); break;
            case "TO_RESOLVE" : this.model.set(elem, " decided to resolve this issue"); break;
            default : this.model.set(elem, " user created this issue");
          }

          this.$el.html(this.template(this.model.toJSON()));
          console.log(this.$el);
          return this;

        }
      });
    });

		
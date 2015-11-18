define([ 'jquery', 'underscore', 'backbone'],
		function($, _, Backbone) {
						
			
	var SingleIssueView = Backbone.View.extend({
		tagName: 'li',
 		className: 'class_history',
		id: 'tab2',
		
		template: _.template('<strong><%= date %>: </strong> <%= userTitle%> <span><%= username%> </span><%= message%>'),

		initialize: function() {
		},
 
		render: function() {
			var userTitle = this.model.get("roleName");
			if (userTitle != null) {
				this.model.set("userTitle",userTitle);
			}
			if (this.model.get("status") == "NEW"){
				this.model.set("message", " created this issue");
			}
			else if (this.model.get("status") == "APPROVED"){
				this.model.set("message", " approved this issue");
			}
			else if (this.model.get("status") == "RESOLVED"){
				this.model.set("message", " resolved this issue");
			}
			else if (this.model.get("status") == "DELETED"){
				this.model.set("message", " deleted this issue");
			}
			else if (this.model.get("status") == "TO_RESOLVE"){
				this.model.set("message", " decided to resolve this issue");
			}
			else {
				this.model.set("message", " user created this issue");
			}
            this.$el.html(this.template(this.model.toJSON()));
			console.log(this.$el);
			return this; 
		}
	});
return SingleIssueView;
});

		
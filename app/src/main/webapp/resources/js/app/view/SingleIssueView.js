define([ 'jquery', 'underscore', 'backbone'],
		function($, _, Backbone) {
						
			
	var SingleIssueView = Backbone.View.extend({
		tagName: 'li',
 		className: 'class_history',
		id: 'tab2',
		
		template: _.template('<strong><%= date %>:</strong> <%= userTitle%><span><%= username%></span><%= message%>'),

		initialize: function() {
		},
 
		render: function() {
			if (this.model.get("roleName") == "User"){
				this.model.set("userTitle", "User");
				this.model.set("message", " send notification about resolving this issue");
			}  
			else if(this.model.get("roleName") == "Manager"){
					this.model.set("userTitle", "Manager");
				if (this.model.get("statusId") == 1){
					this.model.set("message", " created this issue");
				}
				else if (this.model.get("statusId") == 2){
					this.model.set("message", " approved this issue");
				}
				else if (this.model.get("statusId") == 3){
					this.model.set("message", " resolved this issue");
				}
				else if (this.model.get("statusId") == 4){
					this.model.set("message", " deleted this issue");
				}
				else if (this.model.get("statusId") == 5){
					this.model.set("message", " decided to resolve this issue");
				}
			}
			else {
				this.model.set("userTitle", "Unknown ");
				this.model.set("message", " user created this issue");
			}
			
            this.$el.html(this.template(this.model.toJSON()));
			console.log(this.$el);

			return this; 
		}
	});
return SingleIssueView;
});

		
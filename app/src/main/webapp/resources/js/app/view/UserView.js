define([ 'jquery', 'underscore', 'backbone', 'model/UserModel' ],
		function($, _, Backbone, UserModel) {
			var UserView = Backbone.View.extend({
				tagName : "tr",
				
				render : function() {
					this.$el.html("<td>" + this.model.get("login") + "</td>" +
							"<td>" + this.model.get("email") + "</td>" +
							"<td>" + this.model.get("name") + "</td>" +
							"<td>" +
								"<div class='btn-toolbar'>" +
                    				"<div class='btn-group'>" +
                    					"<button class='btn glyphicon-pencil' id='"+ this.model.get("id") +"'><span class='glyphicon glyphicon-pencil'></span></button>" +
                    					"<button class='btn glyphicon-pencil' id='"+ this.model.get("id") +"'><span class='glyphicon glyphicon-remove'></span></button>" +
                    				"</div>" +
                    			"</div>" +
                    		"</td>");
					
					return this;
				}
			});	
			
			return UserView;
		})

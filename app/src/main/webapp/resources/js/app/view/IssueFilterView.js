define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'collection/CategoryCollection',  'text!templates/issue_filter.html', ],
		function($, _, Backbone, IssueModel, CategoryCollection, IssueFilterTemplate) {
			var IssueFilterView = Backbone.View.extend({
				
				categoryCollection: new CategoryCollection(),
				template: _.template(IssueFilterTemplate),
				
				events: {
					'click #filter': 'render',
					'click #set-issue-filter': 'setFilter'
				},			
					

				initialize: function() {

				},
				
				render: function(id) {
					that = this;
					this.categoryCollection.fetch({success: function(){

							$('#form-container').html(that.template({"categories":that.categoryCollection.toJSON()}));
							console.log(that.categoryCollection.toJSON());
						},
						error: function(){
							console.log("smth wrong");
						}
					});

				},
				
				renderMarkers: function(){
					//mapViev.model = 
				},

				setFilter: function(){

				}

			});
			return IssueFilterView;
		})

define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'model/CategoryModel', 'collection/CategoryCollection',  'text!templates/issue_filter.html', ],
		function($, _, Backbone, IssueModel, CategoryModel, CategoryCollection, IssueFilterTemplate) {
			var IssueFilterView = Backbone.View.extend({
				
				categoryCollection: new CategoryCollection(),
				template: _.template(IssueFilterTemplate),
				issueColection: null,
				events: {
					'click #filter': 'render',
					'click #set-issue-filter': 'setFilter'
				},			
					

				initialize: function() {

				},
				
				render: function() {
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
					radioCategory = $("#issue-filter #category");

					if(radioCategory.prop("checked")){
						var categoryName = $("#issue-filter #categories").val();
						var id = new CategoryModel(this.categoryCollection.findWhere({name: categoryName}));
						console.log(id.get('id'));
					

					}
				}

			});
			return IssueFilterView;
		})

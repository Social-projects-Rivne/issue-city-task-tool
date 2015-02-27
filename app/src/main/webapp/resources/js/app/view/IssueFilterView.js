define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'model/CategoryModel', 'collection/CategoryCollection', 'collection/IssueCollection',  'text!templates/issue_filter.html', ],
		function($, _, Backbone, IssueModel, CategoryModel, CategoryCollection, IssueCollection, IssueFilterTemplate ) {
			var IssueFilterView = Backbone.View.extend({
				
				template: _.template(IssueFilterTemplate),
				categoryCollection: new CategoryCollection(),
				issueColection: null,
				issueFiltredColection: null,

				events: {
					'click #filter': 'render',
					'click #set-issue-filter': 'setFilter'
				},			
					

				initialize: function() {
					this.issueColection = mapView.model;
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
					mapView.model = this.issueFiltredColection;
					mapView.render();
					console.log("issueFiltredColection" + this.issueFiltredColection.toJSON());
				},

				setFilter: function(){
					radioCategory = $("#issue-filter #category");

					if(radioCategory.prop("checked")){
						var categoryName = $("#issue-filter #categories").val();
						var category = new CategoryModel(this.categoryCollection.findWhere({name: categoryName}));
						console.log(category.get('id'));
						this.issueFiltredColection = new IssueCollection(this.issueColection.where({categoryId : category.get('id')}));
					}

					this.renderMarkers();
				}

			});
			return IssueFilterView;
		})

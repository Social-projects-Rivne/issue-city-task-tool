define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'model/CategoryModel', 'collection/CategoryCollection', 'collection/IssueCollection',  'text!templates/issue_filter.html', ],
		function($, _, Backbone, IssueModel, CategoryModel, CategoryCollection, IssueCollection, IssueFilterTemplate ) {
			var IssueFilterView = Backbone.View.extend({
				
				template: _.template(IssueFilterTemplate),
				categoryCollection: new CategoryCollection(),
				issueColection: null,
				issueFiltredColection: null,

				events: {
					'click #filter': 'render',
					'click #set-issue-filter': 'setFilter',
					'click #reset-issue-filter': 'resetFilter'
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
					mapView.cleanMap();
					mapView.model = this.issueFiltredColection;
					mapView.model.each(function(issue) {
						L.marker(issue.get("mapPointer").substr(7, issue.get("mapPointer").length - 1)
								.split(', '), { icon: mapView.markers[Math.floor(Math.random() * 5)] }).addTo(map).on('click', onMarkerClick).title = issue.get("id"); // delete from this line on('click', onMareker...). 
					});
					//mapView.render();
					//console.log("issueFiltredColection" + this.issueFiltredColection.toJSON());
					
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
				},

				resetFilter: function(){
					this.issueFiltredColection = this.issueColection;
					this.renderMarkers();
				}

			});
			function onMarkerClick(e) {
				//issueDetailsView.render(this.title);
				//TODO:replace comment list in issue details view
				Backbone.history.navigate("issue/"+this.title,true)
			}
			return IssueFilterView;
		})

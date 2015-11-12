define([ 'jquery', 'underscore', 'backbone', 'text!templates/CategoryManageTemplate.html', 'collection/CategoryCollection',
		'view/SingleCategoryView', 'model/CategoryModel',  'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, CategoryManageTemplate, CategoryCollection, SingleCategoryView,
				 CategoryModel, NotificationTemplate) {
			
			var that = null;
	
			var CategoryManageView = Backbone.View.extend({

				el: '.right_admin_panel',

				events: {
					'click #addCategory' : 'addCategory',
					'click .editCategory': 'editCategory'
				},
				
				categoryManageTemplate: _.template(CategoryManageTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				categories: null,

				initialize: function() {
					this.categories = new CategoryCollection();
					that = this;
				},
				
				render: function() {
					that.$el.html(that.categoryManageTemplate());
					this.categories.fetch({
						success: function () {
							that.categories.each(function (category) {
								var categoryView = new SingleCategoryView({model: category});
								$("#category-table-body").append(categoryView.render().el);
							}, this);
						}
					});
				},

				addCategory: function() {
					var newCategory = new CategoryModel( { 'name': $('').val() } );
					newCategory.save( {}, {
						success: function(model, response) {
							$('#add-category-link').popover('hide');
							that.$el.append(that.notificationTemplate({'data': response}));
							that.$el.append(that.notificationTemplate({'data':{ 'message': 'Category succsesfully added!'}}));
							$('#notificationModal').modal();
						}
					} );
				},

				editCategory: function(e){
					var category = this.categories.get(e.currentTarget.id);
					category.set({
						name: "new name"// $("")
					}).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							that.$el.append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
				}


			});
			
			return CategoryManageView;
		})

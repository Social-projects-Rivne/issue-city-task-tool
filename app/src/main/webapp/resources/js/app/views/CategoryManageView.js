define([ 'jquery', 'underscore', 'backbone', 'text!templates/CategoryManageTemplate.html', 'collection/CategoryCollection',
		'view/SingleCategoryView', 'model/CategoryModel', 'text!templates/CategoryEditTemplate.html',  'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, CategoryManageTemplate, CategoryCollection, SingleCategoryView,
				 CategoryModel, CategoryEditTemplate, NotificationTemplate) {
			
			var that = null;
	
			var CategoryManageView = Backbone.View.extend({

				el: '.right_admin_panel',

				events: {
					'click #addCategory' : 'addCategory',
					'click .deleteCategory': 'deleteCategory',
					'click .editCategory': 'showEditCategoryTemplate',
					'click .editCategoryConfirm' : 'editCategoryConfirm'
				},
				
				categoryManageTemplate: _.template(CategoryManageTemplate),
				categoryEditTemplate: _.template(CategoryEditTemplate),
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

				editCategoryConfirm: function(e) {
					if($('#confirmationModal')) $('#confirmationModal').remove();
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this category?' }, { 'id': e.currentTarget.id }, { 'action': 'edit category' } ] } ));
					$('#confirmationModal').modal();
				},

				showEditCategoryTemplate: function(e){
					if($('#editCategoryModal')) $('#editCategoryModal').remove();
					var category = this.categories.get(e.currentTarget.id);
					$("#container").append(this.categoryEditTemplate(category.toJSON()));
					$("#editCategoryModal").modal();
				},

				deleteCategory: function(e){
					var category = this.categories.get(e.currentTarget.id);
					category.set({
						state: CATEGORY_DELETED
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

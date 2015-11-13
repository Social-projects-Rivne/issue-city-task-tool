define([ 'jquery', 'underscore', 'backbone', 'text!templates/CategoryManageTemplate.html', 'collection/CategoryCollection',
		'view/SingleCategoryView', 'model/CategoryModel', 'text!templates/CategoryEditTemplate.html', 'text!templates/ConfirmationTemplate.html',  'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, CategoryManageTemplate, CategoryCollection, SingleCategoryView,
				 CategoryModel, CategoryEditTemplate, ConfirmationTemplate, NotificationTemplate) {
			
			var that = null;
	
			var CategoryManageView = Backbone.View.extend({

				el: '.right_admin_panel',

				events: {
					'click #addCategory' : 'addCategory',
					'click .category-table .editCategory': 'showEditCategoryTemplate',
					'click .editCategoryConfirm' : 'editCategoryConfirm',
					'click .category-table .deleteCategory' : 'showRemoveCategoryConfirmation',
					'click .confirm': 'confirmAction'
				},
				
				categoryManageTemplate: _.template(CategoryManageTemplate),
				categoryEditTemplate: _.template(CategoryEditTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),

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

				//TODO Why this function didn't call when clicked on button ok?
				editCategoryConfirm: function(e) {
					if($('#confirmationModal')) $('#confirmationModal').remove();
					$("#container").append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this category?' }, { 'id': e.currentTarget.id }, { 'action': 'edit category' } ] } ));
					$('#confirmationModal').modal();
				},

				showEditCategoryTemplate: function(e){
					if($('#editCategoryModal')) $('#editCategoryModal').remove();
					var category = this.categories.get(e.currentTarget.id);
					$("#container").append(this.categoryEditTemplate(category.toJSON()));
					$("#editCategoryModal").modal();
				},

				showRemoveCategoryConfirmation: function(e){
					if($('#confirmationModal')) $('#confirmationModal').remove();
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this category?' }, { 'id': e.currentTarget.id }, { 'action': 'delete category' } ] } ));
					$('#confirmationModal').modal();
				},

				confirmAction: function(e){
					$('#confirmationModal').modal('hide');
					$('#editModal').modal('hide');
					if (e.currentTarget.name == 'edit category') {
						this.editCategory(e);
					}

					if (e.currentTarget.name == 'delete category') {
						this.deleteCategory(e);
					}
				},

				editCategory: function(e){
					var category = this.categories.get(e.currentTarget.id);
					category.set({
						name: $('#edit-category-name').val()
					}).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
							that.render();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );

				},

				deleteCategory: function(e){
					var category = this.categories.get(e.currentTarget.id);
					category.set({
						state: CATEGORY_DELETED
					}).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
							that.render();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
				}

			});
			
			return CategoryManageView;
		})

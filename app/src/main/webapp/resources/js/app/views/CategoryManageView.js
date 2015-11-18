define([ 'jquery', 'underscore', 'backbone', 'text!templates/CategoryManageTemplate.html', 'collection/CategoryCollection', 'text!templates/CategoryAddTemplate.html',
		'view/SingleCategoryView', 'model/CategoryModel', 'text!templates/CategoryEditTemplate.html', 'text!templates/ConfirmationTemplate.html',  'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, CategoryManageTemplate, CategoryCollection, CategoryAddTemplate, SingleCategoryView,
				 CategoryModel, CategoryEditTemplate, ConfirmationTemplate, NotificationTemplate) {
			
			var that = null;
	
			return Backbone.View.extend({
				el: '.right_admin_panel',
				events: {
					'click .category-table .editCategory': 'showEditCategoryTemplate',
					'click .editCategoryConfirm' : 'showEditCategoryConfirmation',
					'click .category-table .deleteCategory' : 'showRemoveCategoryConfirmation',
					//'click .confirm': 'confirmAction',
					'click #addCategory': 'showAddCategoryTemplate',
					'click .addCategoryConfirm' : 'addNewCategory'
				},
				
				categoryManageTemplate: _.template(CategoryManageTemplate),
				categoryEditTemplate: _.template(CategoryEditTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				categoryAddTemplate: _.template(CategoryAddTemplate),
				categories: null,

				initialize: function() {
					this.categories = new CategoryCollection();
					that = this;
				},
				
				render: function() {
					that.$el.html(that.categoryManageTemplate());
					this.categories.fetch({
						success: function () {
							$("#category-table-body").empty();
							that.categories.each(function (category) {
								var categoryView = new SingleCategoryView({model: category});
								$("#category-table-body").append(categoryView.render().el);
							}, this);
						}
					});
					return this;
				},

				showEditCategoryTemplate: function(e){
					if($('#editCategoryModal')) $('#editCategoryModal').remove();
					var category = this.categories.get(e.currentTarget.id);
					$("#container").append(this.categoryEditTemplate(category.toJSON()));
					$("#editCategoryModal").modal();
					$('.editCategoryConfirm').click(this.showEditCategoryConfirmation);
				},

				showEditCategoryConfirmation: function(e) {
					$("#container").append(that.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this category?' }, { 'id': e.currentTarget.id }, { 'action': 'edit category' } ] } ));
					$('#confirmationModal').modal();
					return e;
				},

				showRemoveCategoryConfirmation: function(e){
					if($('#confirmationModal')) $('#confirmationModal').remove();
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this category?' }, { 'id': e.currentTarget.id }, { 'action': 'delete category' } ] } ));
					$('#confirmationModal').modal();
					return e;
				},

				showAddCategoryTemplate: function(e){
					if($('#addCategoryModal')) $('#addCategoryModal').remove();
					$("#container").append(this.categoryAddTemplate());
					$("#addCategoryModal").modal();
					$('.addCategoryConfirm').click(this.showAddCategoryConfirmation);
				},

				showAddCategoryConfirmation: function(e){
					if($('#confirmationModal')) $('#confirmationModal').remove();
					$("#container").append(that.confirmationTemplate( { 'data': [ { 'message': 'Do you really want add new category?' }, { 'id': e.currentTarget.id }, { 'action': 'add category' } ] } ));
					$('#confirmationModal').modal();
					return e;
				}

			});

		})

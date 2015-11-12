define([ 'jquery', 'underscore', 'backbone', 'text!templates/SingleCategoryTemplate.html'],
		function($, _, Backbone, SingleCategoryTemplate) {
						
			
	var SingleIssueView = Backbone.View.extend({
		tagName: 'tr',
		
		template: _.template(SingleCategoryTemplate),
		model: null,

		initialize: function() {
		},
 
		render: function() {
            this.$el.html(this.template(this.model.toJSON()));
			return this; 
		}
	});
return SingleIssueView;
});

		
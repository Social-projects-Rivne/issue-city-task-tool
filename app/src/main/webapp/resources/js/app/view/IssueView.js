define([ 'underscore', 'backbone', 'text!templates/IssueDetails.html'], function(_,
		Backbone, IssueDetailsTemplate) {

		var IssueView = Backbone.View.extend({		
			
			//model : IssueModel,
			tagName : 'div',
			className : 'grid',
			template : _.template(IssueDetailsTemplate),
		
			events : {
				'click #cry-out' : 'addIssueForm',
			},


			intialize : function(issueId) {

			},

			addIssueForm : function(){
				console.log('cry-out button event');
				this.render();
			},
		
		//TODO: render from collection is incorrect! Remove it later
		render : function() {
				console.log('Render function working');
				this.$el.html(this.template); // add (this.model.toJSON()) in template when model will be done
				console.log('el changed');
				$('#add-issue').append(this.el);
				console.log('apended');
			}
		});

	return IssueView;

});

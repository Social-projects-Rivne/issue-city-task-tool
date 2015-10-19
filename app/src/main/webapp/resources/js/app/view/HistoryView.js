define([ 'jquery', 'underscore', 'backbone', 'model/HistoryIssueModel', 'collection/HistoryCollection',
		'text!templates/ViewIssueHistory.html', 'view/SingleIssueView', 'text!templates/IssueDetails.html' ],
		function($, _, Backbone, HistoryIssueModel, HistoryCollection, ViewIssueHistory, SingleIssueView, IssueDetails) {

	var that = null;

	var HistoryView = Backbone.View.extend({
			tagName: 'ul',
            template: _.template(IssueDetails),
			initialize: function(){
				this.issueHistoryCollection = new HistoryCollection();

				},
			render: function(issueId){

                var that = this;

				this.issueHistoryCollection.url = "issue/" + issueId + "/history";

                this.issueHistoryCollection.fetch({success: function(){
                    that.issueHistoryCollection.each(function(issueHistory){
                        var issueView = new SingleIssueView({model: issueHistory});
                        //2.Вставити в головний тег ul (this.$el)
                        that.$el.append(issueView.render().el);
                    }, this);
                    $("#comments").append(that.$el);
                }
                });
				//this.issueHistoryCollection.fetch();
				//1.Пройти по всьому списку (по кожному екземпляру) і зрендерить кожен HistoryIssueView

                return this;
			}
	});

return HistoryView;
});

		
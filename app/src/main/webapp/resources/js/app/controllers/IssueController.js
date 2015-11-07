var IssueController = function(app) {
    return {
        issue: function (id) {
            $('.col-1-3').hide();
            issueDetailsView.render(id);
            historyView.render(id);
            // comments must rendering from issue details view
        },

        cryOut: function () {
            mapView.render();
            addIssueView.render();
        },


        filter: function () {
            issueFilterView.render();
        },

    }
}
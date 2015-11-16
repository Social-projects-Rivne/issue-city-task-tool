define(['jquery', 'underscore', 'backbone', 'text!templates/SingleCategoryTemplate.html'],
    function ($, _, Backbone, SingleCategoryTemplate) {


        return Backbone.View.extend({

            tagName: 'tr',
            model: null,
            template: _.template(SingleCategoryTemplate),

            render: function () {
                this.$el.html(this.template(this.model.toJSON()));
                return this;
            }
        });
    });

		
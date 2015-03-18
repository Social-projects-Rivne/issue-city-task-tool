define([ 'underscore', 'backbone', 'text!templates/ImageEditorTemplate.html' ],
		function(_, Backbone, ImageEditorTemplate) {
			var ImageEditorView = Backbone.View.extend({
				template: _.template(ImageEditorTemplate),
				
				initialize: function() {
					var that = this;
				},
				
				events: {
					'click #crop-button': 'cropImage',
				},
				
				render: function(src) {
					if($("#imageEditorModal")) $("#imageEditorModal").remove();
					this.$el.append(this.template( { data: { srcImg: src} } ));
					$("#imageEditorModal").modal();
					$("#crop-frame").resizable();
				},
				
				cropImage: function() {
					$.ajax({
						type: 'POST',
						url: 'crop-image',
						contentType: 'application/json',
						mimeType: 'application/json',
						data: JSON.stringify(),
						success: function(data) {
							alert('Success!');
						},
						error: function(data) {
							alert('Error!');
						}
					});
				}
				
			});	
			
			return ImageEditorView;
		})


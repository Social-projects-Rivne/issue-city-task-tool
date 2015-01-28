requirejs.config({
	baseUrl:"${pageContext.request.contextPath}",
	paths:{
		jquery: '${pageContext.request.contextPath}/resources/lib/jquery',
		underscore: 'lib/underscore',
		backbone: 'lib/backbone',
			testJS:'testJS'
	}
});
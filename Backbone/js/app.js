(function(){
	var Model = Backbone.Model.extend({
		initialize: function() {
			console.log("initialized");
		}
	});
	var model = new Model();
})();
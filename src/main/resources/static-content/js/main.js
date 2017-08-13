function app() {
	map.initMap();

	var formApp = new Vue({
		el : '#form',
		
		// data
		data : {
			points : [],			
			routeItems : [],			
			selectedPoints: []
		},
		
		// init function
		mounted: function () {
			this.loadPoints();
	    },
		
	    // operations
		methods : {
			loadPoints : function(){
		        var self = this;
				axios.get('/api/points')
				  .then(function (response) {
					  self.points = response.data;
					  map.addMarkers(response.data);
				  })
				  .catch(function (error) {
				    alert(error);
				  });
			},
			
			makeRoute : function() {
				this.routeItems = [];
				var self = this;
				
				if(this.selectedPoints.length==0) {
					alert("Выберите несколько точек для построения маршрута удерживая клавишу Ctrl.");
					return;
				}
				
				axios.get('/api/route/'+this.selectedPoints)
				  .then(function (response) {
					  
					  // convert meters to kilometers
					  response.data.forEach(function(item){
						  item.distance = (item.distance / 1000).toFixed(2);
					  });
					  
					  self.routeItems = response.data;
					  map.addPath(response.data);
				  })
				  .catch(function (error) {
					    alert(error);
				  });
			}
		}
	});
}
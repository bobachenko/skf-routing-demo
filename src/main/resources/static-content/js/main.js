
var MAX_POINTS = 8;

function app() {
	map.initMap();

	var formApp = new Vue({
		el : '#form',
		
		// data
		data : {
			points : [],			
			routeItems : [],			
			selectedPoints: [],
			total: 0,
			useFull: false
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
				
				if(this.useFull && this.selectedPoints.length > MAX_POINTS) {
					alert("Может быть выбрано не более "+MAX_POINTS+" пунктов.");
					return;
				}
				
				axios.get('/api/route/'+this.selectedPoints+'/'+this.useFull)
				  .then(function (response) {
					  
					  // convert meters to kilometers
					  response.data.paths.forEach(function(item){
						  item.distance = (item.distance / 1000).toFixed(2);
					  });
					  
					  self.routeItems = response.data.paths;
					  self.total = (response.data.distance / 1000).toFixed(2);
					  map.addPath(response.data.paths);
				  })
				  .catch(function (error) {
					    alert(error);
				  });
			}
		}
	});
}
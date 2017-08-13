var map = {

	map : null,
	osmLayer : null,

	initMap : function() {
		this.map = L.map('map').setView([ 51.166667, 71.433333 ], 12);

		this.osmLayer = L
				.tileLayer(
						'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
						{
							attribution : '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
							mapZoom : 18
						}).addTo(this.map);
	},

	addMarkers : function(data) {
		var self = this;
		var first = true;
		data.forEach(function(item) {
			var marker = L.marker([ item.lat, item.lon ]).addTo(self.map);
			marker.bindPopup(item.name);
			
			// show store popup
			if(first) {
				marker.openPopup();
				first = false;
			}
		});
	},
	
	clearPath : function(){
		for (i in this.map._layers) {
			if(this.map._layers[i]._latlngs)
				this.map.removeLayer(this.map._layers[i]);
	    }
	},
	
	addPath : function(data) {
		this.clearPath();
		var self = this;
		data.forEach(function(item){			
			var latlngs = eval("(" + item.path + ')');	
			
			// change lon and lat order
			latlngs.forEach(function(coord){
				var tmp = coord[0];
				coord[0] = coord[1]
				coord[1] = tmp;
			});
			
			L.polyline(latlngs, {color: 'red'}).addTo(self.map);
		});		
	}
}

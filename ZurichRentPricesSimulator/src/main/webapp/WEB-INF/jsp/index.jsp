<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
  <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
   integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
   crossorigin=""></script>
  <title>Nomoko</title>
</head>
<body>
<div class="jumbotron">
  <h1>Zurich rental prices / m2</h1>
</div>
<div class="container">
  <div id="mapid" style="height:600px"></div>
</div>
<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

var mymap = L.map('mapid').setView([47.374456, 8.536671], 12);

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 18,
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
		'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
		'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox/streets-v11',
	tileSize: 512,
	zoomOffset: -1
}).addTo(mymap);


var theMarker = {};

mymap.on('click',function(e){
	  lat = e.latlng.lat;
	  lon = e.latlng.lng;
	  //Clear existing marker, 
	  if (theMarker != undefined) {
		  mymap.removeLayer(theMarker);
	  };
	
	  //Add a marker to show where you clicked.
	   theMarker = L.marker([lat,lon]).addTo(mymap); 
	   $.get("/json/predict", { lat: lat, lon: lon }).done(function(data) {
		   theMarker.bindPopup("Estimated price: " + data).openPopup();
	   });
});
</script>
</body>
</html>
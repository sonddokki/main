<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta name="viewport" content="width = device-width" />

<title>Local Twitter Search</title>

<script type="text/javascript">

function startSearch(){

var gps = navigator.geolocation;

if (gps){

gps.getCurrentPosition(searchTwitter,

function(error){

alert("Got an error, code: " + error.code + " message: "

+ error.message);

});

} else {

searchTwitter();

}

}

function searchTwitter(position){

var query = "http://search.twitter.com/search.jsoncallback=showResults&q=";

query += $("kwBox").value;

if (position){

var lat = position.coords.latitude;

var long = position.coords.longitude;

query += "&geocode=" + escape(lat + "," + long + ",50mi");

}

var script = document.createElement("script");

script.src = query;

document.getElementsByTagName("head")[0].appendChild(script);

}

</script>

</head>

<body>

	<div id="main">

		<label for="kwBox">Search Twitter:</label> <input type="text"
			id="kwBox" /> <input type="button" value="Go!"
			onclick="startSearch()" />

	</div>

	<div id="results"></div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">





<title>Insert title here</title>


</head>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=b9b0wee2jf"></script>
<body>

	<div id="map" style="width: 100%; height: 800px;"></div>

	<script>
		/*var mapOptions = {
			center : new naver.maps.LatLng(37.3595704, 127.105399),
			zoom : 14
		};

		var map = new naver.maps.Map('map', mapOptions);
		 */

		//지도를 삽입할 HTML 요소 또는 HTML 요소의 id를 지정합니다.
		var mapDiv = document.getElementById('map'); // 'map'으로 선언해도 동일

		//옵션 없이 지도 객체를 생성하면 서울 시청을 중심으로 하는 16 레벨의 지도가 생성됩니다.
		var map = new naver.maps.Map(mapDiv);

		let lat;
		let lng;
		let circle;
		let me;
		let markers;
		let infoWindows;
		let zoom = 13; // 초기 줌 레벨

		navigator.geolocation.getCurrentPosition(function(pos) {
			//혹은 하이브리드와 같은 앱의 경우 map함수에 좌표를 넣어 실행시켜주면 됨
			var latitude = pos.coords.latitude;
			var longitude = pos.coords.longitude;

			console.log("현재 위치는 : " + latitude + ", " + longitude);
			alert("현재 위치는 : " + latitude + ", " + longitude);
			loadNaverMap(latitude, longitude);

		});

		function loadNaverMap(lat, lng) {

			console.log("좌표값: " + lat + ", " + lng);
			map = new naver.maps.Map('map', {
				center : new naver.maps.LatLng(lat, lng), //좌표
				zoom : zoom, //지도의 초기 줌 레벨
				minZoom : 1, //지도의 최소 줌 레벨
				draggable : true,
				pinchZoom : true,
				scrollWheel : true,
				disableKineticPan : false, // 관성드래깅
				scaleControl : false, // 스케일 컨트롤러
				logoControl : true, // 로고 컨트롤러
				logoControlOptions : {
					position : naver.maps.Position.BOTTOM_RIGHT
				},
				mapDataControl : false,
				zoomControl : true, //줌컨트롤러
				zoomControlOptions : {
					position : naver.maps.Position.TOP_LEFT
				},
				mapTypeControl : false
			});

			markers = new naver.maps.Marker({
				position : new naver.maps.LatLng(lat, lng),
				map : map
			});

		}
	</script>



</body>
</html>
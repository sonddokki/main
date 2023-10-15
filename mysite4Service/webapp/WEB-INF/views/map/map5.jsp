<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>내 위치 확인</title>
    <!-- 네이버 지도 API 스크립트를 포함합니다. -->
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=b9b0wee2jf"></script>
    <!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js">
		
	</script>
    
</head>
<body>

    <div id="map" style="width: 100%; height: 800px;"></div>
    
    <input id="mapX" type="text" name="mapX"  value="">
    <input id="mapY" type="text" name="mapY"  value="">
    <button id="mapBtn"> 기록 시작 버튼 </button>

    <script type="text/javascript">
        let map;
        let myLocationMarker;
        var lat = "";
        var lag = "";
        
        
        $("#mapBtn").on("click", function(){
        	
        	console.log("버튼클릭");
        	
        });	

        function initMap() {
            map = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(0, 0), // 초기 맵 중심 위치
                zoom: 15, // 줌 레벨 조정
            });
            updateMyLocation();
            setInterval(updateMyLocation, 10000); // 30초마다 위치 업데이트
        }

        function updateMyLocation() {
            if ("geolocation" in navigator) {
                navigator.geolocation.getCurrentPosition(function (position) {
                	
                	lat = position.coords.latitude;
                	lag = position.coords.longitude;
                	
                	console.log("현재 위치는 : " + lat + ", " + lag);
                	
                	$("#mapX").val(lat);
                	$("#mapY").val(lag);
                	
                    const myLocation = new naver.maps.LatLng(lat, lag);   
                                        
                    if (myLocationMarker) {
                        myLocationMarker.setMap(null);
                    }
                    myLocationMarker = new naver.maps.Marker({
                        position: myLocation,
                        map: map,
                        title: "내 위치"
                    });
                    map.setCenter(myLocation);
                });
            } else {
                alert("브라우저가 위치 정보를 지원하지 않습니다.");
            }
            
        }
        
        var polyline = new naver.maps.Polyline({
			map : map,
			path : paths[0],
			strokeColor : '#5347AA',
			strokeWeight : 5,
			clickable : true,
		});
        
        
    </script>
    
    
   
    
    <script>
        // 네이버 지도 API 스크립트 로딩 후 initMap 함수 호출
        naver.maps.onJSContentLoaded = initMap;
    </script>
    
    
</body>
</html>
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
    
    <button id="startButton">시작</button>
    
    <button id="stopButton" style="display: none;">정지</button>
    
    <input id="mapX" type="text" name="mapX"  value="">
    <input id="mapY" type="text" name="mapY"  value="">

    <script>
        let map;
        let myLocationMarker;
        let linePath = [];
        let lineOverlay;
        let watchId;
        let isTracking = false;
        
     	// 위치 요청 옵션
        const options = {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
        };

        // 처음 맵구현
        function initMap() {
          // 현재 위치 가져오기
          navigator.geolocation.getCurrentPosition(success, error, options);
        }

        // 가져오기 성공
        function success(position) {
          const coords = position.coords;

          myLocation = new naver.maps.LatLng(coords.latitude, coords.longitude)
          console.log("현재 위치는 위도: " + coords.latitude + ", 경도: " + coords.longitude);

          map = new naver.maps.Map("map", {
            center: myLocation,
            zoom: 15
          });          
         
          myLocationMarker = new naver.maps.Marker({
              position: myLocation,
              map: map,              
              title: "내 위치"
          });

          // 시작버튼 클릭
          $("#startButton").on("click", startTracking);
          // 정지버튼 클릭
          $("#stopButton").on("click", stopTracking);
        }

        function error(error) {
          console.error("에러 코드: " + error.code);
          console.error("에러 메시지: " + error.message);
        }
        
        
       
     	
     	
	
        // 시작버튼 클릭 후 작동
        function startTracking() {
            if (!isTracking) {
                isTracking = true;
                $("#startButton").hide(); // 시작버튼 가리기
                $("#stopButton").show(); // 정지버튼 보이기

                // 네비게이션 기능으로 위치정보 받아오기
                watchId = navigator.geolocation.watchPosition(updateMyLocation, handleError, { enableHighAccuracy: true, maximumAge: 0 });
                
                // 20초마다 위치 업데이트
                setInterval(updateMyLocation, 20000);
            }
        }

        // 정지버튼 클릭 후 작동
        function stopTracking() {
            if (isTracking) {
                isTracking = false;
                $("#startButton").show();
                $("#stopButton").hide();

                // 위치정보 받아오기 중단
                navigator.geolocation.clearWatch(watchId);
            }
        }

        // 현재 내정보
        function updateMyLocation(position) {
        	
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
                icon: redIcon, // 빨간색 아이콘 설정
                title: "이동중 내 위치"
            });

            // 라인 그리기
            linePath.push(myLocation);
            if (lineOverlay) {
                lineOverlay.setMap(null);
            }
            
            // 라인 설정
            lineOverlay = new naver.maps.Polyline({
                map: map,
                path: linePath,
                strokeColor: "#FF0000",
                strokeWeight: 3
            });

            map.setCenter(myLocation);
        }
        

        function handleError(error) {
            console.error("위치 정보 가져오기 실패: " + error.message);
        }
        
     	// 빨간색 마커 아이콘을 생성
        var redIcon = {
          content: '<div style="background-color: red; width: 20px; height: 20px; border-radius: 50%;"></div>', // 빨간색 원 모양 아이콘
          size: new naver.maps.Size(10, 10), // 아이콘 크기 설정
          anchor: new naver.maps.Point(5, 5) // 아이콘 기준점 설정
        };
        
        
    </script>
    <script>
        // 네이버 지도 API 스크립트 로딩 후 initMap 함수 호출
        naver.maps.onJSContentLoaded = initMap;
    </script>
</body>
</html>
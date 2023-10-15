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
    
    <input id="mapX" type="text" name="mapX"  value=""> 위도 </input>
    <input id="mapY" type="text" name="mapY"  value=""> 경도 </input>

    <script>
    	
    	// 보여지는 맵
        let map;
    	// 처음 보여지는 마커
        let myLocationMarker;
    	// 라인 배열
    	let linePath = [];
    	// 라인 옵션
        let lineOverlay;
     	// 라인 옵션
        let watchId;
        // 버튼 설정
        let isTracking = false;         
        
     	
        // 처음 맵구현
        function initMap() {
          // 현재 위치 가져오기
        	navigator.geolocation.getCurrentPosition(success, error, options);             

            // 시작버튼 클릭
            $("#startButton").on("click", startTracking);
            // 정지버튼 클릭
            $("#stopButton").on("click", stopTracking);              
        }     
        
        // 가져오기 성공
        function success(position) {
          let lat = position.coords.latitude;
          let lag = position.coords.longitude;          
          
          console.log("현재 위치는 위도: " + lat + ", 경도: " + lag);
          
          map = new naver.maps.Map("map", {
            center: new naver.maps.LatLng(lat, lag),
            zoom: 15
          });          
         
          myLocationMarker = new naver.maps.Marker({
              position: new naver.maps.LatLng(lat, lag),
              map: map,              
              title: "내 위치"
          });         
        }

        function error(error) {
          console.error("에러 코드: " + error.code);
          console.error("에러 메시지: " + error.message);
        }
        
    	 // 위치 요청 옵션
        const options = {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
        };    	
     	
        // navigator.geolocation.watchPosition() 위치정보가 변하면 현재위치정보를 지속적으로 업데이트하는 함수
        // navigator.geolocation.getCurrentPosition() 현재위치정보를 업데이트하는 함수
         	
        // 시작버튼 클릭 후 작동
        function startTracking() {
            if (!isTracking) {
                isTracking = true;
                $("#startButton").hide(); // 시작버튼 가리기
                $("#stopButton").show(); // 정지버튼 보이기                
                
                console.log("시작버튼 클릭");
                
                // 네비게이션 기능으로 위치정보 받아오기 (10초마다 위치 업데이트)
                updateMyLocation();
                watchId = setInterval(updateMyLocation, 10000);                    
            }
        }
             
        // 정지버튼 클릭 후 작동
        function stopTracking() {
            if (isTracking) {
                isTracking = false;
                $("#startButton").show();
                $("#stopButton").hide();
                
                console.log("위치정보 받아오기 중단");    

                // 위치정보 받아오기 중단
                clearInterval(watchId);                            
                console.log("그려진 라인 정보 : "+ linePath);
            }           
        }
        
    	// 빨간색 마커 아이콘을 생성
        const redIcon = {
          content: '<div style="background-color: red; width: 20px; height: 20px; border-radius: 50%;"></div>', // 빨간색 원 모양 아이콘
          size: new naver.maps.Size(10, 10), // 아이콘 크기 설정
          anchor: new naver.maps.Point(5, 5) // 아이콘 기준점 설정
        };

        // 이동중 현재 위치정보
        function updateMyLocation(position) {  
        	if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
	        	
	        	let lat = position.coords.latitude;
	        	let log = position.coords.longitude;       	
	        	
	            let myLocation = new naver.maps.LatLng(lat, log); 
	            
				console.log("이동중 현재 위치는 : " + lat + ", " + log);
	        	
	        	$("#mapX").val(lat);
	        	$("#mapY").val(log);
	            
	        	// 이동위치마커 표시할때 처음위치마커 지우기
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
                });
            }
         }
        

        function handleError(error) {
            console.error("위치 정보 가져오기 실패: " + error.message);
        }
        
     	
        
        
    </script>
    <script>
        // 네이버 지도 API 스크립트 로딩 후 initMap 함수 호출
        naver.maps.onJSContentLoaded = initMap;
    </script>
</body>
</html>
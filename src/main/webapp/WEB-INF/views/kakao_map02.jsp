<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>카카오지도 내 위치</title>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f86a61f5f100a6879db473f73a5dde67"></script>
	<script type="text/javascript">
		navigator.geolocation.getCurrentPosition(function (position) {
			var lat = position.coords.latitude; 	//위도
			var lon = position.coords.longitude;	//경도
			
			document.getElementById("lat").innerHTML =lat;
			document.getElementById("lon").innerHTML =lon;
			go_map(lat,lon);
		});
	</script>
</head>
<body>
<h1>카카오지도(내 위치)</h1>

<h3>위도 : <span id="lat"></span>, 경도 : <span id="lon"></span></h3>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 100%; height: 350px;"></div>

	<script>
		function go_map(lat,lon) {
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				mapOption = {
					center : new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
					level : 3
		// 지도의 확대 레벨
		};

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);
		}
	
	
	</script>
</body>
</html>
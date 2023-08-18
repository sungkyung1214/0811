<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>īī������ �� ��ġ</title>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f86a61f5f100a6879db473f73a5dde67"></script>
	<script type="text/javascript">
		navigator.geolocation.getCurrentPosition(function (position) {
			var lat = position.coords.latitude; 	//����
			var lon = position.coords.longitude;	//�浵
			
			document.getElementById("lat").innerHTML =lat;
			document.getElementById("lon").innerHTML =lon;
			go_map(lat,lon);
		});
	</script>
</head>
<body>
<h1>īī������(�� ��ġ ��Ŀ, ����������)</h1>

<h3>���� : <span id="lat"></span>, �浵 : <span id="lon"></span></h3>
	<!-- ������ ǥ���� div �Դϴ� -->
	<div id="map" style="width: 100%; height: 350px;"></div>

	<script>
		function go_map(lat,lon) {
			var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
				mapOption = {
					center : new kakao.maps.LatLng(lat, lon), // ������ �߽���ǥ
					level : 10
		// ������ Ȯ�� ����
		};

		// ������ ǥ���� div��  ���� �ɼ�����  ������ �����մϴ�
		var map = new kakao.maps.Map(mapContainer, mapOption);
		}
		
		// ��Ŀ�� ǥ�õ� ��ġ�Դϴ� 
		var markerPosition  = new kakao.maps.LatLng(lat, lon); 

		// ��Ŀ�� �����մϴ�
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
		marker.setMap(map);

		var iwContent = '<div style="padding:5px;">�ű��ű��ű��ű�<br><a href="http://ictedu.co.kr" target="_blank">�ű��ű��ű��ű��ű��ű�</a></div>', // ���������쿡 ǥ��� �������� HTML ���ڿ��̳� document element�� �����մϴ�
		    iwPosition = new kakao.maps.LatLng(lat, lon); //���������� ǥ�� ��ġ�Դϴ�

		// ���������츦 �����մϴ�
		var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
		});
		  
		// ��Ŀ ���� ���������츦 ǥ���մϴ�. �ι�° �Ķ������ marker�� �־����� ������ ���� ���� ǥ�õ˴ϴ�
		infowindow.open(map, marker); 
	
	
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>īī������</title>
</head>
<body>

	<!-- ������ ǥ���� div �Դϴ� -->
	<h1>īī������</h1>
	<div id="map" style="width: 100%; height: 350px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f86a61f5f100a6879db473f73a5dde67"></script>
	<script>
		var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // ������ �߽���ǥ
			level : 10
		// ������ Ȯ�� ����
		};

		// ������ ǥ���� div��  ���� �ɼ�����  ������ �����մϴ�
		var map = new kakao.maps.Map(mapContainer, mapOption);
	</script>
</body>
</html>
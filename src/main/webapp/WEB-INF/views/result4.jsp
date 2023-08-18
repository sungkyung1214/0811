<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트랜잭션</title>

</head>
<body>
	<h2> 결과 보기 </h2>
    <c:choose>
        <c:when test="${res >=2 }">
            <h3>
                ${txVO.customerId }  고객님
                ${txVO.amount} 자리 결제를 하셨습니다.
                ${txVO.countnum }개 티켓을 구매 하셨습니다.
            </h3>
        </c:when>
        <c:otherwise>
            <h3>결제가 취소 되었습니다</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
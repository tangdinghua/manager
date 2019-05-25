<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, viewport-fit=cover">

    <title></title>
</head>
<body>
<div style="margin: 0 auto; color: #333; max-width: 384px;">
    <c:forEach items="list" var="item">
    <div style="font-size: 14px; margin-top: 20px;line-height: 25px;">
        <div style="margin-top: 0; font-size: 18px; font-weight: bold;">${item.subContentType}</div>
        <div>${item.content}</div>
    </div>
    </c:forEach>
</div>
</body>
</html>


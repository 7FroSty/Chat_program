<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Profile</title>
</head>
<body>
    <jsp:include page="../WEB-INF/common/menu.jsp"/>
    <div class="container">
        <div class="d-flex justify-content-center">
            <h2>Profile</h2>
        </div>
        <br>
        <h4>Email</h4>
        <p>${sessionScope.currentUser.email}</p>
        <h4>Nickname</h4>
        <p>${sessionScope.currentUser.nickname}</p>
        <h4>Age</h4>
        <p>${sessionScope.currentUser.age}</p>
        <h4>Sex</h4>
        <p>${sessionScope.currentUser.sex}</p>
        <h4>Interest</h4>
        <p>${sessionScope.currentUser.interest}</p>
        <form action="${pageContext.request.contextPath}/pages/edit-profile.jsp">
            <button id="submit" type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</body>
</html>

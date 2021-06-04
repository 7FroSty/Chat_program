<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Edit profile</title>
</head>
<body>
    <jsp:include page="../WEB-INF/common/menu.jsp"/>
    <div class="container">
        <div class="d-flex justify-content-center">
            <h2>Edit profile</h2>
        </div>
        <br>
        <form class="form" action="${pageContext.request.contextPath}/ProfileController" method="post">
            <div class="form-group">
                <label for="email"><h4>Email</h4></label>
                <input type="email" class="form-control" name="email" id="email"
                       placeholder="enter an email" value="${sessionScope.currentUser.email}">
            </div>
            <div class="form-group">
                <label for="nickname"><h4>Nickname</h4></label>
                <input type="text" class="form-control" name="nickname" id="nickname"
                       placeholder="Name" value="${sessionScope.currentUser.nickname}">
            </div>
            <div class="form-group">
                <label for="age"><h4>Age</h4></label>
                <input type="text" class="form-control" name="age" id="age"
                       placeholder="a short description" value="${sessionScope.currentUser.age}">
            </div>
            <div class="form-group">
                <label for="interest"><h4>Interest</h4></label>
                <input type="text" class="form-control" name="interest" id="interest"
                       placeholder="Name" value="${sessionScope.currentUser.interest}">
            </div>
            <div class="form-group">
                <button id="submit" type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </form>
    </div>
</body>
</html>

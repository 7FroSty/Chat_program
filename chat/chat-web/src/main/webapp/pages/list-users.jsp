<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Users</title>
</head>
<body>
    <jsp:include page="../WEB-INF/common/menu.jsp"/>
    <div class="container">
        <div class="d-flex">
            <div class="mr-auto">
                <form class="form-inline" action="${pageContext.request.contextPath}/ListUsersByNicknameController" method="post">
                    <input class="form-control mr-sm-2" type="search" name="nickname" id="nickname" placeholder="Search for nickname" aria-label="Search">
                    <button class="btn btn-primary my-2" type="submit">Search</button>
                </form>
            </div>
            <div>
                <form class="form-inline" action="${pageContext.request.contextPath}/ListUsersByInterestController" method="post">
                    <input class="form-control mr-sm-2" type="search" name="interest" id="interest" placeholder="Search for interest" aria-label="Search">
                    <button class="btn btn-primary my-2" type="submit">Search</button>
                </form>
            </div>
        </div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nickname</th>
                <th scope="col">Age</th>
                <th scope="col">Sex</th>
                <th scope="col">Interest</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.userList}">
                <tr>
                    <td>${item.nickname}</td>
                    <td>${item.age}</td>
                    <td>${item.sex}</td>
                    <td>${item.interest}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

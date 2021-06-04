<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Rooms</title>
</head>
<body>
    <jsp:include page="../WEB-INF/common/menu.jsp"/>
    <div class="container">
       <div class="d-flex">
           <div class="mr-auto">
               <form class="form-inline" action="${pageContext.request.contextPath}/ListRoomsByNameController" method="post">
                   <input class="form-control mr-sm-2" type="search" name="name" id="name" placeholder="Search for name" aria-label="Search">
                   <button class="btn btn-primary my-2" type="submit">Search</button>
               </form>
           </div>
           <div>
               <form class="form-inline" action="${pageContext.request.contextPath}/ListRoomsByCategoryController" method="post">
                   <input class="form-control mr-sm-2" type="search" name="category" id="category" placeholder="Search for category" aria-label="Search">
                   <button class="btn btn-primary my-2" type="submit">Search</button>
               </form>
           </div>
       </div>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Rules</th>
                    <th scope="col">Category</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.roomList}">
                <tr>
                    <td><a id="room-link" href="${pageContext.request.contextPath}/ChatRoomController?roomId=${item.id}">${item.name}</a></td>
                    <td id="rules">${item.rule}</td>
                    <td>${item.category}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="room" class="hu.alkfejl.model.Room" scope="request"/>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <script src="${pageContext.request.contextPath}/js/picture.js"></script>
    <title>Room - ${room.name}</title>
</head>
<body>
    <jsp:include page="../WEB-INF/common/menu.jsp"/>
    <div class="container">
        <div class="d-flex justify-content-center">
            <h2>${room.name}</h2>
        </div>
            <c:forEach var="item" items="${requestScope.commentList}">
                <c:if test="${item.message != null or item.picture != null}">
                    <div id="comment">
                        <p><c:out value="${item.user.nicknameProperty().value}"></c:out></p>
                        <hr>
                        <c:if test="${item.message != null}">
                            <p><c:out value="${item.message}"></c:out></p>
                        </c:if>
                        <c:if test="${item.picture != null}">
                            <span><img src="${item.picture}" height="100"></span>
                        </c:if>
                    </div>
                </c:if>
            </c:forEach>
        <br>
        <form id="send-message" action="${pageContext.request.contextPath}/CommentController" method="post">
            <input type="hidden" name="roomId" value="${room.id}"/>
            <div class="form-group">
                <textarea class="form-control" name="message" id="message"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Send</button>
        </form>
        <input type="file" name="picture" id="picture" accept=".png, .jpg">
        <br>
        <img id="img" height="100" class="mt-2">
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center">
            <h2>Login</h2>
        </div>
        <br>
        <form action="${pageContext.request.contextPath}/LoginController" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input required name="email" type="email" class="form-control" id="email" placeholder="Email"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input required name="password" type="password" class="form-control" id="password" placeholder="Password"/>
            </div>
            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
        </form>
        <form action="register.jsp">
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</body>
</html>

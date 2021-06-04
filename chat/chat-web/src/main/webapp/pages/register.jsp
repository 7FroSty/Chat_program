<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="../WEB-INF/common/header.jsp"/>
    <title>Register</title>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center">
            <h2>Register</h2>
        </div>
        <br>
        <form action="${pageContext.request.contextPath}/RegisterController" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input required name="email" type="email" class="form-control" id="email" placeholder="Email"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input required name="password" type="password" class="form-control" id="password" placeholder="Password"/>
            </div>
            <div class="form-group">
                <label for="nickname">Nickname</label>
                <input required name="nickname" type="text" class="form-control" id="nickname" placeholder="Nickname"/>
            </div>
            <div class="form-group">
                <label for="age">Age</label>
                <input required name="age" type="number" class="form-control" id="age" placeholder="Age"/>
            </div>
            <div class="form-group">
                <label for="sex">Sex</label>
                <br>
                <select required name="sex" class="form-select" id="sex">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option selected value="other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="interest">Interest</label>
                <input required name="interest" type="text" class="form-control" id="interest" placeholder="Interest"/>
            </div>
            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand">
    <ul class="navbar-nav w-100">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/ListUsersController">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/ListRoomsController">Rooms</a>
        </li>

        <c:if test="${sessionScope.currentUser.id != null}">
            <li class="nav-item dropdown ml-auto">
                <a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' data-toggle='dropdown'
                   aria-haspopup='true' aria-expanded='false'>
                        ${sessionScope.currentUser.nickname}
                </a>
                <div class='dropdown-menu dropdown-menu-right' aria-labelledby='navbarDropdown'>
                    <a class='dropdown-item' href='${pageContext.request.contextPath}/pages/profile.jsp'>Profile</a>
                    <a class='dropdown-item' href='${pageContext.request.contextPath}/LogoutController'>Logout</a>
                </div>
            </li>
        </c:if>
    </ul>
</nav>

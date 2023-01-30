<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>

    </head>
    <body>
<%--
    ${username}
    <c:if test="${username == 'cybersoft' && password == 'admin@123'}">
    <c:out value="Hello JSP"/>
    </c:if>
--%>

<%--
        Action: link muốn gọi đến
        Method: phương thức muốn gọi là phương thức nào(post,get)
        Thuộc tính name: là tên tham số truyền tới link ở thuộc tính
        action trong thẻ form
--%>
        <form action="http://localhost:8080/login" method="post">
            <input type="text" name="username" placeholder="Tên đăng nhập"/>
            <input type="text" name="password" placeholder="Mật khẩu"/>
            <button>Đăng nhập</button>
        </form>
    </body>
</html>
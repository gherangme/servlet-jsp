<%@ page isELIgnored="false" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<%--
    <%  %>: xử lý logic code
    <%= %>: xuất giá trị java ra màn hình giao diện
    <%! %>: khai báo biến
    Expression: ${username}

    List<User> userList = (List<User>) request.getAttribute("users");
          for(User user: userList) {
          %>
          <%= user.getFullName() %>
          <%
               }
          %>
--%>
    <head>

    </head>
    <body>
        <c:if test="${username == 'cybersoft' && password == 'admin@123'}">
            <c:out value="Hello JSP"/>
        </c:if>
    </body>
</html>
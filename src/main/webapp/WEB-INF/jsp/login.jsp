<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ithink
  Date: 17-7-31
  Time: 下午9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>小春论坛登录</title>
    </head>
    <body>
        <c:if test="${!empty error}">
            ${error}
        </c:if>
        <form action="<c:url value='/loginCheck.html'/>" method="post">
            <table border="0">
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="登录"/></td>
                    <td><input type="reset" value="重置"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>

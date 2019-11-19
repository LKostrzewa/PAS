<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lukasz
  Date: 18.11.2019
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj klienta</title>
</head>
<body>
<form:form action="add-client" modelAttribute="client">
    <table>
        <tr>
            <td><form:label path="login">Login</form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="name">Imie</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">
                Nazwisko</form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <!--<tr>
            <td><form:label path="type">
                Typ</form:label></td>
            <td><form:input path="type"/></td>
        </tr>-->
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lukasz
  Date: 10.11.2019
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj stół</title>
</head>
<body>
<form:form method="POST" action="add-resource/table-added" modelAttribute="table">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="price">price</form:label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><form:label path="numOfPeople">
                Number of people at the table</form:label></td>
            <td><form:input path="numOfPeople"/></td>
        </tr>
        <tr>
            <td><form:label path="number">
                Number of the table</form:label></td>
            <td><form:input path="number"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

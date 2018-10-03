<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<body>
<h1>Customers page</h1>
<p>Available customers: </p>
<table width="30%">
    <tr>
        <th align="left">Name</th>
        <th align="left">Surname</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.getName()}</td>
            <td>${customer.getSurname()}</td>
        </tr>
    </c:forEach>
</table>
<h3>Create new customer</h3>
<form action="<c:url value="/customer"/>" method="post">
    <table width="40%">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" title="Name"></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname" title="Surname"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
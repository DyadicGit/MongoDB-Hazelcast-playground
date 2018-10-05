<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<body>
<h1>Companies</h1>
<form method="post" action="/excel/upload" enctype="multipart/form-data">
    <input type="file" accept=".xls,.xlsx" name="file" /><br/>
    <label title="save to MongoDB?">
        <input type="checkbox" name="saveToMongodb"/>
        save to MongoDB?
    </label><br/>
    <input type="submit" value="Submit" />
</form>
<br/><br/>
<h2><c:if test="${savedToMongo}"><p style="color: blue">Successfully saved to MongoDB</p></c:if></h2>
<c:if test="${companies != null}">
    <table>
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Address</th>
            <th>County code</th>
        </tr>
        <c:forEach var="company" items="${companies}">
            <tr>
                <td>${company.getCode()}</td>
                <td>${company.getName()}</td>
                <td>${company.getAddress()}</td>
                <td>${company.getCountyCode()}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
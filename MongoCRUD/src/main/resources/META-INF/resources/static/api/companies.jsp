<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<body>
<h1>Companies</h1>
<h3>Upload excel file with companies</h3>
<form method="post" action="/excel/upload" enctype="multipart/form-data">
    <input type="file" accept=".xls,.xlsx" name="file" /><br/>
    <label title="save to MongoDB?">
        <input type="checkbox" name="saveToMongodb"/>
        save to MongoDB?
    </label><br/>
    <input type="submit" value="Submit" />
</form>
<br/><br/>
<h3>Create new company</h3>
<form action="<c:url value="/company"/>" method="post">
    <table width="40%">
        <tr>
            <td>Code</td>
            <td><input type="text" name="code" title="Code"></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" title="Name"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" title="Address"/></td>
        </tr>
        <tr>
            <td>County code</td>
            <td><input type="text" name="countyCode" title="County code"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
<br/><br/>
<form action="/company/dropCollection" method="post">
    <input value="Delete all Companies" type="submit"/>
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
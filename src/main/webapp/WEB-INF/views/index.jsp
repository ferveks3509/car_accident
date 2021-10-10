<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Accident</title>
</head>
<body>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Desc</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td><c:out value="${accident.id}"/></td>
                <td><c:out value="${accident.name}"/></td>
                <td><c:out value="${accident.text}"/></td>
                <td><c:out value="${accident.address}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
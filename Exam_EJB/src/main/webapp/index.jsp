<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des CDs</title>
</head>
<body>
<h1>Liste des CDs</h1>
<table border="1">
    <tr>
        <th>Titre</th>
        <th>Artiste</th>
        <th>Action</th>
    </tr>
    <c:forEach var="cd" items="${cds}">
        <tr>
            <td>${cd.title}</td>
            <td>${cd.artist}</td>
            <td>
                <c:choose>
                    <c:when test="${cd.available}">
                        <a href="cds?action=borrow&cdId=${cd.id}">Emprunter</a>
                    </c:when>
                    <c:otherwise>
                        <a href="cds?action=return&cdId=${cd.id}">Retourner</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

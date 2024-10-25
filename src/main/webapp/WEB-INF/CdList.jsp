<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Liste des CDs Disponibles</title></head>
<body>
<h2>CDs Disponibles</h2>
<table border="1">
    <tr><th>ID</th><th>Titre</th><th>Artiste</th><th>Emprunter</th></tr>
    <c:forEach var="cd" items="${cds}">
        <tr>
            <td>${cd.id}</td>
            <td>${cd.title}</td>
            <td>${cd.artist}</td>
            <td><a href="cds?action=borrow&cdId=${cd.id}">Emprunter</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


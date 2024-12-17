<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.appline.logic.Model" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP</title>
</head>
<body>
<h1>Домашняя страница по работе с пользователями</h1>
Ведите id пользователя
<br/>

Доступно: <%Model model=Model.getInstance();
  out.print(model.getFromList().size());
%>

<form method="Get" action="get">
  <label>ID:
    <input type="number" name="id"><br/>

  </label>
  <button type="submit">Поиск</button>
</form>

<a href="adduser.html">Создать нового пользователя</a>
</body>
</html>
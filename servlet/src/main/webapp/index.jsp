<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заголовок окна</title>
</head>
<body>
<h1>Да хрен его знает, что это за тег</h1>
<div>
    <a href="/list">Лист, откройся!</a>
</div>
<div>
    <a href="/spisok">Список здесь</a>
</div>
<div>
    <div>
        <%if (request.getCharacterEncoding() == null) {
        request.setCharacterEncoding("UTF-8");
        }
        String x = request.getParameter("input");
        x = (null != x) ? "хуи" + x : "";%>
        <%= x %>
    </div>
    <form action="/" method="post">
        <label for="input">Вводить сюда:</label>
        <input type="text" name="input" id="input" value="" placeholder="Я серый текст">
        <input type="submit" value="Саня, жми!">
    </form>
</div>
</body>
</html>


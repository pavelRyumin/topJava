<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr/>
<h2>Meals</h2>
<br>

<form method="POST" action='meals' name="frmAddMeal">
    Id : <input
        type="text" name="mealId" readonly
        value="<c:out value="${meal.id}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Date : <input
        type="text" name="date"
        value="${fn:formatDateTime(meal.dateTime)}" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Save" />
</form>
</body>
</html>

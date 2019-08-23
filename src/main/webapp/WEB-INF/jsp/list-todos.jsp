<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Todo's for ${name}</title>
    <link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Your todos</h1>
    <table class="table table-striped">
        <caption>Your todos are</caption>
        <thead>
        <tr>
            <th scope="col">Desciption</th>
            <th scope="col">Target Date</th>
            <th scope="col">Is it Done?</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%--    JSTL FOR LOOP--%>
        <c:forEach items="${todos}" var="todo">
            <tr scope="row">
                <td>${todo.desc}</td>
                <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
                <td>${todo.done}</td>
                <td><a class="btn btn-success" type="button" href="/update-todo?id=${todo.id}">UPDATE</a></td>
                <td><a class="btn btn-warning" type="button" href="/delete-todo?id=${todo.id}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <a class="button" href="/todo">Add new todo</a>
    </div>

    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</div>
</body>
</html>
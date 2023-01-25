<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!doctype html>
<html lang="en" class="h-100">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.101.0">
        <title>CoinRich(비트 가격 넣기)</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
              crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="/css/cover.css" rel="stylesheet">
    </head>
    <body class="d-flex text-center text-bg-dark">

    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <jsp:include page="../../layout/header.jsp">
            <jsp:param name="header" value="header"/>
        </jsp:include>

        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand text-light fw-bold fs-3" href="#">자유게시판</a>
                <button class="btn btn-dark btn-outline-light" onclick="location.href='/free-board/form'">글쓰기</button>
            </div>
        </nav>

        <jsp:include page="./freeBoardTable.jsp">
            <jsp:param name="freeBoardTable" value="freeBoardTable"/>
        </jsp:include>

        <jsp:include page="../../layout/footer.jsp">
            <jsp:param name="footer" value="footer"/>
        </jsp:include>
    </div>

    </body>
</html>

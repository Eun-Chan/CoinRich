<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
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
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="/css/cover.css" rel="stylesheet">
</head>
<body class="d-flex text-center text-bg-dark">

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <jsp:include page="../layout/header.jsp">
        <jsp:param name="header" value="header" />
    </jsp:include>

    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">종목</th>
            <th scope="col">기호</th>
            <th scope="col">가격(KRW)</th>
            <th scope="col">총 시가</th>
            <th scope="col">변동(1H)</th>
            <th scope="col">변동(24H)</th>
            <th scope="col">변동(7D)</th>
        </tr>
        </thead>
        <tbody id="coingeckoTbody"></tbody>
    </table>

    <jsp:include page="../layout/footer.jsp">
        <jsp:param name="footer" value="footer" />
    </jsp:include>
</div>

</body>
</html>

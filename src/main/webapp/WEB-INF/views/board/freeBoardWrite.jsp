<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- summernote -->
<%--        <link href="/css/summernote/summernote-lite.min.css" rel="stylesheet">--%>
<%--        <script src="/js/summernote/summernote-lite.min.js"></script>--%>

    </head>
    <body class="d-flex text-bg-dark">

    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <jsp:include page="../../layout/header.jsp">
            <jsp:param name="header" value="header"/>
        </jsp:include>

        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand text-light fw-bold fs-3" href="#">자유게시판</a>
            </div>
        </nav>

        <form action="/free-board/write" method="post" id="freeBoardForm">
            <div class="form-floating mb-3 h-100">
                <input type="text" class="form-control" id="floatingNickName" name="writer" placeholder="닉네임" required>
                <label for="floatingNickName" class="text-black-50">닉네임을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingPassword" name="password" placeholder="비밀번호" required>
                <label for="floatingPassword" class="text-black-50">비밀번를을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingTitle" name="title" placeholder="제목" required>
                <label for="floatingPassword" class="text-black-50">제목을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <textarea class="form-control" id="summernote" name="content" placeholder="컨텐츠" required></textarea>
            </div>
            <div class="form-floating mb-3">
                <a class="btn btn-dark btn-outline-light" href="/free-board/list">취소</a>
                <button class="btn btn-dark btn-outline-light" type="submit">등록</button>
            </div>
        </form>

        <hr>

        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">제목</th>
                <th scope="col">글쓴이</th>
                <th scope="col">작성일</th>
                <th scope="col">조회</th>
                <th scope="col">추천</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="freeBoard" items="${freeBoardList}">
                <tr>
                    <td>${freeBoard.id}</td>
                    <td>${freeBoard.title}</td>
                    <td>${freeBoard.writer}</td>
                    <td>
                        <javatime:format pattern="yyyy-MM-dd" value="${freeBoard.created_date}"/>
                    </td>
                    <td>${freeBoard.view_cnt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <!-- 처음으로 -->
                <c:if test ="${pageNumber != 0}" >
                    <li class="page-item">
                        <!-- 첫 페이지 이동 -->
                        <a class="page-link" href="/free-board/list?page=0" aria-label="first">
                            <span aria-hidden="true">&laquo;&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <!-- 이전 -->
                <c:if test = "${pageNumber != 0}" >
                    <li class="page-item">
                        <a class="page-link" href="/free-board/list?page=${pageNumber - 1}" aria-label="before">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <!-- 페이지 그룹 -->
                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                    <c:choose>
                        <c:when test="${pageNumber+1 == i}">
                            <li class="page-item disabled">
                                <a class="page-link" href="/free-board/list?&page=${i-1}">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="/free-board/list?page=${i-1}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!-- 다음 -->
                <c:if test = "${pageNumber != (totalPages - 1)}">
                    <li class="page-item">
                        <!-- 마지막 페이지 이동 -->
                        <a class="page-link" href="/free-board/list?page=${pageNumber + 1}" aria-label="after">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <!-- 마지막으로 -->
                <c:if test="${pageNumber != (totalPages - 1)}">
                    <li class="page-item">
                        <!-- 마지막 페이지 이동 -->
                        <a class="page-link" href="/free-board/list?page=${totalPages - 1}" aria-label="last">
                            <span aria-hidden="true">&raquo;&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <jsp:include page="../../layout/footer.jsp">
            <jsp:param name="footer" value="footer"/>
        </jsp:include>
    </div>

    <script>
        $(document).ready(function() {
            $('#summernote').summernote({
                placeholder : "내용을 입력하세요.",
                tabsize: 2,
                height: 500,
                lang: 'ko-KR',
                focus: true
            });
        });

        $("#freeBoardForm").addEventListener("keydown", function(event) {
            if(event.code === "Enter")
                event.preventDefault();
        });


    </script>
    </body>
</html>

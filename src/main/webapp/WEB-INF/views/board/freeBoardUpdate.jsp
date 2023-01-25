<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <form>
            <input type="hidden" id="freeBoardUpdateId" value="${freeBoard.id}">
            <div class="form-floating mb-3 h-100">
                <input type="text" class="form-control" id="freeBoardUpdateWriter" name="writer" placeholder="닉네임" maxlength="20" value="${freeBoard.writer}" required>
                <label for="freeBoardUpdateWriter" class="text-black-50">닉네임을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="freeBoardUpdatePassword" name="password" placeholder="비밀번호" maxlength="20" minlength="4" value="${freeBoard.password}" required>
                <label for="freeBoardUpdatePassword" class="text-black-50">비밀번를을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="freeBoardUpdateTitle" name="title" placeholder="제목" value="${freeBoard.title}" required>
                <label for="freeBoardUpdateTitle" class="text-black-50">제목을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <textarea class="form-control" id="UpdateSummernote" name="content" placeholder="컨텐츠" required>${freeBoard.content}</textarea>
            </div>
            <div class="form-floating mb-3 float-end">
                <a class="btn btn-dark btn-outline-light justify-content-end" href="javascript:history.back()">취소</a>
                <button class="btn btn-dark btn-outline-light justify-content-end" type="button" onclick="update();">수정</button>
            </div>
        </form>

        <hr>

        <jsp:include page="../../layout/footer.jsp">
            <jsp:param name="footer" value="footer"/>
        </jsp:include>
    </div>

    <script>
        $(document).ready(function() {
            $('#UpdateSummernote').summernote({
                placeholder : "내용을 입력하세요.",
                tabsize: 2,
                height: 500,
                lang: 'ko-KR',
                focus: true,
            });
        });

        function update() {
            let freeBoardUpdateId = $("#freeBoardUpdateId").val();

            alert(freeBoardUpdateId);

            const data = {
                writer : $("#freeBoardUpdateWriter").val(),
                password : $("#freeBoardUpdatePassword").val(),
                title : $("#freeBoardUpdateTitle").val(),
                content : $("#UpdateSummernote").val()
            }

            if(!data.writer || data.writer.trim() === "" || !data.password || data.password.trim() === "" ||
                !data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");
                return false;
            } else {
                $.ajax({
                    type : "patch",
                    url : "/api/v1/free-board/"+freeBoardUpdateId,
                    contentType: "application/json; charset=utf-8",
                    data : JSON.stringify(data),
                    dataType : "text",
                    success : function(res) {
                        alert("게시판이 수정 되었습니다.");
                        location.href = "/free-board/detail/"+freeBoardUpdateId;
                    },
                    error : function(res) {
                        location.href = history.back();
                    }
                })
            }
        };

    </script>
    </body>
</html>

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
            <div class="form-floating mb-3 h-100">
                <input type="text" class="form-control" id="freeBoardWriter" name="writer" placeholder="닉네임" maxlength="20" required>
                <label for="freeBoardWriter" class="text-black-50">닉네임을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="freeBoardPassword" name="password" placeholder="비밀번호" maxlength="20" minlength="4" required>
                <label for="freeBoardPassword" class="text-black-50">비밀번를을 입력하세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="freeBoardTitle" name="title" placeholder="제목" required>
                <label for="freeBoardTitle" class="text-black-50">제목을 입력하세요</label>
            </div>

            <%-- 사진 업로드 --%>
            <div class="input-group mb-3">
                <input type="file" class="form-control" id="file" name="file" accept="*">
                <label class="input-group-text" for="file">상

                    <div class="form-floating mb-3">품 이미지 업로드</label>
            </div>
                <textarea class="form-control" id="summernote" name="content" placeholder="컨텐츠" required></textarea>
            </div>
            <div class="form-floating mb-3 float-end">
                <a class="btn btn-dark btn-outline-light justify-content-end" href="/free-board/list">취소</a>
                <button class="btn btn-dark btn-outline-light justify-content-end" type="button" onclick="save();">등록</button>
            </div>
        </form>

        <hr>

        <jsp:include page="./freeBoardTable.jsp">
            <jsp:param name="freeBoardTable" value="freeBoardTable"/>
        </jsp:include>

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
                focus: true,
            });
        });

        // $("#freeBoardForm").addEventListener("keydown", function(event) {
        //     if(event.code === "Enter")
        //         event.preventDefault();
        // });

        function save() {
            let file = $("#file")[0].files[0];
            let formData = new FormData();
            formData.append('file', file);

            const data = {
                writer : $("#freeBoardWriter").val(),
                password : $("#freeBoardPassword").val(),
                title : $("#freeBoardTitle").val(),
                content : $("#summernote").val()
            }

            formData.append("freeBoardRequestDto", new Blob([JSON.stringify(data)], {type : "application/json"}));

            if(!data.writer || data.writer.trim() === "" || !data.password || data.password.trim() === "" ||
                !data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");
                return false;
            } else {
                $.ajax({
                    type : "post",
                    url : "/api/v1/free-board",
                    // contentType: "application/json; charset=utf-8",
                    data : formData,
                    dataType : "json",
                    processData: false,
                    contentType: false,
                    enctype:'multipart/form-data',
                    success : function(res) {
                        alert("게시판이 등록 되었습니다.");
                        location.href = "/free-board/list";
                    },
                    error : function(res) {
                        location.href = "/free-board/list";
                    }
                })
            }
        }
    </script>
    </body>
</html>

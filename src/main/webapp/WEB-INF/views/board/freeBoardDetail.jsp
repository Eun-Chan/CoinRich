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

        <!-- Custom styles for this template -->
        <link href="/css/cover.css" rel="stylesheet">
    </head>
    <body class="d-flex text-bg-dark">

    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <jsp:include page="../../layout/header.jsp">
            <jsp:param name="header" value="header"/>
        </jsp:include>

        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand text-light fw-bold fs-2" href="#">자유게시판</a>
            <div class="container-fluid justify-content-end">
                <button class="btn btn-dark btn-outline-light ms-1" onclick="location.href='/free-board/form'">글쓰기</button>
                <button type="button" class="btn btn-dark btn-outline-light ms-1" data-bs-toggle="modal" data-bs-target="#freeBoardUpdate">수정</button>
                <button type="button" class="btn btn-dark btn-outline-light ms-1" data-bs-toggle="modal" data-bs-target="#freeBoardDelete">삭제</button>
            </div>
        </nav>

        <div class="mb-3 align-content-sm-start">
            <input type="hidden" id="freeBoardId" value="${freeBoard.id}">
            <input type="hidden" id="freeBoardPassword" value="${freeBoard.password}">
            <p class="fw-bold h2">
                ${freeBoard.title}
            </p>
            <p>
                ${freeBoard.writer} |
                <javatime:format pattern="yyyy-MM-dd hh:mm:ss" value="${freeBoard.created_date}"/> |
                조회수 : ${freeBoard.view_cnt}
            </p>
            <p>${freeBoard.content}</p>
            <hr>
            <div class="float-end">
                <div class="modal fade" id="freeBoardUpdate" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 text-dark" id="staticBackdropLabel">게시물 수정</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body text-dark">
                                비밀번호를 입력하세요.
                                <input type="password" class="form-control mt-2" id="freeBoardCompareUpdate" placeholder="비밀번호를 입력하세요.">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                <button type="button" class="btn btn-primary" onclick="freeBoardUpdate()">수정</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="freeBoardDelete" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel2" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 text-dark" id="staticBackdropLabel2 ">게시물 삭제</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body text-dark">
                                비밀번호를 입력하세요.
                                <input type="password" class="form-control mt-2" id="freeBoardCompareDelete" placeholder="비밀번호를 입력하세요.">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                <button type="button" class="btn btn-primary" onclick="freeBoardDelete()">삭제</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <p class="fw-bold fs-5 text-secondary mb-sm-2">댓글</p>
        <hr/>
        <div class="mb-3 align-content-sm-start">
            <c:if test="${freeBoard.comments != null}">
                <c:forEach var="comment" items="${freeBoard.comments}" varStatus="count">
                    <div>
<%--                        <input type="text" id="commentPassword${count.index}" value="${comment.password}">--%>
                        <p class="fw-bold d-inline">
                                ${comment.writer} | <javatime:format pattern="yyyy-MM-dd hh:mm:ss" value="${comment.modified_date}"/>
                        </p>

                        <%-- 댓글 Modal --%>
                        <div class="d-inline float-end">
                            <button type="button" class="btn btn-dark btn-outline-light" data-bs-toggle="modal" data-bs-target="#commentUpdate">답글</button>
                            <button type="button" class="btn btn-dark btn-outline-light" data-bs-toggle="modal" data-bs-target="#commentDelete">삭제</button>
                        </div>

                        <div class="modal fade" id="commentUpdate" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel3" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5 text-dark" id="staticBackdropLabel3">댓글 수정</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body text-dark">
                                        비밀번호를 입력하세요.
                                        <input type="password" class="form-control mt-2" id="commentCompareUpdate" placeholder="비밀번호를 입력하세요.">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                        <button type="button" class="btn btn-primary" onclick="commentUpdate()">수정</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="commentDelete" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel4" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5 text-dark" id="staticBackdropLabel4">댓글 삭제</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body text-dark">
                                        비밀번호를 입력하세요.
                                        <input type="password" class="form-control mt-2" id="commentCompareDelete" placeholder="비밀번호를 입력하세요.">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                        <button type="button" class="btn btn-primary" onclick="commentDelete()">삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <p class="mt-3">${comment.content}</p>
                        <textarea type="text" class="w-100" style="display: none"required>${comment.content}</textarea>
                    </div>
                    <hr/>
                </c:forEach>
            </c:if>
        </div>

        <form>
            <div class="input-group">
                <input type="text" class="form-control" id="commentWriter" placeholder="닉네임을 입력하세요.">
                <input type="password" class="form-control" id="commentPassword" placeholder="비밀번호를 입력하세요.">
            </div>
            <textarea class="form-control mb-2" placeholder="댓글을 입력하세요." id="commentContent" style="height: 100px;"></textarea>
            <div class="float-end">
                <button type="button" class="btn btn-dark btn-outline-light justify-content-end" onclick="commentSave()">등록</button>
            </div>
        </form>

        <hr/>

        <jsp:include page="./freeBoardTable.jsp">
            <jsp:param name="freeBoardTable" value="freeBoardTable"/>
        </jsp:include>

        <jsp:include page="../../layout/footer.jsp">
            <jsp:param name="footer" value="footer"/>
        </jsp:include>
    </div>

    <script>
        function freeBoardUpdate() {
          let freeBoardId = $("#freeBoardId").val();
          let freeBoardPassword = $("#freeBoardPassword").val();
          let freeBoardCompareUpdate = $("#freeBoardCompareUpdate").val();

          if(freeBoardPassword != freeBoardCompareUpdate) {
              alert("비밀번호가 틀립니다.");
          } else {
              location.href = "/free-board/update/"+freeBoardId;
          }

        };

        function freeBoardDelete() {
            let freeBoardId = $("#freeBoardId").val();
            let freeBoardPassword = $("#freeBoardPassword").val();
            let freeBoardCompareDelete = $("#freeBoardCompareDelete").val();

            if(freeBoardPassword != freeBoardCompareDelete) {
                alert("비밀번호가 틀립니다.");
            } else {
                $.ajax({
                    type : "delete",
                    url : "/api/v1/free-board/"+freeBoardId,
                    contentType : "application/json; charset=utf-8",
                    success : function(res) {
                        alert("게시물이 삭제 되었습니다.");
                        location.href = "/free-board/list";
                    },
                    error : function(res) {
                        location.href = "/free-board/list";
                    }

                });
            }
        };

        function commentUpdate() {

        }

        function commentDelete() {
            let commentComparePassword = $("#commentComparePassword").val();
            alert(commentComparePassword);
        }
    </script>
    </body>
</html>

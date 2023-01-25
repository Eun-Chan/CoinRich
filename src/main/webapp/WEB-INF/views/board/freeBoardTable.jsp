<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>


    <table class="table table-hover table-dark text-center">
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">글쓴이</th>
            <th scope="col">작성일</th>
            <th scope="col">조회</th>
<%--            <th scope="col">추천</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty freeBoardList}">
                <c:forEach var="freeBoard" items="${freeBoardList}">
                    <tr onclick="location.href='/free-board/detail/${freeBoard.id}'">
                        <td>${freeBoard.id}</td>
                        <td>${freeBoard.title}</td>
                        <td>${freeBoard.writer}</td>
                        <td>
                            <javatime:format pattern="yyyy-MM-dd" value="${freeBoard.created_date}"/>
                        </td>
                        <td>${freeBoard.view_cnt}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">
                        게시물이 존재하지 않습니다.
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <c:if test="${totalPages != 0}">
                <!-- 처음으로 -->
                <c:if test="${pageNumber != 0}">
                    <li class="page-item">
                        <!-- 첫 페이지 이동 -->
                        <a class="page-link" href="/free-board/list?page=0" aria-label="first">
                            <span aria-hidden="true">&laquo;&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <!-- 이전 -->
                <c:if test="${pageNumber != 0}">
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
                <c:if test="${pageNumber != (totalPages - 1)}">
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
            </c:if>
        </ul>
    </nav>
    </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <c:import url="/fragment/import.jsp"/>

    <title>Document</title>

    <script>
        // sort
        function gotoList() {
            var selected = $('#sort option:selected').val();
            location.href = '/board/list?page=${mc.page}'
                + '&category=${mc.category}&keyword=${mc.keyword}&sort=' + selected;
        }

        // 페이지 이동
        function gotoPage(pageNo) {
            location.href = '/board/list?page=' + pageNo
                + '&category=${mc.category}&keyword=${mc.keyword}&sort=${mc.sort}';
        }

        function gotoWriteForm() {
            location.href='/board/write' +
                '?page=${mc.page}&category=${mc.category}&keyword=${mc.keyword}&sort=${mc.sort}'
        }

        $(document).ready(function () {
            $('.article').click(function () {
                var aid = $(this).data('aid');
                location.href = '/board/detail' +
                    '?page=${mc.page}&category=${mc.category}&keyword=${mc.keyword}&sort=${mc.sort}&aid=' + aid;
            });
        });
    </script>
</head>
<body>
<form action="/board/list">
    <input type="hidden" name="page" value="1">
    <div>
        <select name="category">
            <option value="title" <c:if test="${mc.category == 'title'}">selected</c:if> >제목</option>
            <option value="contents" <c:if test="${mc.category == 'contents'}">selected</c:if> >내용</option>
            <option value="register" <c:if test="${mc.category == 'register'}">selected</c:if> >글쓴이</option>
        </select>
        <input type="text" name="keyword" maxlength="10" value="${mc.keyword}">
        <input type="submit" value="검색">
    </div>
    <input type="hidden" name="sort" value="recent">
</form>
<div>
    <select id="sort" onchange="gotoList()">
        <option value="recent" <c:if test="${mc.sort == 'recent'}">selected</c:if> >최신순</option>
        <option value="last" <c:if test="${mc.sort == 'last'}">selected</c:if> >오래된순</option>
        <option value="high" <c:if test="${mc.sort == 'high'}">selected</c:if> >조회수 ↑</option>
        <option value="low" <c:if test="${mc.sort == 'low'}">selected</c:if> >조회수 ↓</option>
    </select>
</div>

<table>
    <tr>
        <td>제목</td>
        <td>조회수</td>
        <td>작성자</td>
        <td>작성일시</td>
    </tr>
    <%--실제 글 목록--%>
    <c:choose>
        <c:when test="${fn:length(mc.list) > 0}">
            <c:forEach items="${mc.list}" var="row">
                <tr class="article" data-aid="${row.id}">
                    <td>${row.title} (${row.replyCount})</td>
                    <td>${row.hits}</td>
                    <td>${row.registerLoginId}</td>
                    <td>
                    ${row.registerDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4">게시글이 없습니다.</td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<%--페이지네이션--%>
<div>
    <a href="javascript: gotoPage(1)">처음</a>
    <a href="javascript: gotoPage(${mc.prePage})">이전</a>
    <c:forEach begin="${mc.startPage}" end="${mc.endPage}" var="pageNo">
        <a href="javascript: gotoPage(${pageNo})">${pageNo}</a>
    </c:forEach>
    <a href="javascript: gotoPage(${mc.nextPage})">다음</a>
    <a href="javascript: gotoPage(${mc.totalPageCount})">마지막</a>
</div>

<div>
    <button onclick="gotoWriteForm()">글쓰기</button>
</div>
</body>
</html>










<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <c:import url="/fragment/import.jsp"/>

    <script src="/resources/js/jquery-ui.js"></script>

    <title>Document</title>

    <style>
        .popup {
            width: 300px;
            height: 300px;
            background-color: yellow;
        }
    </style>

    <script>
        function closePopup() {
            $('.popup').remove();
        }

        function closePopupToday() {
            setCookie('popup', 'Y', 1);
            closePopup();
        }

        $(document).ready(function () {
            var popupCookieValue = getCookie('popup');
            if (!popupCookieValue || popupCookieValue != 'Y') {
                $('body').append('<div class="popup">\n' +
                    '    <button onclick="closePopup()">닫기</button>\n' +
                    '    <button onclick="closePopupToday()">오늘하루안보기</button>\n' +
                    '</div>');
                $('.popup').draggable();
            }
        });
    </script>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <a href="/member/login">로그인</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a href="/member/info">마이페이지</a>
    <a href="/member/logout">로그아웃</a>
</sec:authorize>
<a href="/board/list">게시판</a>
</body>
</html>
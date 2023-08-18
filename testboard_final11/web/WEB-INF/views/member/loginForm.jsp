<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if>

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
        function login() {
            var loginId = $('#loginId');
            var password = $('#password');
            // 아이디 입력 검사
            if (loginId.val() == '') {
                alert('아이디를 입력해 주세요.');
                loginId.focus();
                return false;
            }

            // 비밀번호 입력 검사
            if (password.val() == '') {
                alert('비밀번호를 입력해 주세요.');
                password.focus();
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<form action="/member/login" method="post" onsubmit="return login();">
    <div>
        아이디
        <input type="text" name="loginId" id="loginId">
    </div>
    <div>
        비밀번호
        <input type="password" name="password" id="password">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="로그인">
</form>
<div>
    <a href="/member/join">회원가입</a>
    <a href="/member/find/id">아이디찾기</a>
    <a href="/member/find/password">비밀번호찾기</a>
</div>
</body>
</html>











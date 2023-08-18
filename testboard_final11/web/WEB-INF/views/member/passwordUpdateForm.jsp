<%@ page import="static www.kb.common.RegexpConstants.REGEXP_PASSWORD" %>
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

    <title>Document</title>

    <script>
        function updatePassword() {
            var password = $('#password');
            var passwordCheck = $('#passwordCheck');

            // 비밀번호 입력 여부 확인
            if (password.val() == '') {
                alert('비밀번호는 필수 입력 값입니다.');
                password.focus();
                return;
            }
            // 비밀번호확인 입력 여부 확인
            if (passwordCheck.val() == '') {
                alert('비밀번호확인은 필수 입력 값입니다.');
                passwordCheck.focus();
                return;
            }

            // 비밀번호 정규표현식 통과 여부 확인
            var regexp = new RegExp('<%=REGEXP_PASSWORD%>');
            if (regexp.exec(password.val()) == null) {
                alert('비밀번호는 4~15자의 영문, 숫자, 특수기호(!,@,#,$)로 입력해 주세요');
                password.focus();
                return;
            }

            // 비밀번호 == 비밀번호확인 여부 확인
            if (password.val() != passwordCheck.val()) {
                alert('비밀번호와 비밀번호확인은 동일하게 입력해 주세요.');
                return;
            }

            callFormService(
                $('#updateForm')
                , "/member/password/update"
                , function (response) {
                    alert(response.message);
                    if (response.isSuccess) {
                        location.replace('/member/login');
                    }
                }
            );
        }
    </script>
</head>
<body>
<form id="updateForm">
    <div>
        비밀번호
        <input type="password" name="password" maxlength="15" id="password"
               placeholder="4~15자의 영문, 숫자, 특수기호(!,@,#,$)로 입력해 주세요.">
    </div>
    <div>
        비밀번호확인
        <input type="password" name="passwordCheck" maxlength="15" id="passwordCheck"
               placeholder="비밀번호와 동일하게 입력해 주세요.">
    </div>
</form>
<button onclick="updatePassword()">변경</button>
<button onclick="location.href='/'">취소</button>
</body>
</html>





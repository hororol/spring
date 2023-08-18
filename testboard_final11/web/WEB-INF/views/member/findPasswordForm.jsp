<%@ page import="static www.kb.common.RegexpConstants.REGEXP_NAME" %>
<%@ page import="static www.kb.common.RegexpConstants.REGEXP_MOBILE_NO" %>
<%@ page import="static www.kb.common.RegexpConstants.*" %>
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
        function findPassword() {
            var loginId = $('#loginId');
            var name = $('#name');
            var mobileNo = $('#mobileNo');
            var email = $('#email');

            // 아이디 입력 여부 확인
            if (loginId.val() == '') {
                alert('아이디는 필수 입력 값입니다.');
                loginId.focus();
                return;
            }

            // 이름 입력 여부 확인
            if (name.val() == '') {
                alert('이름은 필수 입력 값입니다.');
                name.focus();
                return;
            }
            // 휴대전화번호 입력 여부 확인
            if (mobileNo.val() == '') {
                alert('휴대전화번호는 필수 입력 값입니다.');
                mobileNo.focus();
                return;
            }
            // 이메일 입력 여부 확인
            if (email.val() == '') {
                alert('이메일은 필수 입력 값입니다.');
                email.focus();
                return;
            }

            // 이름 정규표현식 통과 여부 확인
            var regexp = new RegExp('<%=REGEXP_NAME%>');
            if (regexp.exec(name.val()) == null) {
                alert('이름은 2~10자의 한글로 입력해 주세요');
                name.focus();
                return;
            }

            // 휴대전화번호 정규표현식 통과 여부 확인
            regexp = new RegExp('<%=REGEXP_MOBILE_NO%>');
            if (regexp.exec(mobileNo.val()) == null) {
                alert('휴대전화번호는 10~12자 숫자로 입력해 주세요.');
                mobileNo.focus();
                return;
            }
            // 이메일 정규표현식 통과 여부 확인
            regexp = new RegExp('<%=REGEXP_EMAIL%>');
            if (regexp.exec(email.val()) == null) {
                alert('이메일은 50자 이내로 입력해 주세요.');
                email.focus();
                return;
            }

            callFormService(
                $('#searchForm')
                , "/member/find/password"
                , function (response) {
                    if (response.isSuccess) {
                        if (!response.message) {
                            location.href = '/member/password/update';
                        } else {
                            alert(response.message);
                        }
                    } else {
                        alert(response.message);
                    }
                }
            );
        }
    </script>
</head>
<body>

<form id="searchForm">
    <div>
        아이디
        <input type="text" name="loginId" id="loginId" maxlength="15">
    </div>
    <div>
        이름
        <input type="text" name="name" id="name" maxlength="10">
    </div>
    <div>
        휴대전화번호
        <input type="text" name="mobileNo" id="mobileNo" maxlength="12">
    </div>
    <div>
        이메일
        <input type="text" name="email" id="email" maxlength="50">
    </div>
</form>
<button onclick="findPassword()">확인</button>
<button onclick="location.href='/member/login'">취소</button>

</body>
</html>









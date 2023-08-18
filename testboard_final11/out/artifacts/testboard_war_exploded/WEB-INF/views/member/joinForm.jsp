<%@ page import="static www.kb.common.RegexpConstants.REGEXP_LOGIN_ID" %>
<%@ page import="static www.kb.common.RegexpConstants.REGEXP_PASSWORD" %>
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
        // 아이디 중복 검사 여부
        var checkDuplication = false;

        function checkId() {
            // 아이디 입력란에 입력한 데이터 저장
            var input = $('#loginId').val();
            if (input == '') {
                alert('아이디를 입력해 주세요.');
                $('#loginId').focus();
                return;
            }

            var regexp = new RegExp('<%=REGEXP_LOGIN_ID%>');
            if (regexp.exec(input) == null) {
                alert('아이디는 4~15자의 영문소문자, 숫자로 입력해 주세요.');
                $('#loginId').focus();
                return;
            }

            // 비동기통신을 통해 아이디 존재 여부 확인
            callService(
                false
                , "/member/check/id"
                , {
                    loginId: input
                }
                , function (response) {
                    alert(response.message);
                    if (response.isUsable) {
                        checkDuplication = true;
                    } else {
                        $('#loginId').focus();
                    }
                }
            );
        }

        function initCheck() {
            checkDuplication = false;
        }

        function joinMember() {
            var loginId = $('#loginId');
            var password = $('#password');
            var passwordCheck = $('#passwordCheck');
            var name = $('#name');
            var birthday = $('#birthday');
            var mobileNo = $('#mobileNo');
            var email = $('#email');
            var zipcode = $('#zipcode');
            var address = $('#address');
            var detailAddress = $('#detailAddress');

            // 아이디 입력 여부 확인
            if (loginId.val() == '') {
                alert('아이디는 필수 입력 값입니다.');
                loginId.focus();
                return;
            }
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

            var addressFlag = false;
            // 주소형제가 하나라도 입력되어 있다면 우편번호, 주소, 상세주소 입력 여부 확인
            if (zipcode.val() != '' || address.val() != '' || detailAddress.val() != '') {
                addressFlag = true;
            }

            if (addressFlag
                && (zipcode.val() == ''
                    || address.val() == ''
                    || detailAddress.val() == '')) {
                alert('주소를 모두 입력해 주세요.');
                return;
            }

            // 아이디 정규표현식 통과 여부 확인
            var regexp = new RegExp('<%=REGEXP_LOGIN_ID%>');
            if (regexp.exec(loginId.val()) == null) {
                alert('아이디는 4~15자의 영문소문자, 숫자로 입력해 주세요.');
                loginId.focus();
                return;
            }

            // 비밀번호 정규표현식 통과 여부 확인
            regexp = new RegExp('<%=REGEXP_PASSWORD%>');
            if (regexp.exec(password.val()) == null) {
                alert('비밀번호는 4~15자의 영문, 숫자, 특수기호(!,@,#,$)로 입력해 주세요');
                password.focus();
                return;
            }
            // 이름 정규표현식 통과 여부 확인
            regexp = new RegExp('<%=REGEXP_NAME%>');
            if (regexp.exec(name.val()) == null) {
                alert('이름은 2~10자의 한글로 입력해 주세요');
                name.focus();
                return;
            }
            // 생년월일이 입력되어 있다면 정규표현식 통과 여부 확인
            regexp = new RegExp('<%=REGEXP_BIRTHDAY%>');
            if (birthday.val() != '' && regexp.exec(birthday.val()) == null) {
                alert('생년월일은 8자리의 숫자로 입력해 주세요.');
                birthday.focus();
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
            // 주소형제가 하나라도 입력되어 있다면 우편번호 정규표현식 통과 여부 확인
            // 주소형제가 하나라도 주소 정규표현식 통과 여부 확인
            // 주소형제가 하나라도 상세주소 정규표현식 통과 여부 확인
            if (addressFlag) {
                regexp = new RegExp('<%=REGEXP_ZIPCODE%>');
                if (regexp.exec(zipcode.val()) == null) {
                    alert('주소 검색 버튼을 이용하여 주소를 검색해 주세요.');
                    zipcode.focus();
                    return;
                }

                regexp = new RegExp('<%=REGEXP_ADDRESS%>');
                if (regexp.exec(address.val()) == null) {
                    alert('주소 검색 버튼을 이용하여 주소를 검색해 주세요.');
                    address.focus();
                    return;
                }

                regexp = new RegExp('<%=REGEXP_ADDRESS_DETAIL%>');
                if (regexp.exec(detailAddress.val()) == null) {
                    alert('상세 주소는 100자 이내로 입력해 주세요.');
                    detailAddress.focus();
                    return;
                }
            }

            // 아이디 중복 체크 여부 확인
            if (!checkDuplication) {
                alert('아이디 중복 체크를 진행해 주세요.');
                return;
            }

            // 비밀번호 == 비밀번호확인 여부 확인
            if (password.val() != passwordCheck.val()) {
                alert('비밀번호와 비밀번호확인은 동일하게 입력해 주세요.');
                return;
            }

            callFormService(
                $('#joinForm')
                , "/member/join"
                , function (response) {
                    if (response.isSuccess) {
                        location.replace("/member/join/result");
                    } else {
                        alert(response.message);
                    }
                }
            );
        }
    </script>
    <style>
        ::placeholder {
            font-size: 9px;
        }
    </style>
</head>
<body>
<h5>회원가입</h5>

<form method="post" id="joinForm">
    <div>
        아이디
        <input type="text" name="loginId" maxlength="15" id="loginId"
               placeholder="4~15자의 영문소문자, 숫자로 입력해 주세요."
               oninput="initCheck()">
        <button type="button" onclick="checkId()">중복확인</button>
    </div>
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
    <div>
        이름
        <input type="text" name="name" maxlength="10" id="name"
               placeholder="2~10자의 한글로 입력해 주세요.">
    </div>
    <div>
        생년월일
        <input type="text" name="strBirthday" maxlength="8" id="birthday"
               placeholder="입력예) 19991231">
    </div>
    <div>
        휴대전화번호
        <input type="text" name="mobileNo" maxlength="12" id="mobileNo"
               placeholder="-를 제외한 숫자로 입력해 주세요">
    </div>
    <div>
        이메일
        <input type="email" name="email" maxlength="50" id="email"
               placeholder="-를 제외한 숫자로 입력해 주세요">
    </div>
    <div>
        주소
        <button type="button" onclick="DaumPostcode()">검색</button>
        <input type="text" name="zipcode" maxlength="5" readonly id="zipcode">
        <input type="text" name="address" maxlength="100" readonly id="address">
        <input type="text" name="addressDetail" maxlength="100" readonly id="detailAddress">
    </div>
</form>
<button onclick="joinMember()">가입</button>
<button onclick="location.href='/'">취소</button>

</body>
</html>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/daumPost.js"></script>










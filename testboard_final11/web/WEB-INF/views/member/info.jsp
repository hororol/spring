<%@ page import="java.time.format.DateTimeFormatter" %>
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
        function updateMember() {
            var name = $('#name');
            var birthday = $('#birthday');
            var mobileNo = $('#mobileNo');
            var email = $('#email');
            var zipcode = $('#zipcode');
            var address = $('#address');
            var detailAddress = $('#detailAddress');

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

            // 이름 정규표현식 통과 여부 확인
            var regexp = new RegExp('<%=REGEXP_NAME%>');
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

            callFormService(
                $('#updateForm')
                , "/member/info/update"
                , function (response) {
                    alert(response.message);
                    if (response.isSuccess) {
                        location.reload();
                    }
                }
            );

            function leave() {
                if (!confirm("정말 탈퇴 하시겠습니까?")) {
                    return;
                }

                location.href = '/member/leave';
            }
        }
    </script>
    <style>
        ::placeholder {
            font-size: 9px;
        }
    </style>
</head>
<body>
<h5>회원정보</h5>

<form method="post" id="updateForm">
    <div>
        이름
        <input type="text" name="name" maxlength="10" id="name"
               placeholder="2~10자의 한글로 입력해 주세요." value="${dto.name}">
    </div>
    <div>
        생년월일
        <input type="text" name="strBirthday" maxlength="8" id="birthday"
               placeholder="입력예) 19991231" value="${dto.birthday.format(DateTimeFormatter.ofPattern("yyyyMMdd"))}">
    </div>
    <div>
        휴대전화번호
        <input type="text" name="mobileNo" maxlength="12" id="mobileNo"
               placeholder="-를 제외한 숫자로 입력해 주세요" value="${dto.mobileNo}">
    </div>
    <div>
        이메일
        <input type="email" name="email" maxlength="50" id="email"
               placeholder="-를 제외한 숫자로 입력해 주세요" value="${dto.email}">
    </div>
    <div>
        주소
        <button type="button" onclick="DaumPostcode()">검색</button>
        <input type="text" name="zipcode" maxlength="5" readonly id="zipcode" value="${dto.zipcode}">
        <input type="text" name="address" maxlength="100" readonly id="address" value="${dto.address}">
        <input type="text" name="addressDetail" maxlength="100" readonly id="detailAddress" value="${dto.addressDetail}">
    </div>
</form>
<button onclick="updateMember()">수정</button>
<button onclick="leave()">회원탈퇴</button>
<button onclick="location.href='/'">취소</button>

</body>
</html>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/daumPost.js"></script>










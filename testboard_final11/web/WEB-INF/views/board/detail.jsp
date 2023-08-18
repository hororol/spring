<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

    <style>
        .box {
            border: 1px solid #000000;
        }
    </style>

    <script>
        function gotoUpdateArticle() {
            location.href = '/board/update?page=${listInfoDTO.page}&category=${listInfoDTO.category}&keyword=${listInfoDTO.keyword}&sort=${listInfoDTO.sort}&aid=${listInfoDTO.aid}';
        }
        function deleteArticle() {
            callService(
                true
                ,'/board/delete'
                , {
                    aid : ${listInfoDTO.aid}
                }
                , function (response) {
                    alert(response.message);
                    if (response.isSuccess) {
                        location.replace('/board/list?' +
                            'page=${listInfoDTO.page}&category=${listInfoDTO.category}&keyword=${listInfoDTO.keyword}&sort=${listInfoDTO.sort}')
                    }
                }
            );
        }
        function gotoList() {
            location.href = '/board/list?page=${listInfoDTO.page}&category=${listInfoDTO.category}&keyword=${listInfoDTO.keyword}&sort=${listInfoDTO.sort}'
        }

        function registerReply() {
            var contents = $('input[name=contents]');

            if (contents.val() == '') {
                alert("댓글 내용을 입력해 주세요.");
                contents.focus();
                return;
            }

            if (contents.val().length > 100) {
                alert("댓글은 100자 이내로 입력해 주세요.");
                contents.focus();
                return;
            }

            <sec:authorize access="isAnonymous()">
            alert('로그인이 필요한 서비스 입니다.');
            location.href='/member/login';
            </sec:authorize>

            callFormService(
                $('#replyForm')
                , "/board/reply/register"
                , function (response) {
                    alert(response.message);
                    if (response.isSuccess) {
                        location.reload();
                    }
                }
            );
        }
    </script>
</head>
<body>
<div>
    제목
    <div class="box">${dto.title}</div>
</div>
<div>
    내용
    <div class="box">${dto.contents}</div>
</div>
<div>
    작성자
    <div class="box">${dto.registerLoginId}</div>
</div>
<div>
    작성일시
    <div class="box">${dto.registerDatetime}</div>
</div>
<div>
    조회수
    <div class="box">${dto.hits}</div>
</div>

<div style="border: 2px solid #000">
    <c:choose>
        <c:when test="${fn:length(dto.replyList) > 0}">
            <c:forEach items="${dto.replyList}" var="reply">
                <div style="border: 1px solid red">
                    <div>
                        작성자 : ${reply.registerLoginId}
                    </div>
                    <div>
                        작성일시 : ${reply.registerDatetime}
                    </div>
                    <div>
                        내용
                        <div>
                            ${reply.contents}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            댓글이 존재하지 않습니다.
        </c:otherwise>
    </c:choose>
</div>

<form id="replyForm">
    <div>
        <input type="text" name="contents">
    </div>
    <input type="hidden" name="aid" value="${listInfoDTO.aid}">
</form>
<button onclick="registerReply()">댓글작성</button>

<c:if test="${dto.register}">
    <button onclick="gotoUpdateArticle()">수정</button>
    <button onclick="deleteArticle()">삭제</button>
</c:if>
<button onclick="gotoList()">목록</button>
</body>
</html>

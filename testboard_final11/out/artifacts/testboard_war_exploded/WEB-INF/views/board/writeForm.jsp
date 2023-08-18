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

    <%-- summernote plugin --%>
    <script src="/resources/plugin/summernote/summernote-lite.js"></script>
    <script src="/resources/plugin/summernote/lang/summernote-ko-KR.js"></script>
    <link rel="stylesheet" href="/resources/plugin/summernote/summernote-lite.css">

    <title>Document</title>

    <script>
        function register() {
            var title = $('input[name=title]');
            var contents = $('#editor').summernote('code');

            if (title.val() == '') {
                alert('제목을 입력해 주세요.');
                title.focus();
                return;
            }

            if (contents == '<p><br></p>') {
                alert('내용을 입력해 주세요.');
                $('#editor').focus();
                return;
            }

            if (title.val().length > 50) {
                alert('제목은 50자 이내로 입력해 주세요.');
                title.focus();
                return;
            }

            callService(
                true
                , "/board/write"
                , {
                    title : title.val()
                    , contents : contents
                }
                , function (response) {
                    alert(response.message);
                    if (response.isSuccess) {
                        location.replace("/board/list");
                    }
                }
            );
        }

        function gotoList() {
            location.href = '/board/list?' +
                'page=${listInfoDTO.page}&category=${listInfoDTO.category}&keyword=${listInfoDTO.keyword}&sort=${listInfoDTO.sort}'
        }

        $(document).ready(function () {
            $('#editor').summernote({
                height: 300,                 // 에디터 높이
                minHeight: null,             // 최소 높이
                maxHeight: null,             // 최대 높이
                focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
                lang: "ko-KR",					// 한글 설정
                callbacks: {	//여기 부분이 이미지를 첨부하는 부분
                    onImageUpload: function (files) {
                        uploadSummernoteImageFile(files[0], this);
                    },
                    onPaste: function (e) {
                        var clipboardData = e.originalEvent.clipboardData;
                        if (clipboardData && clipboardData.items && clipboardData.items.length) {
                            var item = clipboardData.items[0];
                            if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                                e.preventDefault();
                            }
                        }
                    }
                }
            });
        });

        function uploadSummernoteImageFile(file, editor) {
            var data = new FormData();
            data.append("file", file);

            $.ajax({
                data: data
                , type: "POST"
                , url: "/image/upload"
                , enctype: "multipart/form-data"
                , contentType: false
                , processData: false
                , beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"))
                }
                , error: function (request, error) {
                    alert('서비스 호출에 실패하였습니다.');
                    console.log("code = " + request.status);
                    console.log("message = " + request.responseText);
                    console.log("error = " + error);
                }
                , success: function (data) {
                    //항상 업로드된 파일의 url이 있어야 한다.
                    $(editor).summernote('insertImage', data.url);
                }
            });
        }
    </script>
</head>
<body>
<form id="writeForm">
    <div>
        제목
        <input type="text" name="title" maxlength="50">
    </div>
    <div>
        내용
        <textarea id="editor" name="contents"></textarea>
    </div>
</form>

<button onclick="register()">저장</button>
<button onclick="gotoList()">취소</button>
</body>
</html>

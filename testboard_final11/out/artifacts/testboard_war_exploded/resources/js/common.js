function getValueOfParameter(key) {
    var urlParams = new URLSearchParams(location.search);
    var value = urlParams.get(key);
    if (value == null || value == undefined) {
        value = "";
    }
    return value;
}

function callService(async, url, data, callback) {
    $.ajax({
        url : url
        , type : "post"
        , dataType : "json"
        , async: async
        , data : data
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"))
        }
        , error : function (request, error) {
            alert('서비스 호출에 실패하였습니다.');
            console.log("code = " + request.status);
            console.log("message = " + request.responseText);
            console.log("error = " + error);
        }
        , success: function (response) {
            return callback(response);
        }
    });
}

function callFormService($object, url, callback) {
    $object.ajaxForm({
        url: url
        , dataType: "json"
        , method: "post"
        , async: true
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"))
        }
        , error: function (request, error) {
            alert('서비스 호출에 실패하였습니다.');
            console.log("code = " + request.status);
            console.log("message = " + request.responseText);
            console.log("error = " + error);
        }
        , success: function (response) {
            return callback(response);
        }
    }).submit();
}

function setCookie(cookieName, cookieValue, day) {
    var regexp = new RegExp('^[0-9]*$');
    if (day && regexp.exec(day) == null) {
        console.log("setCookie 함수 파라메터 에러 : 3번째 파라메터는 양의 정수만 입력할 수 있습니다.");
        return false;
    }
    var date = new Date();
    date.setDate(date.getDate() + day);
    var value = escape(cookieValue) + (day ? ';expires=' + date.toUTCString() : '');
    document.cookie = cookieName + '=' + value;
    return true;
}

function getCookie(cookieName) {
    var x, y;
    var val = document.cookie.split(';');

    for (var i = 0; i < val.length; i++) {
        x = val[i].substr(0, val[i].indexOf('='));
        y = val[i].substr(val[i].indexOf('=') + 1);
        x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
        if (x == cookieName) {
            return unescape(y); // unescape로 디코딩 후 값 리턴
        }
    }
}
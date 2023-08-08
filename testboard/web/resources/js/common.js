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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    <c:if test="${not empty message}">
    alert("${message}");
    </c:if>

    history.back();
</script>

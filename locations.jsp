<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Locations</title>
</head>
<body>


<table>
    <c:forEach items="${sessionScope.locations}" var="location">
        <tr>
            <td><a href="/CompanyInfo/rest/departmentsPerLocation/${location.locno}"><c:out value="${location.locno}"/></a></td>
            <td><c:out value="${location.locname}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

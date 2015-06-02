<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>

<table>
    <c:forEach items="${sessionScope.employees}" var="employee">
        <tr>
            <td><a href="/CompanyInfo/rest/employeeDetails/${employee.empno}"><c:out value="${employee.empno}"/></a></td>
            <td><c:out value="${employee.ename}" /></td>
            <td><c:out value="${employee.job}"/></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Details</title>
</head>
<body>

<p>empno <c:out value="${sessionScope.employee.empno}"/></p>
<p>ename <c:out value="${sessionScope.employee.ename}" /></p>
<p>job <c:out value="${sessionScope.employee.job}"/></p>
<p>hireDate <c:out value="${sessionScope.employee.hireDate}"/></p>
<p>salary <c:out value="${sessionScope.employee.sal}"/></p>
<p>comm <c:out value="${sessionScope.employee.comm}"/></p>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Список паспортов</title>
	</head>
	<body>
		<jsp:useBean id="passportService" class="ru.lenoblgis.introduse.sergey.services.PassportService"
	   scope="request"/>
	   
	<H1>PASSPORTS LIST</H1>
	<table>
	  <c:forEach var="passport" items="${reviewingPassportsList}">
		  <tr>
		    <td>${passport.getId()}</td>
		    <td>${passport.getNameOwner()}</td>
		    <td>${passport.getRegion()}</td>
		    <td>${passport.getCadastrNumber()}</td>
		    <td>${passport.getArea()}</td>
		    <td>${passport.getType()}</td>
		    <td>${passport.getComment()}</td>
		  </tr>
	  </c:forEach>
	</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information about Passports</title>
</head>
<body>
	<jsp:useBean id="eventService" class="ru.lenoblgis.introduse.sergey.services.EventService"
	   scope="request"/>
	   
	<H1>HELLO!!!</H1>
	<table>
	  <c:forEach var="info" items="${eventService.getAllOwnerEvents(id_organization)}">
		  <tr>
		    <td>${info.get("id")}</td>
		    <td>${info.get("id_passport")}</td>
		    <td>${info.get("id_organization")}</td>
		    <td>${info.get("message")}</td>
		    <td>${info.get("date_time_event")}</td>
		    <td>${info.get("type_event")}</td>
		    <td><td><input type="submit" name="lookPassportBtn" id="hg" value="Посмотреть паспорт"></td>
		  </tr>
	  </c:forEach>
	</table>
</body>
</html>
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
	  <c:forEach var="event" items="${eventService.getAllOwnerEvents(id_organization)}">
		  <tr>
		    <td>${event.get("id")}</td>
		    <td>${event.get("id_passport")}</td>
		    <td>${event.get("id_organization")}</td>
		    <td>${event.get("message")}</td>
		    <td>${event.get("date_time_event")}</td>
		    <td>${event.get("type_event")}</td>
		  </tr>
	  </c:forEach>
	</table>
</body>
</html>
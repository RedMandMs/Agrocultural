<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Просмотр пасспорта</title>
	</head>
	<body>
		<form name="passportInfo" title="Информация о компании">
			<table>
				<tr>
					<td>Id пасспорта: </td>
					<td>${reviewingPassport.getId()}</td>
				</tr>
				
				<tr>
					<td>Компания-владелец паспорта: </td>
					<td>${reviewingPassport.getNameOwner()}</td>
				</tr>
				
				<tr>
					<td>Район поля: </td>
					<td>${reviewingPassport.getRegion()}</td>
				</tr>
				
				<tr>
					<td>Кадастровый номер: </td>
					<td>${reviewingPassport.getCadastrNumber()}</td>
				</tr>
				
				<tr>
					<td>Площадь поля: </td>
					<td>${reviewingPassport.getArea()}</td>
				</tr>
				
				<tr>
					<td>Тип поля: </td>
					<td>${reviewingPassport.getType()}</td>
				</tr>
				
				<tr>
					<td>Комментарий: </td>
					<td>${reviewingPassport.getComment()}</td>
				</tr>
				
				<c:set var="isMyPassport" scope="request" value="${isMyPassport}"/>
				<c:if test="${isMyPassport}">
					<form method="GET" action="change_passport_info/"+${reviewingPassport.getId()} >
						<input type="submit" name="changePassportInfoBtn" value="Изменить информацию о пасспорте">
					</form>
				</c:if>
			</table>
		</form>
	</body>
</html>
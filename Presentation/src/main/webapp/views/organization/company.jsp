<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Информация о комапании</title>
	</head>
	<body>
		<form method="get" action="change_organization_info">
		<table>
			<tr>
				<td>Имя компании: </td>
				<td>${reviewingCompany.getName()}</td>
			</tr>
			
			<tr>
				<td>ИНН компании: </td>
				<td>${reviewingCompany.getInn()}</td>
			</tr>
			
			<tr>
				<td>Адрес компании: </td>
				<td>${reviewingCompany.getAddress()}</td>
			</tr>
			<tr>
					<td><input type="submit" name="changeCopanyInfoBtn" value="Изменить информацию о компании"></td>
					<td><input type="submit" name="lookPassportsBtn" value="Посмотреть все паспорта пренадлежащие компании"></td>
			</tr>
		</table>
		</form>
	</body>
</html>
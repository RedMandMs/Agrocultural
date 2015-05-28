<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Изменение информации о компании</title>
	</head>
	<body>
		<div>
		<h1>Изменение данных о компании:</h1>	
		<a>${message}</a>
		<sf:form method="POST" modelAttribute="changedCompany">
			<fieldset>
				<table>
					<tr>
						<th><label for="name_organization">Введите имя компании: </label></th>
						<td><sf:input path="name" size="20" id="name_organization" value="${myCompany.getName()}"/></td>
					</tr>
					
					<tr>
						<th><label for="inn">Введите ИНН компании: </label></th>
						<td><sf:input path="inn" size="20" id="inn" value="${myCompany.getInn()}"/></td>
					</tr>
					
					<tr>
						<th><label for="organization_address">Введите адрес компании: </label></th>
						<td><sf:input path="address" size="50" id="organization_address" value="${myCompany.getAddress()}"/></td>
					</tr>
					
					<tr>
						<td><input type="submit" name="changedBtn" value="Изменить информацию о компании"></td>
					</tr>
				</table>
			</fieldset>
		</sf:form>
	</div>
	</body>
</html>
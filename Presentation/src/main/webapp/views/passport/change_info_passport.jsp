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
		<div>
			<h1>Изменение данных о пасспорте:</h1>	
			<a>${message}</a>
			<sf:form method="POST" modelAttribute="changedPassport">
				<fieldset>
					<table>
						<tr>
							<td>Id пасспорта: </td>
							<td>${changedPassport.getId()}</td>
						</tr>
						
						<tr>
							<td>Компания-владелец паспорта: </td>
							<td>${changedPassport.getNameOwner()}</td>
						</tr>
					
						<tr>
							<th><label for="region">Введите регион поля: </label></th>
							<td><sf:input path="region" size="20" id="region" value="${changedPassport.getRegion()}"/></td>
						</tr>
						
						<tr>
							<th><label for="area">Введите площадь поля: </label></th>
							<td><sf:input path="area" size="50" id="area" value="${changedPassport.getArea()}"/></td>
						</tr>
						
						<tr>
							<th><label for="cadastrNumber">Введите кадастровый номер паспорта: </label></th>
							<td><sf:input path="cadastrNumber" size="20" id="cadastrNumber" value="${changedPassport.getCadastrNumber()}"/></td>
						</tr>
						
						<tr>
							<th><label for="type">Введите тип поля: </label></th>
							<td><sf:input path="type" size="50" id="type" value="${changedPassport.getType()}"/></td>
						</tr>
						
						<tr>
							<th><label for="comment">Введите комментарий к пасспорту: </label></th>
							<td><sf:input path="comment" size="50" id="comment" value="${changedPassport.getComment()}"/></td>
						</tr>
						
						<tr>
							<td><input type="submit" name="changedBtn" value="Изменить информацию о пасспорте"></td>
						</tr>
					</table>
				</fieldset>
			</sf:form>
		</div>
	</body>
</html>
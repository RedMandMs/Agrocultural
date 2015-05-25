<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Регистрация нового пользователя</title>
</head>
<body>
	<form name="registration" title="Регистрация нового пользователя">
		<table>
			<tr>
				<td>Введите логин: </td>
				<td><input type="text" name="login" size="20" maxlength="20"></td>
			</tr>
			
			<tr>
				<td>Введите пароль: </td>
				<td><input type="password" name="password" size="20" maxlength="20"></td>
			</tr>
			
			<tr>
				<td>Повторно введите пароль: </td>
				<td><input type="password" name="rePassword" size="20" maxlength="20"></td>
			</tr>
			
			<tr>
				<td>Введите имя компании: </td>
				<td><input type="text" name="rePassword" size="20" maxlength="20"></td>
			</tr>
			
			<tr>
				<td>Введите ИНН компании: </td>
				<td><input type="text" name="inn" size="20" maxlength="20"></td>
			</tr>
			
			<tr>
				<td>Введите адрес компании: </td>
				<td><input type="text" name="address" size="40" maxlength="40"></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="regestrationBtn" value="Зарегистрироваться"></td>
				<td><input type="submit" name="backBtn" value="Назад"></td>
			</tr>
		</table>
	</form>
</body>
</html>
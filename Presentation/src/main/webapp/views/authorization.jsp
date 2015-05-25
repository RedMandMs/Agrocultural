<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Вход в систему</title>
	</head>
	<body>
		<form name="authorization" title="Вход в систему">
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
					<td><input type="submit" name="enterBtn" value="Войти"></td>
					<td><input type="submit" name="backBtn" value="Назад"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
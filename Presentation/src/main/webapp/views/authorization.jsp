<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Вход в систему</title>
	</head>
	<body>
		<sf:form method="PUT" modelAttribute="userOrganization">
			<H1>Вход в систему:</H1>
			<a>${message}</a>
			<table>
				<tr>
					<th><label for="user_login">Введите логин:</label></th>
					<td>
						<sf:input path="login" size="20" id="user_login"/>
					</td>
				</tr>
					
				<tr>
					<th><label for="user_password">Введите пароль:</label></th>
					<td>
						<sf:input path="password" size="20" id="user_password"/>
					</td>
				</tr>
				<tr>
					<td><input type="submit" name="authorizationBtn" value="Войти"></td>
				</tr>
			</table>
		</sf:form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Вход в систему</title>
	</head>
	<body>
		<c:url value="/j_spring_security_check" var="loginUrl" />
		<form name="loginForm" action="<c:url value='/j_spring_security_check' />" method='POST'>
			<H1>Вход в систему:</H1>
			<a>${message}</a>
			<table>
				<tr>
					<c:if test="${param.error != null}">
						<th>Неверный логин или пароль</th>
					</c:if>
					<c:if test="${param.logout != null}">
						<th>Вы вышли из системы</th>
					</c:if>
				</tr>
				<tr>
					<th><label for="user_login">Введите логин:</label></th>
					<td>
						<input type="text" name="j_username" id="user_login">
					</td>
				</tr>
					
				<tr>
					<th><label for="user_password">Введите пароль:</label></th>
					<td>
						<input type="password" name="j_password" id="user_password">
					</td>
				</tr>
				<tr>
					<td><input type="submit" name="authorizationBtn" value="Войти"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
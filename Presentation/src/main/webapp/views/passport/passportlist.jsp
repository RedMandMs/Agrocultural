<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Список паспортов</title>
	</head>
	<body>
	<H1>PASSPORTS LIST</H1>
	<c:set var="isSerchList" scope="request" value="${isSerchList}"/>
	<c:if test="${isSerchList}">
		<sf:form method="POST" modelAttribute="serchingPassport">
			<fieldset>
				<table>
					<tr>
						<td>Id паспорта:</td>
						<td>Id владельца-организации пасспорта:</td>
						<td>Регион расположения поля:</td>
						<td>Кадастровый номер паспорта:</td>
						<td>Площадь поля:</td>
						<td>Тип поля:</td>
						<td>Комментарий к пасспорту:</td>
					</tr>
					<tr>
						<td align="center"><sf:input path="id" size="10"/></td>
						<td align="center"><sf:input path="idOwner" size="10"/></td>
						<td align="center"><sf:input path="region" size="15"/></td>
						<td align="center"><sf:input path="cadastrNumber" size="10"/></td>
						<td align="center"><sf:input path="area" size="7"/></td>
						<td align="center"><sf:input path="type" size="10"/></td>
						<td align="center"><sf:input path="comment" size="20"/></td>
					</tr>
					<tr>
						<td colspan="7" align="center">
							<input type="submit" name="serchBtn" value="Найти подходящие паспорта">
						</td>
					</tr>
				</table>
			</fieldset>
		</sf:form>
	</c:if>
	<H2><a>${messageList}</a></H2>
	<table>
	  <c:forEach var="passport" items="${reviewingPassportsList}">
		  <tr>
		    <td>${passport.getId()}</td>
		    <td>${passport.getNameOwner()}</td>
		    <td>${passport.getRegion()}</td>
		    <td>${passport.getCadastrNumber()}</td>
		    <td>${passport.getArea()}</td>
		    <td>${passport.getType()}</td>
		    <td>${passport.getComment()}</td>
		    <td>
		    	<form action="http://localhost:8080/Presentation/passport/${passport.getId()}" method="get">
		    		<input type="submit" value="Посмотреть">
		    	</form>
		    </td>
		  </tr>
	  </c:forEach>
	</table>
	</body>
</html>
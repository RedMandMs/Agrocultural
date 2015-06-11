<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Мои паспорта</title>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<form name="goReviewCompanyForm" action="<c:url value='/organization/company/mycompany' />" method='GET'>
						<input type="submit" name="reviewMyCompanyBtn" value="Назад к информации о своей компании">
					</form>
				</td>
				<td>
					<form method="GET" action="/Presentation/passport/findlistpassports">
						<input type="submit" name="serchPassportsBtn" value="Поиск паспортов"/>
					</form>
				</td>
			</tr>
		</table>
		<br>
		<H1>Паспорта принадлежащие организации</H1>
		<H2>Список всех ваших паспортов:</H2>
		<table>
		  <c:forEach var="passport" items="${myPassportsList}">
			  <tr>
			    <td>${passport.getId()}</td>
			    <td>${passport.getNameOwner()}</td>
			    <td>${regions[0].getRegion(passport.getRegion()).getView()}</td>
			    <td>${passport.getCadastrNumber()}</td>
			    <td>${passport.getArea()}</td>
			    <td>${types[0].getTypeOf(passport.getType()).getView()}</td>
			    <td>${passport.getComment()}</td>
			    <td>
			    	<form action="http://localhost:8080/Presentation/passport/${passport.getId()}" method="get">
			    		<input type="submit" value="Посмотреть">
			    	</form>
			    </td>
			  </tr>
		  </c:forEach>
		</table>
		<form method="GET" action="/Presentation/passport/createPassport">
			<input type="submit" name="createPassportBtn" value="Создать новый пасспорт поля"/>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delivery</title>
</head>
<body>
	<h1 align="center">Delivery administration:</h1>
	<form:form action="/admin/delivery" method="post"
		modelAttribute="delivery">
		<form:hidden path="id" />
		<table border="1">
			<tr>
				<td colspan="2"><p align="center">
						<b>Delivery creator:</b>
					</p></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="a1"><b><form:errors
								path="error" /></b></a></td>
			</tr>
			<tr>
				<td align="center"><b>City select:</b></td>
				<td align="center"><b>Carrier select:</b></td>
			</tr>
			<tr>
				<td align="center"><a class="a1"><b><form:errors
								path="city" /></b></a></td>
				<td align="center"><a class="a1"><b><form:errors
								path="carrier" /></b></a></td>
			</tr>
			<tr>
				<td align="center"><form:select path="city">
						<option value="0">----- Cities -----</option>
						<c:forEach items="${cities}" var="city">
							<c:choose>
								<c:when test="${city.id eq delivery.city.id}">
									<option value="${city.id}" selected="selected">${city.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${city.id}">${city.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
				<td align="center"><form:select path="carrier">
						<option value="0">---- Carriers ---</option>
						<c:forEach items="${carriers}" var="carrier">
							<c:choose>
								<c:when test="${carrier.id eq delivery.carrier.id}">
									<option value="${carrier.id}" selected="selected">${carrier.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${carrier.id}">${carrier.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="a1"><b><form:errors
								path="numCerrDep" /></b></a></td>
			</tr>
			<tr>
				<td colspan="2" align="center">№<form:input path="numCerrDep"
						placeholder="carriar department:" /></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>
	
	<table width="75%" border="1">
		<tr>
			<td align="center" colspan="5">Delivery List:</td>

			<c:forEach items="${deliveries}" var="delivery">
				<tr>
					<td>${delivery.city.name}</td>
					<td>${delivery.carrier.name}</td>
					<td>${delivery.numCerrDep}</td>
					<th width="10%"><a class="a1"
						href="/admin/delivery/delete/${delivery.id}"><b>delete</b></a></th>
					<th width="10%"><a
						href="/admin/delivery/update/${delivery.id}"><b>update</b></a></th>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<form name="returnToAdmin" action="/admin">
		<p align="right">
			<b>Return to administrator menu:</b> <input type="submit"
				value="Return">
		</p>
	</form>
</body>
</html>
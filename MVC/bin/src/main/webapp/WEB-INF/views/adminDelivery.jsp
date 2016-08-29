<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Delivery administration:</h1>
	<form action="/admin/delivery" method="post">
		<table>
			<tr>
				<td colspan="2"><p align="center">
						<b>Delivery creator:</b>
					</p></td>
			</tr>
			<tr>
				<td align="center">
					<p>
						<b>City select:</b>
					</p> <select name="cityId">
						<c:forEach items="${cities}" var="city">
							<option value="${city.id}">${city.name}</option>
						</c:forEach>
				</select>
				</td>
				<td align="center">
					<p>
						<b>Carrier select:</b>
					</p> <select name="carrierId">
						<c:forEach items="${carriers}" var="carrier">
							<option value="${carrier.id}">${carrier.name}</option>
						</c:forEach>
				</select>

				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input name="numCerrDep"
					placeholder="number of carriar department"></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form>
	<table width="75%">
		<tr>
			<td align="center" colspan="4">Delivery List:</td>
			<c:if test="${size == 0}">
				<tr>
					<td><font color="red" size="20">List is empty</font></td>
				</tr>
			</c:if>
			<c:forEach items="${deliveries}" var="delivery">
				<tr>
					<td>${delivery.city.name}</td>
					<td>${delivery.carrier.name}</td>
					<td>${delivery.numCerrDep}</td>
					<th width="10%"><a class="a1"
						href="/admin/delivery/delete/${delivery.id}"><b>delete</b></a></th>
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
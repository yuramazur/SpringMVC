<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>City</title>
<style></style>
</head>
<body>
	<div class="div1">

		<!-- 		<h1 align="center">City administration:</h1> -->


		<!-- 		<form name="newCity" method="post" action="/admin/city"> -->

		<!-- 			<h3 align="left">Enter new City:</h3> -->

		<!-- 			<input name="name" size="30"> <input type="submit" -->
		<!-- 				value="Enter"> -->
		<!-- 		</form> -->

		<form:form action="/admin/city" method="post"
			modelAttribute="city">
			<form:hidden path="id" />
			<table>
				<tr>
					<td><a><b> Enter City:</b></a></td>
				</tr>
				<!-- 				<tr> -->
				<%-- 					<td><form:errors path="name" /></td> --%>
				<!-- 				</tr> -->
				<tr>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<th align="right"><input type="submit" value="save"></th>
				</tr>
			</table>
		</form:form>

		<table border="1">
			<tr>
				<th colspan="3">Cities List:</th>
			</tr>

			<c:forEach items="${cities}" var="city">
				<tr>
					<td><b>${city.name}</b></td>
					<th><a href="/admin/city/delete/${city.id}">delete</a></th>
					<th><a href="/admin/city/update/${city.id}">update</a></th>
				</tr>
			</c:forEach>
		</table>


		<form name="returnToAdmin" action="/admin">
			<p align="right">
				<b>Return to administrator menu:</b> <input type="submit"
					value="Return">
			</p>

		</form>

	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carriers</title>
<style></style>
</head>
<body>
	<div class="div1">

		<!-- 		<h1 align="center">Carrier administration:</h1> -->


		<!-- 		<form name="newCarrier" method="post" action="/admin/carrier"> -->

		<!-- 			<h3 align="left">Enter new Carrier:</h3> -->

		<!-- 			<input name="name" size="30"> <input type="submit" -->
		<!-- 				value="Enter"> -->
		<!-- 		</form> -->

		<form:form action="/admin/carrier" method="post"
			modelAttribute="carrier">
			<form:hidden path="id" />
			<table>
				<tr>
					<td align="center"><a><b> Enter Carrier:</b></a></td>
				</tr>
				<tr>
					<td><a class="a1"><b><form:errors path="name" /></b></a></td>
				</tr>
				<tr>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" value="save"></td>
				</tr>
			</table>
		</form:form>

		<table border="1">
			<tr>
				<td colspan="3" align="center"><b>Carriers List:</b></td>
			</tr>

			<c:forEach items="${carriers}" var="carrier">
				<tr>
					<td><b>${carrier.name}</b></td>
					<th><a class="a1" href="/admin/carrier/delete/${carrier.id}"><b>delete</b></a></th>
					<th><a href="/admin/carrier/update/${carrier.id}">update</a></th>
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
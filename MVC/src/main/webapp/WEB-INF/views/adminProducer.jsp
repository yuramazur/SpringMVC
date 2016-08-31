<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Producer</title>
<style></style>
</head>
<body>
	<div class="div1">

		<!-- 		<h1 align="center">Producer administration:</h1> -->


		<!-- 		<form name="newProducer" method="post" action="/admin/producer"> -->

		<!-- 			<h3 align="left">Enter new Producer:</h3> -->

		<!-- 			<input name="name" size="30"> <input type="submit" -->
		<!-- 				value="Enter"> -->
		<!-- 		</form> -->

		<form:form action="/admin/producer" method="post"
			modelAttribute="producer">
			<form:hidden path="id" />
			<table>
				<tr>
					<td><a><b> Enter Producer:</b></a></td>
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
				<th colspan="3">Producer List:</th>
			</tr>

			<c:forEach items="${producers}" var="producer">
				<tr>
					<td><b>${producer.name}</b></td>
					<th><a href="/admin/producer/delete/${producer.id}">delete</a></th>
					<th><a href="/admin/producer/update/${producer.id}">update</a></th>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ProductType</title>
<style></style>
</head>
<body>
	<div class="div1">

		<h1 align="center">Product Type administration:</h1>


		<form name="newProductType" method="post" action="/admin/producttype">

			<h3 align="left">Enter new Product Type:</h3>

			<input name="name" size="30"> <input type="submit"
				value="Enter">
		</form>


		<table border="1">
			<tr>
				<th colspan="2">Producer List:</th>
			</tr>
			<c:forEach items="${productType}" var="productType">
				<tr>
					<td>${productType.name}</td>
					<th><a href="/admin/producttype/delete/${productType.id}">delete</a>
					</th>
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
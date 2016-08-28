<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
<style>
.a1 {
	color: red;
}
</style>
</head>
<body>
	<h1 align="center">Product administration:</h1>
	<form method="post" action="/admin/product">
		<table>
			<tr>
				<td colspan="2" align="center">
					<p>
						<b>Product creator</b>
					</p>

				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						<b>Product type select:</b>
					</p> <select name="productTypeId">
						<c:forEach items="${productTypes}" var="productType">
							<option value="${productType.id}">${productType.name}</option>
						</c:forEach>
				</select>
				</td>
				<td align="center">
					<p>
						<b>Producer select:</b>
					</p> <select name="producerId">
						<c:forEach items="${producers}" var="producer">
							<option value="${producer.id}">${producer.name}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td align="center"><input name="name"
					placeholder="Product title:"></td>
				<td align="center"><input name="price"
					placeholder="Product price:"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form>

	<table width="75%">
		<tr>
			<th align="center" colspan="5">Product List:</th>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.productType.name}</td>
					<td>${product.producer.name}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<th width="10%"><a class="a1"
						href="/admin/product/delete/${product.id}"><b>delete</b></a></th>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
<style>
</style>
</head>
<body>
	<h1 align="center">Product administration:</h1>
	<form:form method="post" action="/admin/product"
		modelAttribute="product">
		<form:hidden path="id" />
		<table border="1">
			<tr>
				<td colspan="2" align="center">
					<p>
						<b>Product creator</b>
					</p>

				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="a1"><b><form:errors
								path="error" /></b></a></td>

			</tr>
			<tr>
				<td align="center"><b>Product type select:</b></td>
				<td align="center"><b>Producer select:</b></td>
			</tr>
			<tr>
				<td align="center"><a class="a1"><b><form:errors
								path="productType" /></b></a></td>
				<td align="center"><a class="a1"><b><form:errors
								path="producer" /></b></a></td>
			</tr>
			<tr>
				<td align="center"><form:select path="productType">
						<option value="0">---Product Types---</option>
						<c:forEach items="${productTypes}" var="productType">
							<c:choose>
								<c:when test="${productType.id eq product.productType.id}">
									<option value="${productType.id}" selected="selected">${productType.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${productType.id}">${productType.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			
				<td align="center"><form:select path="producer">
						<option value="0">-----Producers-----</option>
						<c:forEach items="${producers}" var="producer">
							<c:choose>
								<c:when test="${producer.id eq product.producer.id}">
									<option value="${producer.id}" selected="selected">${producer.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${producer.id}">${producer.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td align="center"><a class="a1"><b><form:errors path="name" /></b></a></td>
				<td align="center"><a class="a1"><b><form:errors path="price" /></b></a></td>
			</tr>
			<tr>

				<td align="center"><form:input path="name"
						placeholder="Product title:" /></td>

				<td align="center"><form:input path="price"
						placeholder="Product price:" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>

	<table border="1">
		<tr>
			<td align="center" colspan="6"><b>Product List:</b></td>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.productType.name}</td>
					<td>${product.producer.name}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<th width="10%"><a class="a1"
						href="/admin/product/delete/${product.id}"><b>delete</b></a></th>
					<th width="10%"><a href="/admin/product/update/${product.id}"><b>update</b></a></th>
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
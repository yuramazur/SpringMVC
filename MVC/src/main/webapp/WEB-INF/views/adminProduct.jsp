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
					</p> <form:select path="productType">
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
					</form:select>
				</td>
				<td align="center">
					<p>
						<b>Producer select:</b>
					</p> <form:select path="producer">
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
					</form:select>
				</td>
			</tr>
			<tr>
			
				<td align="center"><form:input path="name"  placeholder="Product title:"/></td>
				<td align="center"><form:input path="price" placeholder="Product price:"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>

	<table width="75%">
		<tr>
			<th align="center" colspan="6">Product List:</th>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.productType.name}</td>
					<td>${product.producer.name}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<th width="10%"><a class="a1"
						href="/admin/product/delete/${product.id}"><b>delete</b></a></th>
					<th width="10%"><a class="a1"
						href="/admin/product/update/${product.id}"><b>update</b></a></th>
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
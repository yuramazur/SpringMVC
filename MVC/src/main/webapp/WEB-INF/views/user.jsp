<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
</head>
<body>
	<h1>There will be a super cute page! With a lot of useful
		information, and other cool things =)</h1>
	<table border="1">
			<tr>
				<th colspan="5">Products List:</th>
			</tr>

			<c:forEach items="${products}" var="product">
				<tr>
					<td><b>${product.productType.name}</b></td>
					<td><b>${product.producer.name}</b></td>
					<td><b>${product.name}</b></td>
					<td><b>${product.price}</b></td>
					<th><a href="/user/product/${product.id}">order</a></th>
					
				</tr>
			</c:forEach>
		</table>


		<form name="returnToAdmin" action="/">
			<p align="right">
				<b>Return :</b> <input type="submit"
					value="Return">
			</p>

		</form>

</body>
</html>
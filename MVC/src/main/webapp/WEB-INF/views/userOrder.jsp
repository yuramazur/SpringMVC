<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<script>
		$(function() {
			$('select[name=city]').chosen({
				width : "190px;"
			});
			$('select[name=carrier]').chosen({
				width : "190px;"
			});
		});
	</script>
	<div class="row-fluid">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="col-md-11">
				<h4>Your wish list:</h4>
			</div>
			<div class="col-md-1">
				<ul class="list-group">
					<li class="list-group-item"><a href="/user/wishlist">Wish
							List:</a><span class="badge">${authUser.wishList.size()}</span></li>
				</ul>
			</div>

		</div>
		</nav>
	</div>
	<div class="row-fluid">
		<div class="col-md-4 col-xs-12">
			<div class="row-fluid">
				<h4>Delivery:</h4>
			</div>
			<form:form class="form-inline" action="/user/order/save"
				method="post" modelAttribute="deliveryForm">
				<form:hidden path="id" />
				<custom:hiddenInputs
					excludeParams="city, carrier, numberDepartment, id" />
				<div class="form-group">
					<div class="row-fluid">
						<b>City:</b>
					</div>
					<div class="row-fluid">
						<label for="error"><form:errors path="error" /></label>
						<form:select path="city" items="${cities}" itemLabel="name"
							itemValue="id">
						</form:select>
					</div>
					<div class="row-fluid">
						<b>Carrier:</b>
					</div>
					<div class="row-fluid">
						<form:select path="carrier" items="${carriers}" itemLabel="name"
							itemValue="id">
						</form:select>
					</div>
					<div class="row-fluid">
						<label for="numberDepartment"><form:errors
								path="numberDepartment" /></label>
						<form:input path="numberDepartment" placeholder="№ department"
							class="form-control" />
					</div>

					<div class="row-fluid">
						<button type="submit" class="btn btn-primary">Confirm</button>
					</div>
				</div>
			</form:form>


		</div>
		<div class="col-md-8 col-xs-12">
			<div class="row">

				<div class="col-md-3">
					<h4>Image</h4>
				</div>
				<div class="col-md-2">
					<h4>Product Type</h4>
				</div>
				<div class="col-md-2">
					<h4>Producer</h4>
				</div>
				<div class="col-md-2">
					<h4>Title</h4>
				</div>
				<div class="col-md-1">
					<h4>Price</h4>
				</div>
				<div class="col-md-2">
					<h4>Delete</h4>
				</div>
			</div>
			<c:forEach items="${products}" var="product">
				<div class="row">
					<div class="col-md-3">
						<img class="img-thumbnail" width="100"
							src="/images/product/${product.id}${product.path}?version=${product.version}" />
					</div>
					<div class="col-md-2">${product.productType.name}</div>
					<div class="col-md-2">${product.producer.name}</div>
					<div class="col-md-2">${product.name}</div>
					<div class="col-md-1">${product.price}</div>
				</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>
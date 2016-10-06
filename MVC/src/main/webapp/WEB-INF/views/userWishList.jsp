<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<script>
	$(function() {
		$(".chosen-select").chosen({
			width : "190px;"
		});

	});
</script>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="col-md-9">
				<h4>Your wish list:</h4>
			</div>
			<div class="col-md-1">
				<ul class="list-group">
					<li class="list-group-item"><a href="/user">Exit:</a><span
						class="badge">${authUser.wishList.size()}</span></li>
				</ul>
			</div>
			<div class="col-md-2 col-xs-12">
				<div class="col-md-4">
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							Sort <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<custom:sort innerHtml="Price asc" paramValue="price" />
							<custom:sort innerHtml="Price desc" paramValue="price,desc" />
							<custom:sort innerHtml="Product Type asc"
								paramValue="productType.name" />
							<custom:sort innerHtml="Product Type desc"
								paramValue="productType.name,desc" />
							<custom:sort innerHtml="Producer name asc"
								paramValue="producer.name" />
							<custom:sort innerHtml="Producer name desc"
								paramValue="producer.name,desc" />
							<custom:sort innerHtml="Title asc" paramValue="name" />
							<custom:sort innerHtml="Title desc" paramValue="name,desc" />
						</ul>
					</div>
				</div>
				
			</div>
		</div>
	</nav>
</div>

<div class="col-md-8 col-xs-12">
	<div class="row">
		<div class="col-md-1">
			<h4>Select</h4>
		</div>
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
		<div class="col-md-1">
			<h4>Delete</h4>
		</div>

	</div>

	<form:form action="/user/order" class="form-inline" method="post"
		modelAttribute="addOrderForm">


		<c:forEach items="${products}" var="product">
			<div class="row">
				<div class="col-md-1">

					<form:checkbox path="productIds" value="${product.id}" />

				</div>
				<div class="col-md-3">
					<img class="img-thumbnail" width="100"
						src="/images/product/${product.id}${product.path}?version=${product.version}" />
				</div>
				<div class="col-md-2">${product.productType.name}</div>
				<div class="col-md-2">${product.producer.name}</div>
				<div class="col-md-2">${product.name}</div>
				<div class="col-md-1">${product.price}</div>
				<div class="col-md-1">
					<a href="/user/wishlist/delete/${product.id}<custom:allParams/>">Don't
						want it!</a>
				</div>

			</div>
		</c:forEach>
		<button type="submit" class="btn btn-primary">Buy selected</button>
	</form:form>
	<!-- 		<div class="col-md-12 text-center"> -->
	<%-- 			<custom:pageable page="${page}" cell="<li></li>" --%>
	<%-- 				container="<ul class='pagination'></ul>" /> --%>
	<!-- 		</div> -->
</div>
</div>
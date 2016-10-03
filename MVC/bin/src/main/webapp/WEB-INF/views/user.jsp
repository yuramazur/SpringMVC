<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/ingredientAmount.css">
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
				<h4>Hello! Nice to see you =)</h4>
			</div>
			<div class="col-md-1">
				<security:authorize
					access="isAuthenticated() and hasRole('ROLE_USER')">
					<ul class="list-group">
						<li class="list-group-item">Wish List: <span class="badge">${authUser.wishList.size()}</span></li>
					</ul>
				</security:authorize>
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
				<div class="col-md-8">
					<custom:size posibleSizes="1,2,5,10" size="${page.size}"
						title="Page Size" />
				</div>
			</div>
		</div>
	</nav>
</div>

<div class="row-fluid">
	<div class="col-md-2 col-xs-12" style="padding-top: 90px;">
		<form:form action="/user" class="form-inline" method="get"
			modelAttribute="filter">
			<custom:hiddenInputs
				excludeParams="minPrice,maxPrice,nameSearch,productTypeIds, producerIds,_productTypeIds,_producerIds " />
			<div class="form-group">
				<form:input path="minPrice" placeholder=" min Price:"
					class="form-control" />
			</div>
			<div class="form-group">
				<form:input path="maxPrice" placeholder=" max Price:"
					class="form-control" />
			</div>
			<div class="form-group">
				<form:input path="nameSearch" placeholder="product title:"
					class="form-control" />
			</div>

			<div class="form-group">
				<h4>Product Types:</h4>
			</div>
			<div class="form-group">
				<%-- 				<form:checkboxes items="${productTypes}" path="productTypeIds" --%>
				<%-- 					itemLabel="name" itemValue="id" /> --%>
				<form:select class="chosen-select" data-placeholder="Product Type"
					path="productTypeIds" items="${productTypes}" itemLabel="name"
					itemValue="id" style="width:180px;">

				</form:select>
			</div>
			<div class="form-group">
				<h4>Producers:</h4>
			</div>
			<div class="form-group">
				<%-- 				<form:checkboxes items="${producers}" path="producerIds" --%>
				<%-- 					itemLabel="name" itemValue="id" /> --%>
				<form:select class="chosen-select" data-placeholder="Producers"
					path="producerIds" items="${producers}" itemLabel="name"
					itemValue="id" style="width:180px;">

				</form:select>
			</div>
			<div class="row" style="padding: 5px;">
				<div class="col-md-8"></div>
				<div class="col-md-4 col-xs-12 form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
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
			<div class="col-md-1">
				<h4>Buy</h4>
			</div>
			<security:authorize
				access="isAuthenticated() and hasRole('ROLE_USER')">
				<div class="col-md-1">
					<h4>Wish List</h4>
				</div>
			</security:authorize>
		</div>
		<c:forEach items="${page.content}" var="product">
			<div class="row">
				<div class="col-md-3">
					<img class="img-thumbnail" width="100"
						src="/images/product/${product.id}${product.path}?version=${product.version}" />
				</div>
				<div class="col-md-2">${product.productType.name}</div>
				<div class="col-md-2">${product.producer.name}</div>
				<div class="col-md-2">${product.name}</div>
				<div class="col-md-1">${product.price}</div>
				<div class="col-md-1">
					<a href="/user/order/${product.id}<custom:allParams/>">Buy it!</a>
				</div>

				<div class="col-md-1">
					<security:authorize access="isAuthenticated()">
						<a href="/user/wishlist/add/${product.id}<custom:allParams/>">Want
							it!</a>
					</security:authorize>
				</div>

			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
</div>
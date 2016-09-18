<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li><a href="/admin/name"><b>Name</b></a></li>
					<li><a href="/admin/carrier"><b>Carrier</b></a></li>
					<li><a href="/admin/city"><b>City</b></a></li>
					<li class="active"><a href="/admin/producer"><b>Producer</b></a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/producttype"><b>Product Type</b></a></li>
					<li><a href="/admin/product"><b>Product</b></a></li>
					<li><a href="/admin/delivery"><b>Delivery</b></a></li>
					<li><a href="/admin/order"><b>Order</b></a></li>
					<li><a href="/admin/client"><b>Clients</b></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			<form:form action="/admin/producer" class="form-inline" method="get"
				modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search" />
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="col-md-12 col-xs-12">
			<form:form action="/admin/producer" method="post" class="form-inline"
				modelAttribute="producer">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="name, id" />
				<div class="form-group">
					<form:input id="producer" path="name" placeholder="producer name"
						class="form-control" />
					<label for="name"><form:errors path="name" /></label>
					<button type="submit" class="btn btn-primary">Create Producer</button>
				</div>
			</form:form>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Producer name</h4>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Delete</h4>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Update</h4>
		</div>
		<c:forEach items="${page.content}" var="producer">
			<div class="col-md-4 col-xs-4">${producer.name}</div>
			<div class="col-md-4 col-xs-4">
				<a href="/admin/producer/delete/${producer.id}<custom:allParams/>">delete</a>
			</div>
			<div class="col-md-4 col-xs-4">
				<a href="/admin/producer/update/${producer.id}<custom:allParams/>">update</a>
			</div>
		</c:forEach>
		<div class="col-md-12 col-xs-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="col-md-6 col-xs-6 text-center">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<custom:sort innerHtml="Name asc" paramValue="name" />
					<custom:sort innerHtml="Name desc" paramValue="name,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6 col-xs-6 text-center">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}"
				title="Page size" />
		</div>
	</div>
</div>
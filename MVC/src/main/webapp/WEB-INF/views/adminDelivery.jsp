<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/ingredientAmount.css">
<script>
	$(function() {
		$('select[name=system]').chosen();
		$('select[name=ingredient]').chosen();
	});
</script>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li><a href="/admin/name"><b>Name</b></a></li>
					<li><a href="/admin/carrier"><b>Carrier</b></a></li>
					<li><a href="/admin/city"><b>City</b></a></li>
					<li><a href="/admin/producer"><b>Producer</b></a></li>
					<li><a href="/admin/producttype"><b>Product Type</b></a></li>
					<li><a href="/admin/product"><b>Product</b></a></li>
					<li class="active"><a href="/admin/delivery"><b>Delivery</b></a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/order"><b>Order</b></a></li>
					<li><a href="/admin/client"><b>Clients</b></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
		<form:form action="/admin/delivery" class="form-inline" method="get"
			modelAttribute="filter">
			<custom:hiddenInputs
				excludeParams="numCerrDepInt, cityIds, carrierIds,_cityIds,_carrierIds " />
			<div class="form-group">
				<form:input path="numCerrDepInt" placeholder="№ carrier department:"
					class="form-control" />

			</div>
			<div class="form-group">
				<h4>Cities</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${cities}" path="cityIds"
					itemLabel="name" itemValue="id" />
			</div>
			<div class="form-group">
				<h4>Carriers</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${carriers}" path="carrierIds"
					itemLabel="name" itemValue="id" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<form:form class="form-inline" action="/admin/delivery"
			method="post" modelAttribute="delivery">
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="city, carrier, numCerrDep, id" />
			<div class="form-group">
				<label for="amount"><form:errors path="amount" /></label>
				<form:input path="amount" class="form-control" />
				<form:select path="system" items="${measuringSystems}"
					itemLabel="name" itemValue="id">
				</form:select>
				<form:select path="ingredient" items="${ingredients}"
					itemLabel="name" itemValue="id">
				</form:select>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-2">Amount</div>
			<div class="col-md-2">Measuring system</div>
			<div class="col-md-4">Ingredient</div>
			<div class="col-md-2">
				<h4>Delete</h4>
			</div>
			<div class="col-md-2">
				<h4>Update</h4>
			</div>
		</div>
		<c:forEach items="${page.content}" var="ingredientAmount">
			<div class="row">
				<div class="col-md-2">${ingredientAmount.amount}</div>
				<div class="col-md-2">${ingredientAmount.measuringSystem.name}</div>
				<div class="col-md-4">${ingredientAmount.ingredient.name}</div>
				<div class="col-md-2">
					<a
						href="/admin/ingredientAmount/delete/${ingredientAmount.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-2">
					<a
						href="/admin/ingredientAmount/update/${ingredientAmount.id}<custom:allParams/>">update</a>
				</div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="col-md-6">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<custom:sort innerHtml="Amount asc" paramValue="amount" />
					<custom:sort innerHtml="Amount desc" paramValue="amount,desc" />
					<custom:sort innerHtml="Ingredient name asc"
						paramValue="ingredient.name" />
					<custom:sort innerHtml="Ingredient name desc"
						paramValue="ingredient.name,desc" />
					<custom:sort innerHtml="Ms name asc"
						paramValue="measuringSystem.name" />
					<custom:sort innerHtml="Ms name desc"
						paramValue="measuringSystem.name,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}"
				title="Розмір сторінки" />
		</div>
	</div>
</div>
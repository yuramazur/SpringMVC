<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				excludeParams="numCerrDep, cityIds, carrierIds,_cityIds,_carrierIds " />
			<div class="form-group">
				<form:input path="numCerrDep" placeholder="№ carrier department:"
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
			method="post" modelAttribute="deliveryForm">
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="city, carrier, numberDepartment, id" />
			<div class="form-group">
			<label for="error"><form:errors path="error" /></label>
				<form:select path="city" items="${cities}"
					itemLabel="name" itemValue="id">
				</form:select>
				<form:select path="carrier" items="${carriers}"
					itemLabel="name" itemValue="id">
				</form:select>
				<label for="numberDepartment"><form:errors path="numberDepartment" /></label>
				<form:input path="numberDepartment" placeholder="№ department" class="form-control" />
				
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-2">City</div>
			<div class="col-md-4">Carrier</div>
			<div class="col-md-2">Department №</div>
			<div class="col-md-2">
				<h4>Delete</h4>
			</div>
			<div class="col-md-2">
				<h4>Update</h4>
			</div>
		</div>
		<c:forEach items="${page.content}" var="delivery">
			<div class="row">
				<div class="col-md-2">${delivery.city.name}</div>
				<div class="col-md-4">${delivery.carrier.name}</div>
				<div class="col-md-2">${delivery.numCerrDep}</div>
				<div class="col-md-2">
					<a
						href="/admin/delivery/delete/${delivery.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-2">
					<a
						href="/admin/delivery/update/${delivery.id}<custom:allParams/>">update</a>
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
					<custom:sort innerHtml="Department № asc" paramValue="numCerrDep" />
					<custom:sort innerHtml="Amount desc" paramValue="numCerrDep,desc" />
					<custom:sort innerHtml="City name asc"
						paramValue="city.name" />
					<custom:sort innerHtml="City name desc"
						paramValue="city.name,desc" />
					<custom:sort innerHtml="Carrier name asc"
						paramValue="carrier.name" />
					<custom:sort innerHtml="Carrier name desc"
						paramValue="carrier.name,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}"
				title="Розмір сторінки" />
		</div>
	</div>
</div>
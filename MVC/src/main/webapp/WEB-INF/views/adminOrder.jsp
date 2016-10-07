<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {
		$('select[name=productType]').chosen({
			width : "190px;"
		});
		$('select[name=producer]').chosen({
			width : "190px;"
		});
		$(".chosen-select").chosen({
			width : "190px;"
		});
	});
</script>
<div class="col-xs-10">
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
					<li><a href="/admin/delivery"><b>Delivery</b></a></li>
					<li class="active"><a href="/admin/order"><b>Order</b></a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/client"><b>Clients</b></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="col-xs-2">
	<div class="col-md-6 col-xs-6 text-center">
		<!-- 			<div class="dropdown"> -->
		<!-- 				<button class="btn btn-primary dropdown-toggle" type="button" -->
		<!-- 					data-toggle="dropdown"> -->
		<!-- 					Sort <span class="caret"></span> -->
		<!-- 				</button> -->
		<!-- 				<ul class="dropdown-menu"> -->
		<%-- 					<custom:sort innerHtml="Name asc" paramValue="names" /> --%>
		<%-- 					<custom:sort innerHtml="Name desc" paramValue="names,desc" /> --%>
		<!-- 				</ul> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<div class="col-md-6 col-xs-6 text-center">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}"
				title="Page size" />
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="row-fluid">
		<div class="col-md-2">
			<h4>Date:</h4>
		</div>
		<div class="col-md-5">
			<h4>Client:</h4>
		</div>
		<div class="col-md-5">
			<h4>Delivery:</h4>
		</div>

	</div>
	
	<c:forEach items="${page.content}" var="order">
		<div class="row">
			<div class="col-md-2">${order.date}</div>
			<div class="col-md-5">
				<ul class="list-group">
					<li class="list-group-item">${order.client.name.names}</li>
					<li class="list-group-item">${order.client.lastName}</li>
					<li class="list-group-item">${order.client.phone}</li>
			</ul> 
			</div>
			<div class="col-md-5">
				<ul class="list-group">
					<li class="list-group-item">${order.delivery.city.name}</li>
					<li class="list-group-item">${order.delivery.carrier.name}</li>
					<li class="list-group-item">${order.delivery.numCerrDep}</li>
				</ul>
			</div>
		
<%-- 		<c:forEach items="${order.getProducts()}" var="product"> --%>
<!-- 			<div class="row-fluid"> -->
<!-- 				<div class="col-xm-1"> -->
<!-- 					<b>Product:</b> -->
<!-- 				</div> -->
<%-- 				<div class="col-xm-2">${product.productType.name}</div> --%>
<%-- 				<div class="col-xm-3">${product.producer.name}</div> --%>
<%-- 				<div class="col-xm-3">${product.name}</div> --%>
<%-- 				<div class="col-xm-3">${product.price}</div> --%>
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
</div>
	</c:forEach>
	<div class="row-fluid">
	<div class="col-md-12 col-xs-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
</div>
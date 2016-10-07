<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {
		$('select[name=productType]').chosen({width:"190px;"});
		$('select[name=producer]').chosen({width:"190px;"});
		$(".chosen-select").chosen({width:"190px;"});
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
					<li class="active"><a href="/admin/product"><b>Product</b></a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/delivery"><b>Delivery</b></a></li>
					<li><a href="/admin/order"><b>Order</b></a></li>
					<li><a href="/admin/client"><b>Clients</b></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<div class="col-md-2 col-xs-12" style="padding-top:90px;">
		<form:form action="/admin/product" class="form-inline" method="get"
			modelAttribute="filter">
			<custom:hiddenInputs
				excludeParams="minPrice,maxPrice,name,productTypeIds, producerIds,_productTypeIds,_producerIds " />
			<div class="form-group">
				<form:input path="minPrice" placeholder=" min Price:"
					class="form-control" />
			</div>
			<div class="form-group">
				<form:input path="maxPrice" placeholder=" max Price:"
					class="form-control" />
			</div>
			<div class="form-group">
				<form:input path="name" placeholder="product title:"
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
		<form:form class="form-inline" action="/admin/product" method="post"
			modelAttribute="productForm" enctype="multipart/form-data">
			<form:errors path="*" />
			<form:hidden path="id" />
			<form:hidden path="path" />
			<form:hidden path="version" />
			<custom:hiddenInputs
				excludeParams="productType, producer, title, price, id, path, version" />
			<div class="form-group" style="width:100%;">
				<label for="error"><form:errors path="error" /></label>
				<form:select path="productType" items="${productTypes}"
					itemLabel="name" itemValue="id" data-placeholder="Product Type">

				</form:select>
				<form:select path="producer" items="${producers}" itemLabel="name"
					itemValue="id" data-placeholder="Producer">
				</form:select>
				<label for="title"><form:errors path="title" /></label>
				<form:input path="title" placeholder="title:" class="form-control" />
				<label for="price"><form:errors path="price" /></label>
				<form:input path="price" placeholder="price:" class="form-control" />
				<label class="btn btn-default btn-file"> Browse <input
					type="file" name="file" style="display: none;">
				</label>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-3"><h4>Image</h4></div>
			<div class="col-md-2"><h4>Product Type</h4></div>
			<div class="col-md-2"><h4>Producer</h4></div>
			<div class="col-md-2"><h4>Title</h4></div>
			<div class="col-md-1"><h4>Price</h4></div>
			<div class="col-md-1">
				<h4>Delete</h4>
			</div>
			<div class="col-md-1">
				<h4>Update</h4>
			</div>
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
					<a href="/admin/product/delete/${product.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-1">
					<a href="/admin/product/update/${product.id}<custom:allParams/>">update</a>
				</div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
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
				title="Розмір сторінки" />
		</div>
	</div>
</div>
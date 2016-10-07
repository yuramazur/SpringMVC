<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row-fluid">
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
						<li><a href="/admin/order"><b>Order</b></a></li>
						<li class="active"><a href="/admin/client"><b>Clients</b></a><span
							class="sr-only">(current)</span></li>
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
		<div class="col-md-2 col-xs-12">
			<div class="col-md-12 col-xs-12">
				<form:form action="/admin/client" class="form-inline" method="get"
					modelAttribute="filter">
					<custom:hiddenInputs
						excludeParams="loginSearch,mailSearch,nameSearch,lastNameSearch,phoneSearch" />
					<div class="row-fluid">
						<div class="form-group">
							<form:input path="loginSearch" placeholder="login search:"
								class="form-control" />
						</div>
						
					</div>
					<div class="row-fluid">
						<div class="form-group">
							<form:input path="mailSearch" placeholder="E-mail Search:"
								class="form-control" />
						</div>
						
					</div>
					<div class="row-fluid"> 
						<div class="form-group">
 							<form:input path="nameSearch" placeholder="Name Search:"
 								class="form-control" />
 						</div>
						
					</div>
					<div class="row-fluid">
						<div class="form-group">
							<form:input path="lastNameSearch" placeholder="last Name Search:"
								class="form-control" />
						</div>
						
					</div>
					<div class="row-fluid"> 
					<div class="form-group"> 
						<form:input path="phoneSearch" placeholder="Phone Search:"
							class="form-control" />
						</div>
						
					</div>
					<div class="row-fluid">
						<div class="col-xs-8"></div>
						<div class="col-xs-4">
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Ok</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="col-md-8 col-xs-12">

			<div class="col-md-4 col-xs-2">
				<h4>User</h4>
			</div>
			<div class="col-md-4 col-xs-4">
				<h4>Client</h4>
			</div>
			<div class="col-md-4 col-xs-6">
				<h4>Send E-Mail</h4>
			</div>
			<c:forEach items="${page.content}" var="user">
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<ul class="list-group">
							<li class="list-group-item">${user.login}</li>
							<li class="list-group-item">${user.mail}</li>
						</ul>

					</div>
					<div class="col-md-4 col-xs-4">
						<ul class="list-group">
							<li class="list-group-item">${user.client.name.names}</li>
							<li class="list-group-item">${user.client.lastName}</li>
							<li class="list-group-item">${user.client.phone}</li>
						</ul>
					</div>
					<div class="col-md-4 col-xs-4">
					<form:form action="/admin/client/send/${user.id}" class="form-inline" method="post"
					modelAttribute="mailSender">
						<ul class="list-group">
						<li class="list-group-item"><form:input path="content" placeholder="title:"/></li>
						<li class="list-group-item"><form:textarea  path="mailBody" placeholder="text masege:"/><button type="submit" class="btn btn-primary">Send</button></li>
						</ul>
							</form:form>
					</div>
				</div>
			</c:forEach>


		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>"
					container="<ul class='pagination'></ul>" />
			</div>
		</div>
</div>
	</div>
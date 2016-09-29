<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
						<li><a href="/admin/delivery"><b>Delivery</b></a></li>
						<li><a href="/admin/order"><b>Order</b></a></li>
						<li><a href="/admin/client"><b>Clients</b></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<div class="row">
		<form:form action="/admin" method="post" modelAttribute="mailSender">
		<ul class="nav navbar-nav">
		<li><form:input path="email" placeholder="E-MAIL:"/></li>
		<li><form:input path="content" placeholder="title:"/></li>
		<li><form:textarea path="mailBody" placeholder="text masege:"/></li>
		<Li><button type="submit" class="btn btn-primary">Ok</button></li>
		</ul>
		</form:form>

	</div>
</body>
</html>
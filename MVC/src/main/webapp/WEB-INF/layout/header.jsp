<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- 			<a class="navbar-brand" href="/"><img class="img-thumbnail" -->
			<!-- 				width="80" src="/resources/image/maxresdefault.jpg?version=1" /></a> -->
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<form:form class="navbar-form navbar-right">
				<button type="submit" class="btn btn-default">${authUser.login}</button>
			</form:form>
		</div>
		<security:authorize access="isAuthenticated()">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form:form action="/logout" method="post"
					class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default">Logout</button>
				</form:form>
			</div>
		</security:authorize>
		<security:authorize access="!isAuthenticated()">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form:form action="/login" method="get"
					class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default">login</button>
				</form:form>
			</div>
		</security:authorize>
	</div>
</nav>
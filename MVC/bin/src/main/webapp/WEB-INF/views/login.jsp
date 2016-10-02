<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<c:if test="${param.fail}">
			<div class="col-md-12 col-xs-12">
				<p style="color: red;">Fail</p>
			</div>
		</c:if>
		<form:form action="/login" class="form-inline" method="post">
			<div class="form-group">
				<input name="username" placeholder="Login" class="form-control" />
				<input name="password" type="password" placeholder="********"
					class="form-control" />
				<button type="submit" class="btn btn-primary">Login</button>

			</div>
		</form:form>
		<form action="/registration">
			<button type="submit" class="btn btn-primary">Registration</button>
		</form>

	</div>
</div>
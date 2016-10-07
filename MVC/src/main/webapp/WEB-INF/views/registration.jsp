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

		<form:form action="/registration" class="form-group" method="post"
			modelAttribute="userForm">
			<form:errors path="*" />
			<div class="form-group">
				<div class="row">
					<div class="col-md-6">
						<ul class="list-group">
							<li class="list-group-item"><input name="login"
								placeholder="Login" class="form-control" /></li>
							<li class="list-group-item"><input name="mail"
								placeholder="E-mail" class="form-control" /></li>
							<li class="list-group-item"><input name="password"
								type="password" placeholder="enter password:"
								class="form-control" /></li>
							<li class="list-group-item"><input name="passwordConfirm"
								type="password" placeholder="confirm password:"
								class="form-control" /></li>
						</ul>
					</div>
					<div class="col-md-6">
						<ul class="list-group">
							<li class="list-group-item"><form:errors path="login" /></li>
							<li class="list-group-item"><form:errors path="mail" /></li>
							<li class="list-group-item"><form:errors path="password" /></li>
							<li class="list-group-item"><form:errors
									path="passwordConfirm" /></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<ul class="list-group">
							<li class="list-group-item"><input name="name"
								placeholder="Name:" class="form-control" /></li>
							<li class="list-group-item"><input name="lastName"
								placeholder="Last Name:" class="form-control" /></li>
							<li class="list-group-item"><input name="phone"
								 placeholder="phone:" class="form-control" /></li>
						</ul>
					</div>
					<div class="col-md-6">
						<ul class="list-group">
							<li class="list-group-item"><form:errors path="login" /></li>
							<li class="list-group-item"><form:errors path="mail" /></li>
							<li class="list-group-item"><form:errors path="password" /></li>
							<li class="list-group-item"><form:errors
									path="passwordConfirm" /></li>
						</ul>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Registration</button>


			</div>
		</form:form>
	</div>
</div>

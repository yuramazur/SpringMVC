<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>City</title>
<style></style>
</head>
<body>
	<div class="div1">

		<h1 align="center">City administration:</h1>
		<form:form action="/admin/city" method="post" modelAttribute="city">
			<form:hidden path="id" />
			<table>
				<tr>
					<td align="center"><a><b> Enter City:</b></a></td>
				</tr>
				<tr>
					<td align="center"><a class="a1"><b><form:errors
									path="name" /></b></a></td>
				</tr>
				<tr>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" value="save"></td>
				</tr>
			</table>
		</form:form>

		<table border="1">
			<tr>
				<td colspan="3" align="center"><b>Cities List:</b></td>
			</tr>

			<c:forEach items="${cities.content}" var="city">
				<tr>
					<td><b>${city.name}</b></td>
					<th><a class="a1" href="/admin/city/delete/${city.id}?page=${cities.number+1}&size=${cities.size}&sort=${param['sort']}">delete</a></th>
					<th><a href="/admin/city/update/${city.id}?page=${cities.number+1}&size=${cities.size}&sort=${param['sort']}">update</a></th>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${cities.totalPages > 1}">
			<table border="1">
				<tr>

					<td><c:if test="${cities.number ne 0}">
							<b><a
								href="/admin/city?page=${cities.number}&size=${cities.size}&sort=${param['sort']}">previous</a></b>
						</c:if> <c:if test="${cities.number eq 0}">previous</c:if></td>
					<c:choose>
						<c:when test="${cities.totalPages eq 1}">
							<td><b>${cities.number+1}</b></td>
						</c:when>
						<c:when test="${cities.totalPages eq 2}">
							<c:choose>
								<c:when test="${cities.number eq 0}">
									<td><b>${cities.number+1}</b>&nbsp;<a
										href="/admin/city?page=${cities.number+2}&size=${cities.size}&sort=${param['sort']}">${cities.number+2}</a></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="/admin/city?page=${cities.number}&size=${cities.size}&sort=${param['sort']}">${cities.number}</a>&nbsp;
										<b>${cities.number+1}</b></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${cities.totalPages >= 3}">
							<c:choose>
								<c:when test="${cities.number eq 0}">
									<td><b>${cities.number+1}</b>&nbsp; <a
										href="/admin/city?page=${cities.number+2}&size=${cities.size}&sort=${param['sort']}">${cities.number+2}</a>&nbsp;
										<a
										href="/admin/city?page=${cities.number+3}&size=${cities.size}&sort=${param['sort']}">${cities.number+3}</a>
									</td>
								</c:when>
								<c:when test="${cities.number eq cities.totalPages-1}">
									<td><a
										href="/admin/city?page=${cities.number-1}&size=${cities.size}&sort=${param['sort']}">${cities.number-1}</a>&nbsp;
										<a
										href="/admin/city?page=${cities.number}&size=${cities.size}&sort=${param['sort']}">${cities.number}</a>&nbsp;
										<b>${cities.number+1}</b></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="/admin/city?page=${cities.number}&size=${cities.size}&sort=${param['sort']}">${cities.number}</a>&nbsp;
										<b>${cities.number+1}</b>&nbsp; <a
										href="/admin/city?page=${cities.number+2}&size=${cities.size}&sort=${param['sort']}">${cities.number+2}</a>
									</td>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>

					<td><c:if test="${cities.number ne cities.totalPages-1}">
							<b><a
								href="/admin/city?page=${cities.number+2}&size=${cities.size}&sort=${param['sort']}">&nbsp;&nbsp;&nbsp;next&nbsp;&nbsp;&nbsp;</a></b>
						</c:if> <c:if test="${cities.number eq cities.totalPages-1}">&nbsp;&nbsp;&nbsp;next&nbsp;&nbsp;&nbsp;</c:if></td>
				</tr>
			</table>
		</c:if>
		<table>
			<tr>
				<td><a
					href="/admin/city?page=1&size=1&sort=${param['sort']}">1</a></td>
				<td><a
					href="/admin/city?page=1&size=5&sort=${param['sort']}">5</a></td>
				<td><a
					href="/admin/city?page=1&size=10&sort=${param['sort']}">10</a></td>
				<td><a
					href="/admin/city?page=1&size=20&sort=${param['sort']}">20</a></td>
			</tr>

		</table>
		<table>
			<tr>
				<c:choose>
					<c:when test="${param['sort'] eq ''}">
						<td><td><a href="?page=1&size=${cities.size}&sort=name">Name
								asc</a></td>
				<td><a href="?page=1&size=${cities.size}&sort=name,desc">Name
								desc</a></td>
  
				</c:when>
					<c:when test="${param['sort'] eq 'name,desc'}">
						<td><a href="?page=1&size=${cities.size}&sort=name">Name
								asc</a></td>
						<td><b>Name desc</b></td>
					</c:when>
					<c:otherwise>
						<td><b>Name asc</b></td>
						<td><a href="?page=1&size=${cities.size}&sort=name,desc">Name
								desc</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>

		<form name="returnToAdmin" action="/admin">
								<p align="right">
				<b>Return to administrator menu:</b> <input type="submit"
										value="Return">
			</p>

		</form>

	
						</div>
</body>
</html>
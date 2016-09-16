<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carriers</title>
<style>
</style>
</head>
<body>
	<div class="div1">

		<h1 align="center">Name administration:</h1>

		<form:form action="/admin/name" method="post" modelAttribute="name">
			<form:hidden path="id" />
			<table>
				<tr>
					<td align="center"><a><b> Enter Name:</b></a></td>
				</tr>
				<tr>
					<td><a class="a1"><b><form:errors path="names" /></b></a></td>
				</tr>
				<tr>
					<td><form:input path="names" /></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" value="save"></td>
				</tr>
			</table>
		</form:form>
		<table border="1" align="right">
			<tr>
				<form:form action="/admin/name" method="get" modelAttribute="filter">
					<td><form:input path="search" placeholder="search" />
						<button type="submit">Ok</button></td>
				</form:form>
			</tr>
		</table>
		<table border="1">
			<tr>
				<th colspan="3">Names List:</th>
			</tr>

			<c:forEach items="${names.content}" var="name">
				<tr>
					<td><b>${name.names}</b></td>
					<th><a class="a1"
						href="/admin/name/delete/${name.id}?page=${names.number+1}&size=${names.size}&sort=${param['sort']}">delete</a></th>
					<th><a
						href="/admin/name/update/${name.id}?page=${names.number+1}&size=${names.size}&sort=${param['sort']}">update</a></th>
				</tr>

			</c:forEach>
		</table>
		<c:if test="${names.totalPages > 1}">
			<table border="1">
				<tr>

					<td><c:if test="${names.number ne 0}">
							<b><a
								href="/admin/name?page=${names.number}&size=${names.size}&sort=${param['sort']}">previous</a></b>
						</c:if> <c:if test="${names.number eq 0}">previous</c:if></td>
					<c:choose>
						<c:when test="${names.totalPages eq 1}">
							<td><b>${names.number+1}</b></td>
						</c:when>
						<c:when test="${names.totalPages eq 2}">
							<c:choose>
								<c:when test="${names.number eq 0}">
									<td><b>${names.number+1}</b>&nbsp;<a
										href="/admin/name?page=${names.number+2}&size=${names.size}&sort=${param['sort']}">${names.number+2}</a></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="/admin/name?page=${names.number}&size=${names.size}&sort=${param['sort']}">${names.number}</a>&nbsp;
										<b>${names.number+1}</b></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${names.totalPages >= 3}">
							<c:choose>
								<c:when test="${names.number eq 0}">
									<td><b>${names.number+1}</b>&nbsp; <a
										href="/admin/name?page=${names.number+2}&size=${names.size}&sort=${param['sort']}">${names.number+2}</a>&nbsp;
										<a
										href="/admin/name?page=${names.number+3}&size=${names.size}&sort=${param['sort']}">${names.number+3}</a>
									</td>
								</c:when>
								<c:when test="${names.number eq names.totalPages-1}">
									<td><a
										href="/admin/name?page=${names.number-1}&size=${names.size}&sort=${param['sort']}">${names.number-1}</a>&nbsp;
										<a
										href="/admin/name?page=${names.number}&size=${names.size}&sort=${param['sort']}">${names.number}</a>&nbsp;
										<b>${names.number+1}</b></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="/admin/name?page=${names.number}&size=${names.size}&sort=${param['sort']}">${names.number}</a>&nbsp;
										<b>${names.number+1}</b>&nbsp; <a
										href="/admin/name?page=${names.number+2}&size=${names.size}&sort=${param['sort']}">${names.number+2}</a>
									</td>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>

					<td><c:if test="${names.number ne names.totalPages-1}">
							<b><a
								href="/admin/name?page=${names.number+2}&size=${names.size}&sort=${param['sort']}">&nbsp;&nbsp;&nbsp;next&nbsp;&nbsp;&nbsp;</a></b>
						</c:if> <c:if test="${names.number eq names.totalPages-1}">&nbsp;&nbsp;&nbsp;next&nbsp;&nbsp;&nbsp;</c:if></td>
				</tr>
			</table>
		</c:if>
		<table>
			<tr>
				<td><a href="/admin/name?page=1&size=1&sort=${param['sort']}">1</a></td>
				<td><a href="/admin/name?page=1&size=5&sort=${param['sort']}">5</a></td>
				<td><a href="/admin/name?page=1&size=10&sort=${param['sort']}">10</a></td>
				<td><a href="/admin/name?page=1&size=20&sort=${param['sort']}">20</a></td>
			</tr>

		</table>
		<table>
			<tr>
				<c:choose>
					<c:when test="${param['sort'] eq ''}">
						<td>
						<td><a href="?page=1&size=${names.size}&sort=names">Name
								asc</a></td>
						<td><a href="?page=1&size=${names.size}&sort=names,desc">Name
								desc</a></td>

					</c:when>
					<c:when test="${param['sort'] eq 'names,desc'}">
						<td><a href="?page=1&size=${names.size}&sort=names">Name
								asc</a></td>
						<td><b>Name desc</b></td>
					</c:when>
					<c:otherwise>
						<td><b>Name asc</b></td>
						<td><a href="?page=1&size=${names.size}&sort=names,desc">Name
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
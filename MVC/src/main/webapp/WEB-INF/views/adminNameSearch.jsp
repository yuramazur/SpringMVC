<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NameSearch</title>
<style></style>
</head>
<body>
	<div class="div1">

		<h1 align="center">Names Search:</h1>

		<table align="center"> 
			<tr>

				<td align="center">
					<form name="searchName" method="post" action="/admin/name/search">

						<a><b>Search Name:</b></a><br> <input name="names" size="30">
						<input type="submit" value="Search">
					</form>
				</td>



			</tr>
		</table>

		<table width="500px">
			<tr>
				<th>Result List:</th>
			</tr>
			<c:if test="${size == 0}">
			<tr>
				<th><font color="red" size="25">Matches not found!</font></th>
				</tr>
			</c:if>
			<c:forEach items="${names}" var="name">
				<tr>
					<td><b>${name.names}</b></td>
					<th><a href="/admin/name/delete/${name.id}">delete</a></th>

				</tr>
			</c:forEach>


		</table>


		<form name="returnToAdmin" action="/admin/name">
			<p align="right">
				<input type="submit" value="RETURN">
			</p>

		</form>

	</div>
</body>
</html>
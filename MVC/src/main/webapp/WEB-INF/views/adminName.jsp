<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carriers</title>
<style>
td{
text-align: center;
}
</style>
</head>
<body>
	<div class="div1">

		<h1 align="center">Name administration:</h1>

		<!-- 		<table width="100%"> -->
		<!-- 			<tr> -->
		<!-- 				<td align="center"> -->
		<%-- 					<form name="newName" method="post" action="/admin/name"> --%>

		<!-- 						<a><b>Enter new Name:</b></a><br> <input name="names" -->
		<!-- 							size="30"> <input type="submit" value="Enter"> -->
		<%-- 					</form> --%>
		<!-- 				</td> -->
		<!-- 				<td align="center"> -->
		<%-- 					<form name="searchName" method="post" action="/admin/name/search"> --%>

		<!-- 						<a><b>Names Search:</b></a><br> -->
		<!-- 						 <input name="names" -->
		<!-- 							size="30"> <input type="submit" value="Search"> -->
		<%-- 					</form> --%>
		<!-- 				</td> -->



		<!-- 			</tr> -->
		<!-- </table> -->

		<form:form action="/admin/name" method="post" modelAttribute="name">
			<form:hidden path="id" />
			<table>
			<tr>
			<td><a><b> Enter Name:</b></a></td>
			</tr>
 				<tr>
 					<td><form:errors path="names"/></td>
				</tr>
				<tr>
					<td><form:input path="names"/></td>
				</tr>
				<tr>
					<th align="right"><input type="submit" value="save"></th>
				</tr>
			</table>
		</form:form>



		<table border="1">
			<tr>
				<th colspan="3">Names List:</th>
			</tr>

			<c:forEach items="${names}" var="name">
				<tr>
					<td><b>${name.names}</b></td>
					<th><a href="/admin/name/delete/${name.id}">delete</a></th>
					<th><a href="/admin/name/update/${name.id}">update</a></th>
				</tr>
				
			</c:forEach>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="/resources/css/style.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<title><tiles:getAsString name="title" /></tiles>
	<title>Insert title here</title>
	<style>
body {
	border: solid 3px silver;
}

header {
	margin-bottom: 30px;
	background: gray;
	height: 80px;
	padding: 20px;
	border: solid 3px black;
}

footer {
	margin-left: 0px;
	background: gray;
}

table {
	
	margin-top : 20px;
	border-spacing: 5px;
	background: gray;
	
}

td {
width: ;
	background: white;
	padding: 10px;
	 word-break: break-all;
}

th {
width: ;
	background: white;
	padding: 10px;
	
}
.a1 {
	color: red;
}
.container {
	background: gray;
	padding-left: 100px;
}

.block1 {
	background: silver;
	padding-right: 20px;
	padding-left: 20px;
	border: solid 3px black;
}
</style>
</head>
<body>
	<header> <tiles:insertAttribute name="header" /> </header>

	<div class="container">
		<div class="block1">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<footer> <tiles:insertAttribute name="footer" /> </footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Halaman Utama - Katalog Rumah</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
  		<h2>Katalog Rumah</h2>
  		<form action="ActionController" method="post">
  		<a class="btn btn-info" href="JualRumah.jsp">Jual Rumah</a>
  		
        	<td><input type="hidden" name="action" value="Retrieve"><input class="btn btn-info" type="submit" value="Lihat Rumah"/></td> 
        </form>
	</div>
	<hr/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book-A-Cab</title>
</head>
<body>
<h1>Booking Form</h1>
<br><br>
<form action="show-trip" method="get">
	<label for="fromLoc">Pick Up:</label>
	<input type="text" id="fromLoc" name="fromLoc">
	<br/>
	<label for="toLoc">Drop Off:</label>
	<input type="text" id="toLoc" name="toLoc">
	<br/>
	<label for="seaters">Vehicle Type (No. Of Seaters):</label>
	<input type="text" id="seaters" name="seaters">
	<br/>
	<br/>		
	<input type="submit" value="Get Trip Info">
</form>

<center>

</center>

</body>
</html>
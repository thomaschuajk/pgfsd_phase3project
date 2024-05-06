<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabs List</title>
</head>
<body>

<h1>All Cabs</h1>
<table border="1">
	<tr>
		<th>Cab Id</th>
		<th>Driver Id</th>	
		<th>Seating Capacity</th>
		<th>Cab Status</th>	
		<th>Booking Id</th>
		<th>Pick Up Location</th>	
		<th>Drop Off Location</th>
	</tr>
	<c:forEach var="cab" items="${cabs}">
		<tr>
			<td>${cab.cabId }</td>
			<td>${cab.driverId}</td>
			<td>${cab.seatersCap}</td>
			<td>${cab.cabStatus}</td>
			<td>${cab.bookingId}</td>
			<td>${cab.fromLoc}</td>
			<td>${cab.toLoc}</td>			
		</tr>
	</c:forEach>
</table>

<br><br>
<a href="/">Main Page</a>
<br><br>

</body>
</html>
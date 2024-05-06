<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trip Detail</title>
</head>
<body>

<center>
<h1>Trip Detail</h1>

Pick Up From: ${trip.fromLoc}
</br></br>
Drop Off At: ${trip.toLoc }
<br></br>
Fare: ${trip.fare }
</br></br>
<form action="add-booking" method="get">	
	<input type="submit" value="Book A Cab!">
</form>
<br><br>
<form action="cancel-trip" method="get">	
	<input type="submit" value="Cancel">
</form>

</center>




</body>
</html>
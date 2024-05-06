<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Detail</title>
</head>
<body>
<center>
<h2>Booking Successful!</h2>
</br></br>
Booking Number: ${booking.bookingId}
</br></br>
Booking DateTime: ${booking.createdAt}
<br><br>
Cab Number: ${booking.cabId}
</br></br>
Pick Up From: ${booking.fromLoc}
</br></br>
Drop Off At: ${booking.toLoc }
<br></br>
Fare: ${booking.fare }
<br><br>
<a href="/">Main Page</a>
<br><br>
</center>


</body>
</html>
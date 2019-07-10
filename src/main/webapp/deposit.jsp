<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit Money</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="Firstp.css"/>
</head>
<body>
<%
if(session.getAttribute("username")==null)
{  response.sendRedirect("emplogin.jsp");
	}
%>
<div class="aa">
<center><img src="down.jpg" alt="user" class="image"></center>
<form action="deposit">
<i class="fa fa-user icon"></i><input type="text" name="acnumber" style="margin-bottom: 40px;" id="a" placeholder="Account Number" required><br>
   <i class="fa fa-money icon"></i><input type="text" name="amount" id="a" placeholder="Enter Amount" required><br><br>
<input type="submit"  class="login"  value="Deposit" >
</form>
</div>
</body>
</html>
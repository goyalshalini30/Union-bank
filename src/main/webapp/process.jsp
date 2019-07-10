<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
<link rel="stylesheet" type="text/css" href="process.css">
</head>
<body >
<%
if(session.getAttribute("username")==null)
{  response.sendRedirect("emplogin.jsp");
	}
%>
<div class="sidenav" style="display:none" id="mySidebar">
  
<a href="#" class="closebtn" onclick="w3_close()">&times</a>
<a href="open.jsp">Account Open</a>
<a href="deposit.jsp">Deposit</a>
<a href="withdraw.jsp">Withdraw</a>
<a href="transfer.jsp">Transfer</a>
<a href="balanceenquiry.jsp">Balance Enquiry</a>
<a href="close.jsp">Account Close</a>
</div>

<div id="main">

 <div class="w3-teal">
    <button id="openNav" class="w3-button w3-teal w3-xlarge" onclick="w3_open()">&#9776;</button>
    <div class="w3-container">
    </div>
 </div>

</div>

<script>
function w3_open() {
  document.getElementById("main").style.marginLeft = "0%";
  document.getElementById("mySidebar").style.width = "25%";
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("openNav").style.display = 'none';
}
function w3_close() {
  document.getElementById("main").style.marginLeft = "0%";
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("openNav").style.display = "inline-block";
}
</script>

</body>
</html>
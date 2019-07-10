<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Front</title>
<style>
body{
background-image:url("back.jpeg");
    height: 100%; 
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.button1{
display: inline-block;
    box-sizing: border-box;
    margin: 0 auto;
    padding: 8px 22px;
	font-size:20px;
    width:180px;
	 font-weight: lighter;
    max-width: 200px;
	margin-left:20px;
	margin-right:20px;
    background: transparent;
    border-radius: 3px;
    color: #fff;
	border-color:white;
    text-align: center;
    text-decoration: none;
    letter-spacing: 1px;
    transition: all 0.3s ease-out;
}
.button1:hover{
background-color:rgba(255, 255, 255, 0.5);
color:black;

}
</style>
<script>
function signup1(){
document.first.action="adminlogin.jsp";
document.first.submit();
}
</script>
</head>
<body>
<center><h1 style="color:white;font-family:Roboto;font-size:70px;margin-top:150px;margin-bottom:8px; font-weight: lighter">Unite Bank</h1>
<h1  style="color: #d8d8d8;font-size:30px;letter-spacing: 1px; font-weight: lighter"><i>Dream &nbsp Trust &nbsp &nbsp Achieve</i></h1><br><br><br><br><br><br><br><br>
<form name="first" action="emplogin.jsp">
<input type="submit" class="button1"  value="LOG IN">
<input type="button" class="button1"  onclick="signup1()" value="ADMIN PANEL">
</form>
</center>
</body>
</html>
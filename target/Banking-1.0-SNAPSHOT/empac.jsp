<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
	<link rel="stylesheet" type="text/css" href="Firstp.css"/>
	<link rel="stylesheet" type="text/css" href="form.css"/>
</head>
<body>
<%
if(session.getAttribute("adminname")==null){
	response.sendRedirect("adminlogin.jsp");
}

%>
<div class="main">
        <h2>Employee Account</h2>
        <form action="Form">   
            <p>Name</p>
			<input type="text" id="a1" name="name" placeholder="Enter Name"  required>
            <p>Gender </p>
            <input type="radio" value="male" style="margin-bottom:10px;margin-top:10px"name="gender"><label>M<label>
            <input type="radio" value="female" name="gender"><label>F<label>
			<p>Address</p>
            <input type="text"  placeholder="Enter Address"   name="address" id="a1" required></textarea>
			<p>Country</p>
<select name="country" placeholder="Country" id="a1" >
	<option>India</option> 
	<option>Australia</option> 
	<option>Nepal</option> 
	<option>Pakistan</option>
</select>
<p>Contact Number </p>
<input type="text" pattern="[0-9]{10}" id="a1" name="mob" title="Enter only 10 digits"  required>
<p>Email Address</p>
<input type="email" placeholder="e.g.abd@gmail.com" id="a1" name="email" required >
<p>Date Of Birth</p>
              <input type="date" id="a1"  placeholder="DD/MM/YYYY"  name="dob" required>
              <p>Password</p>
              <input type="password" placeholder="************"  id="password" name="password" required>
			 <span><i class="fa fa-eye" id="eye" style="color:#8E8E8E;"  onclick="myFunction()" ></i></span>
			 <input type="submit" style="margin-right:15px;margin-left:70px" class="login" value="Register">
<input type="reset" style="margin:5px;" class="login" value="Reset">
        </form>
    </div>
    	<script>
function myFunction() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
</script>
</body>
</html>
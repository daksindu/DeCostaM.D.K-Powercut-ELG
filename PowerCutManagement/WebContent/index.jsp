<%@page import="com.Power"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/index.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Power Management</h1>
<form id="formpower" name="formpower" method="post" action="index.jsp">

Date:
<input id="date" name="date" type="text"
 class="form-control form-control-sm">
<br>Time:
<input id="time" name="time" type="text"
 class="form-control form-control-sm">
<br>Area:
<input id="area" name="area" type="text"
 class="form-control form-control-sm">
<br>Duration:
<input id="duration" name="duration" type="text"
 class="form-control form-control-sm">
 <br>
  <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPowerIDSave" 
 name="hidPowerIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPowerGrid">
	<%
				Power PowerObj = new Power();
		 		out.print(PowerObj.readIndex());
	%>
</div>
</div> </div></div>
</body>
</html>
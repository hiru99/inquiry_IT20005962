<%@page import="com.InquiryManage.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inquiry Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/inquiry.js"></script>
	</head>

	<style>
	body {
		background-image: url("image/inq.jpg");
	}
</style>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<div style = "background-color:white; width:500px"><h1>&nbsp;&nbsp; Inquiry Management </h1>
		
			<form id="formInquiry" name="formInquiry">
			
 				&nbsp;&nbsp;&nbsp;&nbsp; Person Name : 
 				<input id="PersonName" name="PersonName" type="text" placeholder="Enter Person Name"
 				class="form-control form-control-sm" style="width:300px;margin-left:40px"> <br>
 				
 				&nbsp;&nbsp;&nbsp;&nbsp; Area : 
 				<input id="Area" name="Area" type="text" placeholder="Enter Inquiry Area"
				class="form-control form-control-sm" style="width:300px;margin-left:40px"><br>
 				
 				&nbsp;&nbsp;&nbsp;&nbsp; Inquiry Date : 
 				<input id="Date" name="Date" type="text" placeholder="Enter Inquiry Date"
 				class="form-control form-control-sm" style="width:300px;margin-left:40px"><br> 
 				
 				&nbsp;&nbsp;&nbsp;&nbsp; Inquiry Reason : 
 				<input id="Reason" name="Reason" type="text" placeholder="Enter Inquiry Reason"
 				class="form-control form-control-sm" style="width:300px;margin-left:40px"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary" style="margin-left:150px">
 				<input type="hidden" id="hidInquiryIDSave" 
				name="hidInquiryIDSave" value="">
				
			</form></div><br>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divInquiryGrid">
 		<%
 			Inquiry inqObj = new Inquiry(); 
 		 		out.print(inqObj.readInquiry());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>

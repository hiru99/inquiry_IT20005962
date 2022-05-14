$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validateInquiryForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidInquiryIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "InquiryAPI",
 type : type,
 data : $("#formInquiry").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onInquirySaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidInquiryIDSave").val($(this).data("inquiryid"));
 $("#PersonName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Area").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Date").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Reason").val($(this).closest("tr").find('td:eq(3)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "InquiryAPI",
 type : "DELETE",
 data : "inqID=" + $(this).data("inquiryid"),
 dataType : "text",
 complete : function(response, status)
 {
 onInquiryDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateInquiryForm()
{
	

// person name
if ($("#PersonName").val().trim() == "")
 {
 return "Insert PersonName.";
 } 

// Inquiry Area-------------------------------
if ($("#Area").val().trim() == "")
 {
 return "Insert Area.";
 }

// Inquiry Date-------------------------------
if ($("#Date").val().trim() == "")
 {
 return "Insert Date.";
 }
  
 // Inquiry Reason-------------------------------
if ($("#Reason").val().trim() == "")
 {
 return "Insert Reason.";
 }
 
return true;
}

function onInquirySaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divInquiryGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidInquiryIDSave").val("");
 $("#formInquiry")[0].reset();
}

function onInquiryDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divInquiryGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

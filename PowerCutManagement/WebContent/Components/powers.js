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
//Form validation-------------------
 var status = validatePowerForm(); 

 if (status != true) 
  { 
  $("#alertError").text(status); 
  $("#alertError").show(); 
  return; 
  } 
//If valid------------------------
 var type = ($("#hidPowerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PowerAPI", 
 type : type, 
 data : $("#formPower").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFundSaveComplete(response.responseText, status); 
 } 
 }); 
 }); 
//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidPowerIDSave").val($(this).data("powerid")); 
 $("#date").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#time").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#area").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#duration").val($(this).closest("tr").find('td:eq(3)').text());  
});
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "PowerAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("powerid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onPowerDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

//CLIENT-MODEL================================================================
function validateFundForm() 
{ 
//Date
if ($("#date").val().trim() == "") 
{ 
return "Insert Date."; 
} 
//Time
if ($("#time").val().trim() == "") 
{ 
return "Insert Time."; 
} 
//Area-------------------------------
if ($("#area").val().trim() == "") 
{ 
return "Insert Area."; 
} 
//Duration
if ($("#duration").val().trim() == "") 
{ 
return "Insert Duration."; 
} 
return true; 
}
function onPowerSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPowerGrid").html(resultSet.data); 
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
$("#hidPowerIDSave").val(""); 
$("#formPower")[0].reset(); 
}

function onPowerDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPowerGrid").html(resultSet.data); 
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



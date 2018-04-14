function doesUserExist()
{	xmlHttp=GetXmlHttpObject() 
var facultyid=document.getElementById("facultyname").value; 
var url="controller2";  
xmlHttp.open("POST",url,true);
xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlHttp.send("loginfacultyid="+facultyid)
xmlHttp.onreadystatechange  = validateUIDCheckResponse;
}	
function GetXmlHttpObject()
{ var xmlHttp=null;
try {
	 xmlHttp=new XMLHttpRequest(); }
catch (e)
 {
	 try{ 	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 	  }
	 catch (e) { 	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 	  }
 }	return xmlHttp;
}

function validateUIDCheckResponse() {
if (xmlHttp.readyState == 4) {
	if(xmlHttp.status == 200) {
		var response= xmlHttp.responseText; 
		var parsedJsonRes=JSON.parse(response);
		if(parsedJsonRes.facultyid_check=="not_exists"){
			document.getElementById("ajaxresponsefield").innerHTML="*invalid facultyid";
			
		}
		if(parsedJsonRes.facultyid_check=="exists"){
			document.getElementById("ajaxresponsefield").innerHTML="";
		}
	}
		
		/*if(response=="failed"){
		 document.getElementById("ajaxresponsefield").innerHTML="*invalid userid";
		 }*/
		 
	else alert("ajax call failed");
}}














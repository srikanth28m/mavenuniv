function doesUserExist()
{	xmlHttp=GetXmlHttpObject() 
var studentid=document.getElementById("studentid").value; 
var url="controller1";  
xmlHttp.open("POST",url,true);
xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlHttp.send("loginstudentid="+studentid)
xmlHttp.onreadystatechange  = validateUIDCheckResponse;
}	

function validateUIDCheckResponse() {
	if (xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var response= xmlHttp.responseText; 
			var parsedJsonRes=JSON.parse(response);
			
			
			if(parsedJsonRes.studentid_check=="not_exists"){
				 document.getElementById("ajaxresponsefield").innerHTML="invalid studentid";
				 }
				 if(parsedJsonRes.studentid_check=="exists"){
				 document.getElementById("ajaxresponsefield").innerHTML="";
				 }
			/*if(response=="failed"){
			 document.getElementById("ajaxresponsefield").innerHTML="*invalid userid";
			 }*/
			 }
		else   alert("ajax call failed");
	}
	}


function registerStudentExist()
{	xmlHttp=GetXmlHttpObject() 
	var regstudentid=document.getElementById("registerstudentid").value; 
	var url="controller3";  
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send("rstudentid="+regstudentid)
	xmlHttp.onreadystatechange  = registerSIDCheckResponse;
	}	
function registerSIDCheckResponse() {
	if (xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var response= xmlHttp.responseText; 
			var parsedJsonRes=JSON.parse(response);
			if(parsedJsonRes.regstudentid_check=="null"){
				 document.getElementById("registerresponsefield").innerHTML="studentid is empty";
				 }
			if(parsedJsonRes.regstudentid_check=="exists"){
				 document.getElementById("registerresponsefield").innerHTML="studentid already exists";
				 }
			 
				 if(parsedJsonRes.regstudentid_check=="not_exists"){
				 document.getElementById("registerresponsefield").innerHTML="";
				 }
		 }
		else   alert("ajax call failed");
	}
	}


function GetXmlHttpObject()
{ var xmlHttp=null;
try {
	 xmlHttp=new XMLHttpRequest(); 
	 }
catch (e)
 {
	 try{ 	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 	 
	  }
	 catch (e) { 
	 	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 	  }
 }
 	return xmlHttp;
}

function newStudentRegister (){
document.getElementById("tableregister").style="display:block";

document.getElementById("signin").style="display:none";
document.getElementById("divregister").style="display:none";
document.getElementById("divlogin").style="display:block";
}

function registerStudentLogin(){
document.getElementById("tableregister").style="display:none";
document.getElementById("signin").style="display:block";
document.getElementById("divregister").style="display:block";
document.getElementById("divlogin").style="display:none";
}



		
		
		
		
		
		
		
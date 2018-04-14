	function stRegistration()
{
document.getElementById("courseregister").style="display:block";
document.getElementById("editprofile").style="display:none";
document.getElementById("mycourses").style="display:none";
document.getElementById("courses").style="display:none";
document.getElementById("feesdiv").style="display:none";

}
	function editProfile()
{
document.getElementById("editprofile").style="display:block";
document.getElementById("courseregister").style="display:none";
document.getElementById("courses").style="display:none";
document.getElementById("feesdiv").style="display:none";
}
	
//	function showRegCourse(){
//		
//		document.getElementById("abcRegCourse").style="display:block";
//		document.getElementById("courseregister").style="display:none";
//		document.getElementById("mycourses").style="display:none";
//	}
	function myCourses(){
		
		document.getElementById("courses").style="display:block";
		document.getElementById("courseregister").style="display:none";
		document.getElementById("editprofile").style="display:none";
		document.getElementById("abcRegCourse").style="display:none";
		document.getElementById("feesdiv").style="display:none";
	}
	function feeDetails(){
		document.getElementById("courses").style="display:none";
		document.getElementById("courseregister").style="display:none";
		document.getElementById("editprofile").style="display:none";
		document.getElementById("abcRegCourse").style="display:none";
		document.getElementById("feesdiv").style="display:block";
	
	}
	function payment(){
		document.getElementById("paymentdiv").style="display:block";
	}
	

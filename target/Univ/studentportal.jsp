<!-- <!DOCTYPE html> -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" import="java.lang.String" %>

<html>


<head>
<link rel="stylesheet" type="text/css" href="studentportal.css"/>

<script src="studentportal.js">

</script>

</head>
<body>

<% String userid=(String) session.getAttribute("id"); %>
<% String password=(String) session.getAttribute("pswd"); %>
<% String semister=(String) session.getAttribute("semester"); %>
<% String course1=(String) session.getAttribute("course1");  %>
<% String course2=(String) session.getAttribute("course2");  %>
<% String course3=(String) session.getAttribute("course3");  %>
<% String fee=(String) session.getAttribute("fees");  %>
<div id="h1div">
<h1 style="color:#D8D8D8;">My University</h1>
</div>

<div >
<% String stId=(String) session.getAttribute("id"); %>
<% if(stId!=null){ %>
<h2 style="width: 300px;margin:auto;">Welcome <%=stId %>
</h2>
<%} %>
</div>


<div id="container">
<div id="navigation">
<ul id="navmenu">

<li id="mydropdown"><a>Home</a>
<div id="mydropdown-content">
<a onclick="editProfile()">Change Password</a>

<a href="stlogin.html">LogOut</a>
</div>
</li>

<li id="spdropdown"><a>Courses</a>
<div id="spdropdown-content">
<a onclick="myCourses()">My Courses</a>
<a onclick="stRegistration()">Register Courses</a>
</div>

</li>
<li id="admindropdown"><a>Administrative Services</a>
<div id="admindropdown-content">
<a onclick="feeDetails()">My Ledger</a>
<a href="#">My Grades</a>
</div>
</li>

</ul>
</div>
</div>

<div id="feesdiv" style="display:none">
<%if(course1!=null && course2!=null && course3!=null){
	if(fee==null){%>

<table>
<tr>
<td><%=course1 %></td>
<td>1000</td>
</tr>
<tr>
<td><%=course2 %></td>
<td>1000</td>
</tr>
<tr>
<td><%=course3 %></td>
<td>1000</td>
</tr>
<tr>
<td>Insurance</td>
<td>100</td>
</tr>
<tr>
<td>ServiceFee</td>
<td>50</td>
</tr>
<tr>
<td>Tax</td>
<td>50</td>
</tr>

<tr>
<td>TotalFee</td>
<td><input type="text" value="3200" name="totfee" readonly/></td>
</tr>
<tr>
<td></td>
<td><input type="button" value="pay" onclick="payment()"></td>
</tr>
</table>
<%} else{ %>
No fee due already paid
<%} %>
<%} else{ %>
You need to Register for Courses
<%} %>

<div id="paymentdiv" style="display:none">
<form action="feepayment">
<input type="hidden" value="<%=userid %>" id="uid" name="ruserid"/>
<input type="hidden" value="<%=semister %>" id="sem" name="rsemster"/>
<table id="paytable">

<tr>
<td>Card Number</td>
<td><input type="text" name="cnumber" required/></td>
</tr>

<tr>
<td>Name on Card</td>
<td><input type="text" name="nameoncard" required/></td>
</tr>

<tr>
<td>Expiration Date</td>
<td><input type="text" name="expdate" required/></td>
</tr>
<tr>
<td>CVV</td>
<td><input type="text" name="cvv" required/></td>
</tr>
<tr>
<td>Address</td>
<td><input type="text" name="address" required/></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="PayFee" /></td>
</tr>
</table>
</form>

</div>
</div>

<div id="editprofile" style="display:none;">
<form action="editprofile">

Enter StudentId<input type="text" id="oldstudentid" name="studentId"  required/><br>
    Password<input type="password" id="oldpswd" name="oldpassword" required/><br>
    New Password<input type="password" id="newpswd" name="newpassword" required/><br>
    <input type="submit" value="update"/>
    </form>
    
</div>



<div id="courseregister" style="display:none;">
<form action="controller4">
*please select three courses*
<input type="hidden" value="<%=userid %>" id="uid" name="ruserid"/>
<input type="hidden" value="<%=semister %>" id="sem" name="rsemster"/>
<br><input type="text" id="courseID1" name="cID1" required/><br>
<input type="text" id="courseID2" name="cID2" required/><br>
<input type="text" id="courseID3" name="cID3" required/><br>

<input  type="submit" value="Register" />
</form>

<table id="coursereg">
<tr>
<td>COURSE ID</td>
<td>COURSE NAME</td>
<td>PROFESSOR</td>
</tr>

<tr>
<td>C-OS</td>
<td>Operating System</td>
<td>Manoj</td>
</tr>

<tr>
<td>C-SE</td>
<td>Software Engineering</td>
<td>Suman</td>
</tr>

<tr>
<td>C-DB</td>
<td>Data Base</td>
<td>Rishab</td>
</tr>

<tr>
<td>C-RM</td>
<td>Research Methods</td>
<td>Manoj</td>
</tr>

<tr>
<td>C-ST</td>
<td>Software Testing</td>
<td>Suman</td>
</tr>

<tr>
<td>C-CP</td>
<td>Career Planing</td>
<td>Srikanth</td>
</tr>
</table>
</div>
<div id="abcRegCourse" style="diplay:none">
<% String c1=(String) session.getAttribute("cID1"); %>
<% String c2=(String) session.getAttribute("cID2"); %>
<% String c3=(String) session.getAttribute("cID3"); %>


 <% if(c1!=null && c2!=null && c3!=null){%>
 <p>you have registered courses : <br>
 <%=c1 %><br><%=c2 %><br>
 <br><%=c3 %></p>
 <% }%>
</div>

<div id="mycourses" style="display:none;">
<form action="controller4" method="post">

<input type="hidden" value="<%=userid %>" id="uid" name="userid"/>
<input type="hidden" value="<%=semister %>" id="sem" name="semster"/>

</form>
</div>
<div id="courses" style="display:none;">

<% if(course1!=null && course2!=null && course3!=null){ %>
<p> your registered courses are :<br>
  course 1 :<%=course1%><br>
  course 2 : <%=course2%><br>
  course 3 : <%=course3%><br>
</p>
<%} else{%>
No Courses need to register
<%} %>
</div>
</body>
</html>

package com.sample.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sample.jdbc.UsersTable;

public class EditProfile extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		update(req,res);
	}
	public void dopost(HttpServletRequest req,HttpServletResponse res)
	{
		update(req,res);
	}
	 Logger log=Logger.getLogger(StudentCourseRegister.class);
		public void update(HttpServletRequest req,HttpServletResponse res) {
			try{
				PrintWriter pw=res.getWriter();
				String stId=req.getParameter("studentId");
			String oldpswd=req.getParameter("oldpassword");
			String newpswd=req.getParameter("newpassword");
			boolean isvalid=UsersTable.checkStudentIdandpwd(stId, oldpswd);
			if(stId!=null && newpswd!= null ){
				if(isvalid){
				UsersTable.updatePassword(stId, oldpswd, newpswd);
				log.info(stId);
				
				log.info(oldpswd);
				log.info(newpswd);
				res.sendRedirect("studentportal.jsp");
				}
				else{
					res.setContentType("text/html");  
					pw.println("<script type=\"text/javascript\">");  
					pw.println("alert('please check your id and password');");  
					pw.println("location='studentportal.jsp';");
					pw.println("</script>");
			
				}
			}
			else{
			res.sendRedirect("error.html");;
			}
		}
			catch(Exception e){
				e.printStackTrace();
				
			}
}
}

package com.sample.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.biz.Business;

public class Facultylogin extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		process(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		process(req,res);
	}
	
	public void process(HttpServletRequest req,HttpServletResponse res)
	{
		try{
			
			//conn=DBConnection.getDBConnection("mysql");
			//conn.setAutoCommit(false);
			//boolean isValid=UsersTable.validateLogin(userid, password, conn);
			String facultyid=req.getParameter("name");
			String password=req.getParameter("pswd");
			String department=req.getParameter("department");
			System.out.println("in servlets "+facultyid);
			System.out.println("in servlets "+password);
			System.out.println("in servlets "+department);
			
			boolean isValid=Business.facultyProcessAuth(facultyid,password,department);
			
			System.out.println("before if condition"+isValid);
			if(isValid){
				System.out.println("in servlets "+isValid);
				res.sendRedirect("facultyportal.html");
			}else{
				System.out.println("in servlets "+isValid);
				res.sendRedirect("error.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
		}
	}
}

	
	
	
	
	
	
	/*public void process(HttpServletRequest req,HttpServletResponse res)
	{
		try{
			String checkLoginFacultyId=req.getParameter("loginfacultyid");
			if(checkLoginFacultyId!=null){
				
			String jsonSucessResponse="";
			jsonSucessResponse+="{ \n";
			jsonSucessResponse+="\"facultyid_check\":\"exists\"\n";
			jsonSucessResponse+="}\n";	
			
			String jsonFailedResponse="";
			jsonFailedResponse+="{ \n";
			jsonFailedResponse+="\"facultyid_check\":\"not_exists\"\n";
			jsonFailedResponse+="}\n";
			
			res.setContentType("application/json");
			if(checkLoginFacultyId.equalsIgnoreCase("2001")){
				PrintWriter pw=res.getWriter();
				pw.write(jsonSucessResponse);
				pw.close();
				}
			else{
				PrintWriter pw=res.getWriter();
				pw.write(jsonFailedResponse);
				pw.close();
				}
			
			
//			/*if(checkLoginUserId.equalsIgnoreCase("java")){
//				PrintWriter pw=res.getWriter();
//				pw.write("sucess");
//				pw.close();
//				
//			}
//			else{
//				PrintWriter pw=res.getWriter();
//				pw.write("failed");
//				pw.close();
//			}
			return;
			}
			
			String userid=req.getParameter("name");
			String password=req.getParameter("pswd");
			String dep=req.getParameter("department");
			System.out.println(userid);
			System.out.println(password);
			if((userid.equalsIgnoreCase("2001") && password.equalsIgnoreCase("jee")) || dep.equals("IT"))
				res.sendRedirect("facultyportal.html");
			else
				res.sendRedirect("error.html");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/



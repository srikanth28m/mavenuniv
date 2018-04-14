package com.sample.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.jdbc.DBConnection;
import com.sample.jdbc.UsersTable;

public class RegisterStudent extends HttpServlet{

public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		registerProcess(req,res);
	}
public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		registerProcess(req,res);
	}
	
public void registerProcess(HttpServletRequest req,HttpServletResponse res){
	Connection conn=null;

	try{
		conn=DBConnection.getDBConnection("mysql");
		conn.setAutoCommit(false);

		checkRegStudentIdProcess(req, res);		
		String studentid=req.getParameter("registerid");
		String fname=req.getParameter("firstname");
		String lname=req.getParameter("lastname");
		String semester=req.getParameter("rsemester");
		String password=req.getParameter("rpassword");
		System.out.println(studentid);
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(semester);
		System.out.println(password);
		boolean stIdExists=UsersTable.checkStudentId(studentid);
		if(stIdExists==false && password!=null){
			UsersTable.insertStudent(studentid, fname, lname, semester, password, conn);
			res.sendRedirect("stlogin.html");
		}
	
		conn.commit();
	}
		catch(Exception e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			try{conn.close();}catch(Exception e){}
		}
	}
	
public void checkRegStudentIdProcess(HttpServletRequest req,HttpServletResponse res){
	String checkRegStudentId=req.getParameter("rstudentid");
	Connection conn=null;
	try{
	if(checkRegStudentId!=null){
		conn=DBConnection.getDBConnection("mysql");
		conn.setAutoCommit(false);
		
	
		String jsonSucessResponse="";
		jsonSucessResponse+="{ \n";
		jsonSucessResponse+="\"regstudentid_check\":\"exists\"\n";
		jsonSucessResponse+="}\n";
		
		String jsonFailedResponse="";
		jsonFailedResponse+="{ \n";
		jsonFailedResponse+="\"regstudentid_check\":\"not_exists\"\n";
		jsonFailedResponse+="}\n";
		
		res.setContentType("application/json");
		boolean isValid=UsersTable.validateStudentId(checkRegStudentId);
	if(isValid){
		PrintWriter pw=res.getWriter();
		pw.write(jsonSucessResponse);
		pw.close();
	}
	else{
		PrintWriter pw=res.getWriter();
		pw.write(jsonFailedResponse);
		pw.close();
	}
	return;
	}
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	


	
	
	
}

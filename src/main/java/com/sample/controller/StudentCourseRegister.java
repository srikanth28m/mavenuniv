package com.sample.controller;



import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.sample.biz.Business;
import com.sample.jdbc.DBConnection;
import com.sample.jdbc.UsersTable;

public class StudentCourseRegister extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		courseReg(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		
		String course1=req.getParameter("cID1");
		String course2=req.getParameter("cID2");
		String course3=req.getParameter("cID3");
		
		if(course1!=null && course2!=null && course3!=null){
		courseReg(req,res);
		}
		
	}
	 Logger log=Logger.getLogger(StudentCourseRegister.class);
		static String c1;
		static String c2;
		static String c3;

	public void courseReg(HttpServletRequest req,HttpServletResponse res)
	{
	
		try{
			HttpSession session=req.getSession();
			PrintWriter pw=res.getWriter();
			String course1=req.getParameter("cID1");
			String course2=req.getParameter("cID2");
			String course3=req.getParameter("cID3");
			
			String userid=req.getParameter("ruserid");
			String sem=req.getParameter("rsemster");
			String[] resultSet= UsersTable.getStudentCourses(userid,sem);
			c1=resultSet[0];
			c2=resultSet[1];
			c3=resultSet[2];
			log.info(userid);
			log.info(sem);
			log.info(c1);
			log.info(c2);
			log.info(c3);
			log.info(course1);
			log.info(course2);
			log.info(course3);
			if(c1==null && c2==null && c3==null){
				session.setAttribute("cID1", course1);
				session.setAttribute("cID2", course2);
				session.setAttribute("cID3", course3);
			log.info("calling coursereg ");
		UsersTable.courseReg(course1, course2, course3, userid,sem);
		RequestDispatcher dispatcher= req.getRequestDispatcher("studentportal.jsp");
		log.info("forwarding req and res ");
		dispatcher.forward(req, res);
			}
			else{
				res.setContentType("text/html");  
				pw.println("<script type=\"text/javascript\">");  
				pw.println("alert('already registered look my courses');");  
				pw.println("location='studentportal.jsp';");
				pw.println("</script>");
			}
		
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	

	
	
}

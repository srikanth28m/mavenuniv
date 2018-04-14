package com.sample.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sample.biz.Business;
import com.sample.jdbc.DBConnection;
import com.sample.jdbc.UsersTable;


@WebService(serviceName="soap_auth" )
@SOAPBinding(style=Style.DOCUMENT)
public class Stlogin extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		loginProcess(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		loginProcess(req,res);
	}
	Logger log=Logger.getLogger(Stlogin.class);
	public void loginProcess(HttpServletRequest req,HttpServletResponse res)
	{
		
		
	
		try{
			

			HttpSession session=req.getSession();
			log.info("db connection opened");
			
			checkLoginStudentIdprocess(req, res);
			boolean isValid=Business.stProcessAuth(req);
			String stId=req.getParameter("id");
			String pswd=req.getParameter("pswd");
			String sem=req.getParameter("semester");
			session.setAttribute("id", stId);
			session.setAttribute("pswd", pswd);
			session.setAttribute("semester", sem);
			if(isValid){
				  RequestDispatcher dispatcher = req.getRequestDispatcher("studentportal.jsp");
						myCourse(stId,sem,req, res);
						String fee=UsersTable.getFees(stId, sem);
						session.setAttribute("fees", fee);
						log.info(fee);
			log.info("forwarding req and res ");
			dispatcher.forward(req, res);
				
				
			}
			else{
				res.sendRedirect("error.html");
			}
			
		}
			catch(Exception e)
		{
				e.printStackTrace();
		}
		}
		
		
	
	public void checkLoginStudentIdprocess(HttpServletRequest req,HttpServletResponse res) throws IOException, SQLException{
		String checkLoginStudentId=req.getParameter("loginstudentid");
	
		try{
		if(checkLoginStudentId!=null){
			
		
			
		
			String jsonSucessResponse="";
			jsonSucessResponse+="{ \n";
			jsonSucessResponse+="\"studentid_check\":\"exists\"\n";
			jsonSucessResponse+="}\n";
			
			String jsonFailedResponse="";
			jsonFailedResponse+="{ \n";
			jsonFailedResponse+="\"studentid_check\":\"not_exists\"\n";
			jsonFailedResponse+="}\n";
			
			res.setContentType("application/json");
			boolean isValid=UsersTable.validateStudentId(checkLoginStudentId);
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
		catch(Exception e){
			e.printStackTrace();
		}
}
	
	public void myCourse(String userid,String sem,HttpServletRequest req,HttpServletResponse res) throws Exception{
		HttpSession session=req.getSession();
		
			String[] resultSet= UsersTable.getStudentCourses(userid,sem);
			log.info("resultSet"+resultSet);
			try{
			log.info("course1 : "+resultSet[0]);
			log.info("course1 : "+resultSet[1]);
			log.info("course1 : "+resultSet[2]);
			session.setAttribute("course1", resultSet[0]);
			session.setAttribute("course2", resultSet[1]);
			session.setAttribute("course3", resultSet[2]);
			session.setAttribute("id", userid);
			}
			catch(Exception e){
				e.printStackTrace();
			}	
	}
	
}










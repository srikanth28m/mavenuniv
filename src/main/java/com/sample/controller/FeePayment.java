package com.sample.controller;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sample.jdbc.UsersTable;

public class FeePayment extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		paymentProcess(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		paymentProcess(req,res);
	}
	Logger log=Logger.getLogger(Stlogin.class);
	public void paymentProcess(HttpServletRequest req,HttpServletResponse res){
		HttpSession session=req.getSession();
		
		String stId=req.getParameter("ruserid");
		String sem=req.getParameter("rsemster");
		String cnumb=req.getParameter("cnumber");
		String namec=req.getParameter("nameoncard");
		String expDate=req.getParameter("expdate");
		String cvv=req.getParameter("cvv");
		String address=req.getParameter("address");
		log.info(stId);
		log.info(sem);
		log.info(cnumb);
		log.info(namec);
		log.info(expDate);
		log.info(cvv);
		log.info(address);
		try{
			PrintWriter pw=res.getWriter();
		if(stId!=null && sem!=null && cnumb!=null && namec!=null && expDate!=null && cvv!=null && address!=null){
			UsersTable.payment(stId, sem, cnumb, namec, expDate, cvv, address);
//			RequestDispatcher dispatcher=req.getRequestDispatcher("studentportal.jsp");
//			dispatcher.forward(req, res);
			res.setContentType("text/html");  
			pw.println("<script type=\"text/javascript\">");  
			pw.println("alert('payment success');");  
			pw.println("location='studentportal.jsp';");
			pw.println("</script>");
			UsersTable.insertFeePaid(stId, sem);
		}
		else{
			res.setContentType("text/html");  
			pw.println("<script type=\"text/javascript\">");  
			pw.println("alert('invalid details payment failed');");  
			pw.println("location='studentportal.jsp';");
			pw.println("</script>");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
}

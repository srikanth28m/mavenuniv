package com.sample.stloginsoap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.biz.Business;
import com.sample.jdbc.DBConnection;
import com.sample.jdbc.UsersTable;


@WebService(serviceName="soap_auth" )
@SOAPBinding(style=Style.DOCUMENT)

public class StloginSoap extends HttpServlet {
	
	
	public boolean loginProcess(String stId,String pwd)
	{
	
		try{
			
			boolean isValid=UsersTable.validateLogin(stId, pwd);
			if(isValid)
				return true;
			else
				return false;
			}
			catch(Exception e)
		{
				e.printStackTrace();
		}
		return false;
		}
		
		
	
	
	
}




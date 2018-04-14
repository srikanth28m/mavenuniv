package com.sample.biz;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sample.jdbc.UsersTable;
import com.sample.hibernate.HibernateUsersTable;



public class Business {
	
	
	public static boolean stProcessAuth(HttpServletRequest req){
			try{
		String studentid=req.getParameter("id");
		String password=req.getParameter("pswd");
		String semester=req.getParameter("semester");
		System.out.println(studentid);
		System.out.println(password);
		System.out.println(semester);
		boolean isValid=UsersTable.validateLogin(studentid, password);
		return isValid;
	}
			catch(Exception e){
				e.printStackTrace();
			}
			return false;
	}

	public static boolean facultyProcessAuth(String facultyid,String password,String department)throws SQLException{
		
	
		boolean isValid=HibernateUsersTable.validateLoginHibCriteria(facultyid, password, department);
		return isValid;
	}
	
}

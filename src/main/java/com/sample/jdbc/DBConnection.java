package com.sample.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
	public static Connection getDBConnection(String db) throws Exception
	{
		Connection conn =null;
		try{
			  Class.forName("com.mysql.jdbc.Driver");
			  conn = DriverManager.getConnection("jdbc:mysql://localhost/university","root","system"); 
		  return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

}

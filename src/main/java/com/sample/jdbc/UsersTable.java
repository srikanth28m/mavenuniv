package com.sample.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class UsersTable {
	
		
	static Logger log = Logger.getLogger(UsersTable.class);
		
public static void insertStudent(String studentid,String fname, String lname,String semester,String pswrd,Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("insert into registerstudent (studentid,firstname,lastname,semester,password) values (?,?,?,?,? )");
	            stmt.setString(1, studentid);
	            
	            stmt.setString(2, fname);
	            stmt.setString(3, lname);
	            stmt.setString(4, semester);
	            stmt.setString(5, pswrd);
	            stmt.executeUpdate();
	        }
	        catch (SQLException e)
	        {
	        	throw e;
	        }
	        finally
	        {
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	    }

public static String[] getStudentCourses(String stId,String sem) throws Exception{
	log.info("entered into getStudentCourses ");
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql"); 
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    boolean result=false;
    String courses[]=new String[3];
	try
    {
		log.info("student id for getcourses"+stId);
		log.info("semester for getcourses"+sem);
    	stmt = conn.prepareStatement("select course1,course2,course3 from registerstudent where (studentid=? and semester=?)");
        stmt.setString(1, stId);
        stmt.setString(2, sem);
        resultSet = stmt.executeQuery();
        
        while (resultSet.next())
        {
        	courses[0]=resultSet.getString("course1");
        	courses[1]=resultSet.getString("course2");
        	courses[2]=resultSet.getString("course3");
        	log.info(resultSet.getString("course1"));
        	log.info(resultSet.getString("course2"));
        	log.info(resultSet.getString("course3"));
        }
        
        log.info("resultset address : "+resultSet);
    }
    catch (SQLException e)
    {
    	System.out.println("Exception occired");
    	e.printStackTrace();
        throw e;
    }
    finally
    {
    	try{
    		resultSet.close();} catch(SQLException s){}
        try{
        	stmt.close();} catch(SQLException s){}
    }
    
    log.info("string courses address : "+courses);
	return courses;
}
	
public static void updatePassword(String stId,String oldpwd,String newpwd) throws Exception{
	log.info("entered into courseReg ");
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql");
    PreparedStatement stmt = null;
    try
    {
    	
    	conn.setAutoCommit(false);
    	log.info("updating details ");
        stmt = conn.prepareStatement("update registerstudent set password=? where studentid=? and password=?");
        stmt.setString(1, stId);
        stmt.setString(2, newpwd);
        stmt.setString(3, oldpwd);

        stmt.executeUpdate();
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
	
		//updateOrder("john","new_pasword")
public static void courseReg(String crse1,String crse2,String crse3,String stId,String sem) throws  Exception
	    {
			log.info("entered into courseReg ");
			Connection conn=null;
			conn=DBConnection.getDBConnection("mysql");
	        PreparedStatement stmt = null;
	        try
	        {
	        	log.info("courses : "+crse1+crse2+crse3);
	        	conn.setAutoCommit(false);
	        	log.info("updating course ");
	            stmt = conn.prepareStatement("update registerstudent set course1=?,course2=?,course3=? where studentid=? and semester=?");
	            stmt.setString(1, crse1);
	            stmt.setString(2, crse2);
	            stmt.setString(3, crse3);
	            stmt.setString(4, stId);
	            stmt.setString(5, sem);
	            stmt.executeUpdate();
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
		
public  static boolean validatecourses(String crse1,String crse2,String crse3) throws Exception{
	 Connection conn=null;
		conn=DBConnection.getDBConnection("mysql");
		conn.setAutoCommit(false);
 PreparedStatement stmt = null;
 ResultSet resultSet = null;
 boolean result=false;
 try
 {
 	stmt = conn.prepareStatement("select courseid,courseid,course3 from registerstudent where studentid=? and password=?");
 	stmt.setString(1, crse1);
 	stmt.setString(2, crse2);
 	stmt.setString(3, crse3);
     resultSet = stmt.executeQuery();
     conn.commit();
     if(resultSet.next()){
     	return true;
     }
     else{
     	return false;
 }
     
    
 }
 catch (Exception e)
 {
 	try{
 		conn.rollback();
 	}
 	catch(SQLException e1){
 	System.out.println("Exception occured");
 	e1.printStackTrace();
 	}
 }
 finally
 {
 	try{
 		conn.close();
 		resultSet.close(); } catch(Exception e){}
     try{
     	stmt.close();} catch(SQLException s){}
 }
	return result;

}

public static void deleteUser(String delUid,Connection conn) throws  SQLException
			    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("delete from users where user=?");
	            stmt.setString(1, delUid);
	            int rowsupdated=stmt.executeUpdate();
	        }
	        catch (SQLException e)
	        {
	        	throw e;
	        }
	        finally
	        {
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	     
	    }

public static boolean checkStudentIdandpwd(String stId,String pwd) throws Exception 
	    {
			//date="03/20/15"
			Connection conn=null;
			conn=DBConnection.getDBConnection("mysql");
		
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.prepareStatement("select studentid,password from registerstudent where studentid=? and password=?");
	            stmt.setString(1, stId);
	            stmt.setString(2, pwd);
	            resultSet = stmt.executeQuery();
	    		if(resultSet.next())
	    			return true;
	    			else
	    				return false;
	           
	        }
	        catch (SQLException e)
	        {
	        	System.out.println("Exception occired");
	        	e.printStackTrace();
	            throw e;
	        }
	        finally
	        {
	        	try{
	        		conn.close();
	        		resultSet.close(); } catch(SQLException s){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	        
	      
	    }

		
public static boolean checkStudentId(String stId) throws Exception 
	    {
			//date="03/20/15"
			Connection conn=null;
			conn=DBConnection.getDBConnection("mysql");
		
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.prepareStatement("select studentid from registerstudent where studentid=?");
	            stmt.setString(1, stId);
	            resultSet = stmt.executeQuery();
	    		if(resultSet.next())
	    			return true;
	    			else
	    				return false;
	           
	        }
	        catch (SQLException e)
	        {
	        	System.out.println("Exception occired");
	        	e.printStackTrace();
	            throw e;
	        }
	        finally
	        {
	        	try{
	        		conn.close();
	        		resultSet.close(); } catch(SQLException s){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	        
	      
	    }

public static boolean validateStudentId(String sId) throws Exception
	{
		Connection conn=null;
		conn=DBConnection.getDBConnection("mysql");
		conn.setAutoCommit(false);
		PreparedStatement stmt = null;
        ResultSet resultSet = null;
        boolean result=false;
		try{
			stmt = conn.prepareStatement("select studentid from registerstudent where studentid=? ");
			stmt.setString(1, sId);
			resultSet= stmt.executeQuery();
			 conn.commit();
			if(resultSet.next())
			return true;
			else
				return false;
			
			
		}
		catch (Exception e)
        {
        	try{
        		conn.rollback();
        	}
        	catch(SQLException e1){
        	System.out.println("Exception occured");
        	e1.printStackTrace();
        	}
        }
        finally
        {
        	try{
        		conn.close();
        		resultSet.close(); } catch(Exception e){}
            try{
            	stmt.close();} catch(SQLException s){}
        }
		return result;
	}

public static boolean validateLogin(String userid,String pwd) throws Exception 
	    {
			//date="03/20/15"
			  Connection conn=null;
				conn=DBConnection.getDBConnection("mysql");
				conn.setAutoCommit(false);
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.prepareStatement("select password from registerstudent where studentid=? and password=?");
	        	stmt.setString(1, userid);
	        	stmt.setString(2, pwd);
	            resultSet = stmt.executeQuery();
	            conn.commit();
	            if(resultSet.next()){
	            	return true;
	            }
	            else{
	            	return false;
	        }
	            
	           
	        }
	        catch (Exception e)
	        {
	        	try{
	        		conn.rollback();
	        	}
	        	catch(SQLException e1){
	        	System.out.println("Exception occured");
	        	e1.printStackTrace();
	        	}
	        }
	        finally
	        {
	        	try{
	        		conn.close();
	        		resultSet.close(); } catch(Exception e){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
			return result;
	    }
		
public static boolean fees(String stId,String sem) throws Exception{
			log.info("entered into getStudentCourses ");
			Connection conn=null;
			conn=DBConnection.getDBConnection("mysql"); 
		    PreparedStatement stmt = null;
		    ResultSet resultSet = null;
		    boolean result=false;
		    String courses[]=new String[3];
			try
		    {
				log.info("student id for getcourses"+stId);
				log.info("semester for getcourses"+sem);
		    	stmt = conn.prepareStatement("select course1,course2,course3 from registerstudent where (studentid=? and semester=?)");
		        stmt.setString(1, stId);
		        stmt.setString(2, sem);
		        resultSet = stmt.executeQuery();
		       if(resultSet.next()){ 
		        while (resultSet.next())
		        {
		        	courses[0]=resultSet.getString("course1");
		        	courses[1]=resultSet.getString("course2");
		        	courses[2]=resultSet.getString("course3");
		        	log.info(resultSet.getString("course1"));
		        	log.info(resultSet.getString("course2"));
		        	log.info(resultSet.getString("course3"));
		        }
		        return true;
		       }
		       
		       else{
		    	   return false;
		       }
		    }
			
			 catch (Exception e)
	        {
	        	try{
	        		conn.rollback();
	        	}
	        	catch(SQLException e1){
	        	System.out.println("Exception occured");
	        	e1.printStackTrace();
	        	}
	        }
	        finally
	        {
	        	try{
	        		conn.close();
	        		resultSet.close(); } catch(Exception e){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
		    log.info("string courses address : "+courses);
			return result;
	    }
		
public static void payment(String stId,String sem,String cnumber,String nameCard,String expDate, String cvv,String address) throws Exception{
	log.info("entered into courseReg ");
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql");
    PreparedStatement stmt = null;
    try
    {
    	
    	conn.setAutoCommit(false);
    	log.info("updating details ");
        stmt = conn.prepareStatement("insert into studentcarddetails (studentid,semester,cardnumber,nameoncard,expirationdate,cvv,address) values(?,?,?,?,?,?,?)");
        stmt.setString(1, stId);
        stmt.setString(2, sem);
        stmt.setString(3, cnumber);
        stmt.setString(4, nameCard);
        stmt.setString(5, expDate);
        stmt.setString(6, cvv);
        stmt.setString(7, address);
        log.info(stId);
        log.info(sem);
        log.info(cnumber);
        log.info(nameCard);
        log.info(expDate);
        log.info(cvv);
        log.info(address);
        stmt.executeUpdate();
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

public static void insertFeePaid(String stId,String sem) throws Exception{
	log.info("entered into insertFeePaid ");
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql");
    PreparedStatement stmt = null;
    try
    {
    	log.info("courses : "+stId+sem);
    	conn.setAutoCommit(false);
    	log.info("updating course ");
        stmt = conn.prepareStatement("update registerstudent set fees='paid' where studentid=? and semester=?");
        stmt.setString(1, stId);
        stmt.setString(2, sem);
        stmt.executeUpdate();
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

public static boolean validateFee(String stId,String sem,String fee) throws Exception{
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql");
	conn.setAutoCommit(false);
	PreparedStatement stmt = null;
    ResultSet resultSet = null;
    boolean result=false;
	try{
		stmt = conn.prepareStatement("select fees from registerstudent where studentid=? and semester=? ");
		stmt.setString(1, stId);
		stmt.setString(2, sem);
		resultSet= stmt.executeQuery();
		 conn.commit();
		if(resultSet.next())
		return true;
		else
			return false;
		
	}
	catch (Exception e)
    {
    	try{
    		conn.rollback();
    	}
    	catch(SQLException e1){
    	System.out.println("Exception occured");
    	e1.printStackTrace();
    	}
    }
    finally
    {
    	try{
    		conn.close();
    		resultSet.close(); } catch(Exception e){}
        try{
        	stmt.close();} catch(SQLException s){}
    }
	return result;
}

public static String getFees(String stId,String sem) throws Exception{
	log.info("entered into getFees ");
	Connection conn=null;
	conn=DBConnection.getDBConnection("mysql"); 
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    String fees=null;
	try
    {
		log.info("student id for getFees"+stId);
		log.info("semester for getFees"+sem);
    	stmt = conn.prepareStatement("select fees from registerstudent where (studentid=? and semester=?)");
        stmt.setString(1, stId);
        stmt.setString(2, sem);
        resultSet = stmt.executeQuery();
        
        while (resultSet.next())
        {
        	fees=resultSet.getString("fees");
        	
        	log.info(fees);
        }
        
        log.info("resultset address : "+resultSet);
    }
    catch (SQLException e)
    {
    	System.out.println("Exception occired");
    	e.printStackTrace();
        throw e;
    }
    finally
    {
    	try{
    		conn.close();
    		resultSet.close();} catch(SQLException s){}
        try{
        	stmt.close();} catch(SQLException s){}
    }
    
	return fees;
}


}
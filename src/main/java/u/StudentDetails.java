package u;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDetails {

		public static void studentregister(String studentid,String fname,String lname,String password,Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("insert into studentdetails (studentid,firstname,lastname,password) values (?,?,?,? )");
	            stmt.setString(1, studentid);
	            stmt.setString(2, fname);
	            stmt.setString(3, lname);
	            stmt.setString(4, password);
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

		
		//updateOrder("john","new_pasword")
		public static void updatePwd(String uid,String pwd, Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("update users set password=? where user=?");
	            stmt.setString(1, pwd);
	            stmt.setString(2, uid);
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

		public static boolean getUsersByCopmany(String company,Connection conn) throws SQLException 
	    {
			//date="03/20/15"
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.prepareStatement("select user from users where company=?");
	            stmt.setString(1, company);
	            resultSet = stmt.executeQuery();
	            while (resultSet.next())
	            {
	            	System.out.println(resultSet.getString("user"));
	            }
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
	        		resultSet.close(); } catch(SQLException s){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	        
	        return result;
	    }

	

		public static boolean validateLogin(String userid,String pwd,Connection conn) throws SQLException 
	    {
			//date="03/20/15"
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.prepareStatement("select password from users where user=? and password=?");
	        	stmt.setString(1, userid);
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
	        		resultSet.close(); } catch(SQLException s){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	        
	    }
}

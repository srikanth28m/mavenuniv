package u;

import java.sql.Connection;

import u.DBConnection;
import u.StudentDetails;

public class TestDBCalls {

	public static void main(String args[]) throws Exception
	{
		
		Connection conn=DBConnection.getDBConnection("university");
		try{
		conn.setAutoCommit(false);
		//UsersTable.insertUser("javaclient111", "javaclient111", "java", conn);
		//UsersTable.updatePwd("javaclient", "new_password", conn);
		//UsersTable.deleteUser("javaclient", conn);
		StudentDetails.studentregister("1003", "rishab", "ris", "rishab", conn);
		conn.commit();
		}catch(Exception e)
		{
			conn.rollback();
		}
		finally{
			try{
				conn.close();
			}catch(Exception e){}
		}
	}
}

package u;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import u.DBConnection;
import u.UsersTable;

public class Stlogin extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		loginProcess(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		loginProcess(req,res);
	}
	
	public void loginProcess(HttpServletRequest req,HttpServletResponse res)
	{
		String checkLoginStudentId=req.getParameter("loginstudentid");
		Connection conn=null;

		try{
			conn=DBConnection.getDBConnection("mysql");
			conn.setAutoCommit(false);
	
			checkLoginStudentIdprocess(req, res);		
			String studentid=req.getParameter("id");
			String password=req.getParameter("pswd");
			String semester=req.getParameter("semester");
			System.out.println(studentid);
			System.out.println(password);
			System.out.println(semester);
			boolean isValid=UsersTable.validateLogin(studentid, password,semester, conn);
			if(isValid)
				res.sendRedirect("studentportal.html");
			else
				res.sendRedirect("error.html");
			
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
		
			/*try{
			String checkLoginStudentId=req.getParameter("loginstudentid");
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
			if(checkLoginStudentId.equalsIgnoreCase("1001")){
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
			String userid=req.getParameter("name");
			String password=req.getParameter("pswd");
			String semster=req.getParameter("semester");
			System.out.println(userid);
			System.out.println(password);
			if((userid.equalsIgnoreCase("1001") && password.equalsIgnoreCase("jee")) || semster.equals("Semester 1"))
				res.sendRedirect("studentportal.html");
			else
				res.sendRedirect("error.html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	
	public void checkLoginStudentIdprocess(HttpServletRequest req,HttpServletResponse res) throws IOException, SQLException{
		String checkLoginStudentId=req.getParameter("loginstudentid");
		Connection conn=null;
		try{
		if(checkLoginStudentId!=null){
			conn=DBConnection.getDBConnection("mysql");
			conn.setAutoCommit(false);
			
		
			String jsonSucessResponse="";
			jsonSucessResponse+="{ \n";
			jsonSucessResponse+="\"studentid_check\":\"exists\"\n";
			jsonSucessResponse+="}\n";
			
			String jsonFailedResponse="";
			jsonFailedResponse+="{ \n";
			jsonFailedResponse+="\"studentid_check\":\"not_exists\"\n";
			jsonFailedResponse+="}\n";
			
			res.setContentType("application/json");
			boolean isValid=UsersTable.validateStudentId(checkLoginStudentId, conn);
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
	
	
}










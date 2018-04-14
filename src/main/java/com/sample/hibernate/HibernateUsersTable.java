package com.sample.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sample.dao.UsersEntity;


public class HibernateUsersTable {

	static SessionFactory session=DBHibernateSession.factory;
		public static void insertFacultyHibernate(String facultyid,String firstname, String lastname,String department,String password,SessionFactory factory) throws  SQLException
	    {
			  Session session = factory.openSession();
		      Transaction tx = null;
		      String facultyuserid= null;
		      try{
		         tx = session.beginTransaction();
		         UsersEntity faculty = new UsersEntity (facultyid,firstname,lastname, department, password);
		         facultyuserid = (String) session.save(faculty);
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
	    }
		
 		public static void updatePwdHibernate(String uid,String pwd, SessionFactory factory) throws  SQLException
	    {
		      Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         
		         UsersEntity employee =  (UsersEntity)session.get(UsersEntity.class, uid); 
		         if(employee!=null){
			         employee.setPwd(pwd);
					 session.update(employee); 			          
		         }
				 tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }

	    }
		

 		public static void deleteUserHibernate(String delUid,SessionFactory factory) throws  SQLException
	    {
			      Session session = factory.openSession();
			      Transaction tx = null;
			      try{
			         tx = session.beginTransaction();
			         UsersEntity employee = new UsersEntity(delUid);
			         session.delete(employee); 
			         tx.commit();
			      }catch (HibernateException e) {
			         if (tx!=null) tx.rollback();
			         e.printStackTrace(); 
			      }finally {
			         session.close(); 
			      }
	    }
 		public static UsersEntity getDetailsByUseridHibernate(String userid,SessionFactory factory) throws SQLException 
	    {
			Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         Object result=session.get(UsersEntity.class, userid);
		         
		         if(result==null)
		        	 return null; // -1 if recrd not found
		         UsersEntity employee = (UsersEntity) result;
		         return employee;
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         return null;// -2 if internal hibernate error.
		      }finally {
		         session.close(); 
		      }
	    }
	
 
 		public static boolean validateLoginHibCriteria(String facultyid,String password,String department) throws SQLException 
	    {
			try
	        {
				System.out.println("entered in try block "+facultyid+password+department);
				Session sf = session.openSession();
				sf.beginTransaction();
				Criteria criteria = sf.createCriteria(UsersEntity.class);
				criteria.add(Restrictions.eq("fid", facultyid));
				
				criteria.add(Restrictions.eq("pwd", password));
				criteria.add(Restrictions.eq("dep", department));
				UsersEntity faculty = (UsersEntity ) criteria.uniqueResult();
				sf.getTransaction().commit();
 				if(faculty!=null){
				System.out.println("user found : "+true);
 				return true;
 				}
 				else{
 					System.out.println("user found : "+false);
					return false;
 				}
 			}
	        catch (Exception e)
	        {
	        	System.out.println("Exception occired");
	        	e.printStackTrace();
	            try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
			return false;
	    }
 		// list similar to executeQuery in sql
 		public static boolean validateLoginHibernateHQuery(String facultyid,String password,String department,SessionFactory sf) throws Exception 
	    {
			try
	        {
				Session session = sf.openSession();
				session.beginTransaction();
				// FROM com.sample.dao.UsersEntity empl where empl.uid= and empl.pwd=
				String hql = "FROM com.sample.dao.UsersEntity E WHERE E.fid= :inputfacultyid and E.pwd= :inputPass and E.dep= :inputdept";
				Query query = session.createQuery("FROM UsersEntity E WHERE E.fid= :inputfacultyid and E.pwd= :inputPass and E.dep= :inputdept");
				query.setParameter("inputfacultyid", facultyid);
				query.setParameter("inputPass", password);
				query.setParameter("inputdept", department);
				List<UsersEntity> results = query.list();
				
				if(results==null || results.size()==0)
					return false;
				else
					return true;
	        }
	        catch (Exception e)
	        {
	        	System.out.println("Exception occired");
	        	e.printStackTrace();
	            throw e;
	        }
	        finally
	        {
	        }
	    }

 		//executeUpdate similar to sql executeUpdate used for update,delete,insert
 		public static void updatePwdHibernateHquery(String uid,String pwd, SessionFactory factory) throws  SQLException
	    {
		      Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         
		         Query query=session.createQuery("update UsersEntity set pwd = :inputPwd where uid = :inputUser");
		         query.setParameter("inputUser", uid);
		         query.setParameter("inputPwd", pwd);
				 int result = query.executeUpdate();
					
				 tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }

	    }

}

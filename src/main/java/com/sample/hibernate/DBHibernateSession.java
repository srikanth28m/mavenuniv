package com.sample.hibernate;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class DBHibernateSession {

	// tomcat server configuration
	public static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	// stand alone db call tests
//	public static SessionFactory factory = new  Configuration().configure(new File("C:/Users/sreekanth/workspace/university/config/hibernate.cfg.xml"))
//			.buildSessionFactory();

}

package com.sample.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registerfaculty")
public class UsersEntity {

	@Id
	@Column(name="facultyid")
	String fid;
	@Column(name="firstname")
	String fname;	
	@Column(name="lastname")
	String lname;
	@Column(name="department")
	String dep;
	@Column(name="password")
	String pwd;
	// data types : int->integer/long , string->varchar/clob, blob or byte[]->blob
	public UsersEntity(String fid,String fname,String lname,String dep,String pwd)
	{
		this.fid=fid;
		this.fname=fname;
		this.lname=lname;
		this.dep=dep;
		this.pwd=pwd;
	}
	// uid is unqiue key in the table, need atleast a constructor with uid only.
	public UsersEntity(String fid)
	{
		this.fid=fid;
	}
	public UsersEntity()
	{
	}
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String toString()
	{
		return "facultyid:"+fid+",firstname:"+fname+",lastname:"+lname+",department:"+dep+",password:"+pwd;
	}

}

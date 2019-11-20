package com.faith.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDao implements ILoginDao {
	
	//setting Jdbc template
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
	this.template = template;
	}
	
	//verifying username and password
	@Override
	public boolean verifyUser(String username,String password )
	{
		boolean b;
		String sql = "select username,password from f_loginTable where username=? and  password=?";
		int noOfRows=template.update(sql, new Object[] {username,password});
		if(noOfRows==1)
		{b=true;}
		else 
		{b=false;}
		return b;		
	}
}

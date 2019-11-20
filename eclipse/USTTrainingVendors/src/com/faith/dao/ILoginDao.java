package com.faith.dao;

public interface ILoginDao {

	//verifying username and password
	public abstract boolean verifyUser(String username, String password);

}
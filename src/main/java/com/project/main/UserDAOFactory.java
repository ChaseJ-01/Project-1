package com.project.main;

public class UserDAOFactory {
	private static UserDAO dao;
	
	public static UserDAO getUserDAO() {
		if(dao==null) {
			dao = new UserDAOImpl();
		}
		return dao;
	}
}

package com.project.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
	private Connection connection = null;
	
	public UserDAOImpl() {
		try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
	}
	
	@Override
	public void addUser(User user) throws SQLException {
		String sql = "INSERT INTO Users (first_name, last_name, email, hash, salt, type) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.email);
        ps.setString(4, user.hash);
        ps.setString(5, user.salt);
        ps.setString(6, user.type);
        int count = ps.executeUpdate();
        if(count > 0)
            System.out.println("User saved");
        else
            System.out.println("Oops! something went wrong");
	}
	
	public User getUserByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM Users WHERE email = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}
		return null;
	}
	
	@Override
	public User loginUser(String email, String password) throws SQLException {
		User user = getUserByEmail(email);
		if(user == null) {
			return null;
		}
        if(PasswordManager.getPasswordManager().getHash(password, user.getSalt()).equals(user.getHash())) {
        	return user;
        }
		
		return null;
	}

}

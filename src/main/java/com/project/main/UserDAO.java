package com.project.main;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	void addUser(User user) throws SQLException;
	User loginUser(String email, String hash) throws SQLException;
}

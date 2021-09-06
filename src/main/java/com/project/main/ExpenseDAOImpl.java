package com.project.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ExpenseDAOImpl implements ExpenseDAO {
	private static Statement statement = null;
    Connection connection = null;

    public ExpenseDAOImpl()  {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO Expenses (employeeId, amount, type, status, description) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, expense.getEmployeeID());
        preparedStatement.setDouble(2, expense.getRequestAmount());
        preparedStatement.setString(3, expense.getRequestType());
        preparedStatement.setString(4, expense.getRequestStatus());
        preparedStatement.setString(5, expense.getRequestDescription());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense saved");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public void updateExpense(Expense expense) throws SQLException {
    	//TODO fix this
        String sql = "UPDATE Expenses SET amount = ?, type = ?, status = ?, description = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, expense.getRequestAmount());
        preparedStatement.setString(2, expense.getRequestType());
        preparedStatement.setString(3, expense.getRequestStatus());
        preparedStatement.setString(4, expense.getRequestDescription());
        preparedStatement.setInt(5, expense.getRequestId());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense updated");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM Expenses WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense delete");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public List<Expense> getAllExpenses(boolean pending) throws SQLException {
    	String sql;
    	if(pending) {
    		sql = "SELECT * FROM Expenses WHERE status = 'Pending'";
    	} else {
    		sql = "SELECT * FROM Expenses";
    	}
    	statement = connection.createStatement();
    	ResultSet rs = statement.executeQuery(sql);
    	List<Expense> expenseList = new ArrayList<Expense>();
    	
    	while( rs.next() ) {
    		expenseList.add(new Expense(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
    	}
    	
    	return expenseList;
    }

    @Override
    public Expense getExpenseById(int id) throws SQLException {
    	String sql = "SELECT * FROM Expenses WHERE id = ?";
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setInt(1, id);
    	ResultSet rs = ps.executeQuery();
    	
    	if( rs.next() ) {
    		return new Expense(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
    	}
    	
        return null;
    }
    
    @Override
    public List<Expense> getExpenseByEmployee(int employeeID, boolean pending) throws SQLException {
    	String sql = "SELECT * FROM Expenses WHERE employeeId = ?";
    	if(pending) {
    		sql = sql + " AND status = 'Pending'";
    	}
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		List<Expense> expenseList = new ArrayList<Expense>();
    	
    	while( rs.next() ) {
    		expenseList.add(new Expense(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
    	}
    	
    	return expenseList;
    }
    
    @Override
    public User getUserByExpenseId(int id) throws SQLException{
    	Expense expense = getExpenseById(id);
    	
    	String sql = "SELECT * FROM Users WHERE id = ?";
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setInt(1, expense.getEmployeeID());
    	ResultSet rs = ps.executeQuery();
    	
    	if( rs.next() ) {
    		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
    	}
    	
        return null;
    }
}

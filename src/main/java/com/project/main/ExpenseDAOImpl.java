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
        String sql = "INSERT INTO Expense (expenseAmount, expenseType,"
        		+ "expenseStatus, expenseDescription) values "
        		+ "(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, expense.getRequestAmount());
        preparedStatement.setInt(2, expense.getRequestType());
        preparedStatement.setBoolean(3, expense.getRequestStatus());
        preparedStatement.setString(4, expense.getRequestDescription());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense saved");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public void updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE Expense SET requestAmount = ?,"
        		+ "requestType = ?, requestStatus = ?, "
        		+ "requestDescription = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, expense.getRequestAmount());
        preparedStatement.setInt(2, expense.getRequestType());
        preparedStatement.setBoolean(3, expense.getRequestStatus());
        preparedStatement.setString(4, expense.getRequestDescription());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense updated");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM Expense WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Expense delete");
        else
            System.out.println("Oops! something went wrong");
    }

    @Override
    public List<Expense> getAllExpenses() throws SQLException {
    	int expseID = -1;
    	double amount = 0.0;
    	int type = -1;
    	boolean status = false;
    	String description = "";
    	
    	String sql = "SELECT id, requestAmount, requestType,"
    			+ "requestStatus, requestDescription "
    			+ "FROM Expenses";
    	
    	statement = connection.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	List<Expense> expenseList = new ArrayList<Expense>();
    	
    	while( resultSet.next() ) {
    		expseID = resultSet.getInt(0);
    		amount = resultSet.getDouble(1);
    		type = resultSet.getInt(2);
    		status = resultSet.getBoolean(3);
    		description = resultSet.getString(4);
    		
    		expenseList.add(new Expense(expseID, amount, type,
    				status, description));
    	}
    	
    	return expenseList;
    }

    @Override
    public Expense getExpenseById(int id) throws SQLException {
    	int expseID = -1;
    	double amount = 0.0;
    	int type = -1;
    	boolean status = false;
    	String description = "";
    	Expense expense = null;
    	
    	String sql = "SELECT id, requestAmount, requestType,"
    			+ "requestStatus, requestDescription "
    			+ "FROM Expenses WHERE id = " + id;
    	Statement statement = connection.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	if( resultSet.next() ) {
    		expseID = resultSet.getInt(0);
    		amount = resultSet.getDouble(1);
    		type = resultSet.getInt(2);
    		status = resultSet.getBoolean(3);
    		description = resultSet.getString(4);
    		expense = new Expense(expseID,
    				amount, type, status,
    				description);
    		
    	}
        return expense;
    }
}

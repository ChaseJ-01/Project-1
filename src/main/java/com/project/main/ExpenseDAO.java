package com.project.main;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseDAO {
	void addExpense(Expense expense) throws SQLException;
    void updateExpense(Expense expense) throws SQLException;
    void deleteExpense(int id) throws SQLException;
    Expense getExpenseById(int id) throws SQLException;
	List<Expense> getExpenseByEmployee(int employeeID, boolean pending) throws SQLException;
	List<Expense> getAllExpenses(boolean pending) throws SQLException;
	User getUserByExpenseId(int id) throws SQLException;
}

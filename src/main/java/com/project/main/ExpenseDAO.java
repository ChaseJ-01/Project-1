package com.project.main;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseDAO {
	void addExpense(Expense expense) throws SQLException;
    void updateExpense(Expense expense) throws SQLException;
    void deleteExpense(int id) throws SQLException;
    List<Expense> getAllExpenses() throws SQLException;
    Expense getExpenseById(int id) throws SQLException;
}

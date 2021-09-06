package com.project.servlets;

import java.io.PrintWriter;

import com.project.main.Expense;
import com.project.main.ExpenseDAO;
import com.project.main.ExpenseDAOFactory;
import com.project.main.User;
import com.project.main.UserDAO;
import com.project.main.UserDAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter out = res.getWriter()){
			HttpSession ses = req.getSession();
			ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			int user_id = 0;
			if(ses.getAttribute("user_id") != null) {
				user_id = Integer.parseInt(ses.getAttribute("user_id").toString());
			}
			
			Expense expense = dao.getExpenseById(Integer.parseInt(req.getParameter("id")));
			if(user_id == expense.getEmployeeID()) {
				dao.deleteExpense(expense.getRequestId());
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

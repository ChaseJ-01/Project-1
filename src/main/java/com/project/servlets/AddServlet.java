package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.project.main.Expense;
import com.project.main.ExpenseDAO;
import com.project.main.ExpenseDAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		
		try {
			HttpSession ses = req.getSession(false);
			ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			Expense expense;
			
			int id = 0;
			int userId = Integer.parseInt(ses.getAttribute("user_id").toString());
			
			if(req.getParameter("id") != null) {
				id = Integer.parseInt(req.getParameter("id"));
				if(req.getParameter("newStatus") != null && ses.getAttribute("user_type").equals("Manager")) {
					expense = dao.getExpenseById(id);
					if(req.getParameter("newStatus").equals("Approved")) {
						expense.setRequestStatus("Approved");
						dao.updateExpense(expense);
					} else if (req.getParameter("newStatus").equals("Rejected")) {
						expense.setRequestStatus("Rejected");
						dao.updateExpense(expense);
					}
				} else {
					expense = new Expense(id, userId, Double.parseDouble(req.getParameter("request_amount").toString()), req.getParameter("request_type"), "Pending", req.getParameter("request_desc"), "");
					dao.updateExpense(expense);
				}
			} else {
				expense = new Expense(id, userId, Double.parseDouble(req.getParameter("request_amount").toString()), req.getParameter("request_type"), "Pending", req.getParameter("request_desc"), "");
				dao.addExpense(expense);
			}
			res.sendRedirect("profile");
			
		} catch (Exception e) { //TODO change exception type
			e.printStackTrace();
		}
	}
	
	
}

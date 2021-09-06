package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.main.Expense;
import com.project.main.ExpenseDAO;
import com.project.main.ExpenseDAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TableServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		
		try(PrintWriter out = res.getWriter()){
			List<Expense> requestList = new ArrayList<Expense>();
			HttpSession ses = req.getSession(false);
			
			ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			String filter = req.getParameter("filter");
			
			if(filter.equals("all") && ses.getAttribute("user_type").equals("Manager")) {
				requestList = dao.getAllExpenses(false);
			} else if(filter.equals("pending") && ses.getAttribute("user_type").equals("Manager")) {
				requestList = dao.getAllExpenses(true);
			} else if(filter.equals("all") && ses.getAttribute("user_type").equals("Employee")) {
				requestList = dao.getExpenseByEmployee(Integer.parseInt(ses.getAttribute("user_id").toString()), false);
			} else if(filter.equals("pending") && ses.getAttribute("user_type").equals("Employee")) {
				requestList = dao.getExpenseByEmployee(Integer.parseInt(ses.getAttribute("user_id").toString()), true);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("/logout");
				rd.forward(req, res);
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/table.html");
			rd.include(req, res);
			
			out.println("<script language='Javascript'>");
			for(Expense r : requestList) {
				out.println("addRequest(" + r.getRequestId() + ",'" + dao.getUserByExpenseId(r.getRequestId()).getLastName() + "'," + r.getRequestAmount() + ",'" + r.getRequestType() + "','" + r.getRequestStatus() + "','" + r.getRequestTimestamp() + "');");
			}
			out.println("populate()");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

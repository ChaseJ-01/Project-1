package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.project.main.Expense;
import com.project.main.ExpenseDAO;
import com.project.main.ExpenseDAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		
		try(PrintWriter out = res.getWriter()){
			Expense request = null; //TODO Fill this list
			HttpSession ses = req.getSession();
			
			ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			int idParam = Integer.parseInt(req.getParameter("id"));
			if(ses.getAttribute("user_type") == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.forward(req, res);
			} else if(ses.getAttribute("user_type").equals("Employee")) {
				RequestDispatcher rd = req.getRequestDispatcher("/details-employee.html");
				rd.include(req, res);
			} else if(ses.getAttribute("user_type").equals("Manager")) {
				RequestDispatcher rd = req.getRequestDispatcher("/details.html");
				rd.include(req, res);
			}
			
			Expense r = dao.getExpenseById(Integer.parseInt(req.getParameter("id")));
			
			out.println("<script language='Javascript'>");
			out.println("fill(" + r.getRequestId() + ",'" + dao.getUserByExpenseId(r.getRequestId()).getName() + "'," + r.getRequestAmount() + ",'" + r.getRequestType() + "','" + r.getRequestStatus() + "','" + r.getRequestTimestamp() + "','" + r.getRequestDescription() + "');");
			out.println("</script>");
		} catch (Exception e) { //TODO change exception type
			e.printStackTrace();
		}
	}
}

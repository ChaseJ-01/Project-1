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

public class UpdateServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		
		try(PrintWriter out = res.getWriter()){
			Expense request = null; //TODO Fill this list
			HttpSession ses = req.getSession();
			
			ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			if(ses.getAttribute("user_type") == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/logout");
				rd.forward(req, res);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("request-form.html");
				rd.include(req, res);
			}
			
			Expense r = dao.getExpenseById(Integer.parseInt(req.getParameter("id")));
			
			out.println("<script language='Javascript'>");
			out.println("fill(" + r.getRequestId() +  "," + r.getRequestAmount() + ",'" + r.getRequestType() + "','" + r.getRequestDescription() + "');");
			out.println("</script>");
		} catch (Exception e) { //TODO change exception type
			e.printStackTrace();
		}
	}
}

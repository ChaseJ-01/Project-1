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
			RequestDispatcher rd = req.getRequestDispatcher("/details.html");
			rd.include(req, res);
			//ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			int idParam = Integer.parseInt(req.getParameter("id"));
			
			//TODO Use this filter parameter to determine what elements to display.
			//If filter.equals("all") then display all. If filter.equals("pending") then display all pending.
			//If filter.equals("employee") then grab user_id from the session and display only that user's requests.
			//If filter.equals("epending") then grab user_id and display only pending requests for that user.
			
			/*if(filter.equals("all")) {
				request = dao.getRequestById();
			}*/
			
			Expense r = new Expense(1, 500.00, 3, true, "pergle");
			
			if(idParam == 5 ) {
				r = new Expense(5, 500.00, 3, true, "pergle");
			} else if (idParam == 6) {
				r = new Expense(6, 500.00, 2, false, "blarf");
			}
			
			
			//Writes some Javascript that takes an RRequest object array and populates a table.
			//TODO change the getUserID call to instead get employee's name.
			out.println("<script language='Javascript'>");
			out.println("fill(" + r.getRequestId() + ",'" + "Mark" /*TODO r.getUserId()*/ + "'," + r.getRequestAmount() + ",'" + selectType(r.getRequestType()) + "','" + selectStatus(r.getRequestStatus()) + "','" + "10/10/2020" + "','" + r.getRequestDescription() + "');");
			out.println("</script>");
		} catch (Exception e) { //TODO change exception type
			e.printStackTrace();
		}
	}
	
	private String selectType(int i) {
		switch(i) {
			case 0: return "Travel";
			case 1: return "Food";
			case 2: return "Lodging";
			default: return "Other";
		}
	}
	
	private String selectStatus(boolean status) {
		return status ? "Approved" : "Pending";
	}
}

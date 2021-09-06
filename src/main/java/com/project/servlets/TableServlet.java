package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.main.Expense;
import com.project.main.ExpenseDAO;
import com.project.main.ExpenseDAOFactory;
import com.project.main.RRequest;

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
			List<Expense> requestList = new ArrayList<Expense>(); //TODO Fill this list
			HttpSession ses = req.getSession();
			RequestDispatcher rd = req.getRequestDispatcher("/table.html");
			rd.include(req, res);
			//ExpenseDAO dao = ExpenseDAOFactory.getExpenseDao();
			
			String filter = req.getParameter("filter");
			
			//TODO Use this filter parameter to determine what elements to display.
			//If filter.equals("all") then display all. If filter.equals("pending") then display all pending.
			//If filter.equals("employee") then grab user_id from the session and display only that user's requests.
			//If filter.equals("epending") then grab user_id and display only pending requests for that user.
			
			/*if(filter.equals("all")) {
				requestList = dao.getAllExpenses();
			}*/
			
			requestList.add(new Expense(5, 500.00, 3, true, "pergle"));
			requestList.add(new Expense(6, 500.00, 2, false, "blarf"));
			
			//Writes some Javascript that takes an RRequest object array and populates a table.
			//TODO change the getUserID call to instead get employee's name.
			out.println("<script language='Javascript'>");
			for(Expense r : requestList) {
				out.println("addRequest(" + r.getRequestId() + ",'" + "Mark" /*TODO r.getUserId()*/ + "'," + r.getRequestAmount() + ",'" + selectType(r.getRequestType()) + "','" + selectStatus(r.getRequestStatus()) + "','" + "10/10/2020" + "');");
			}
			out.println("populate()");
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

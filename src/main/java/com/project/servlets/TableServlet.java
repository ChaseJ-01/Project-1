package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.main.RRequest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TableServlet extends HttpServlet{
	public List<RRequest> requests;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		
		try(PrintWriter out = res.getWriter()){
			List<RRequest> requestList = new ArrayList<RRequest>(); //TODO Fill this list
			HttpSession ses = req.getSession();
			RequestDispatcher rd = req.getRequestDispatcher("/table.html");
			rd.include(req, res);
			
			String filter = req.getParameter("filter");
			
			//TODO Use this filter parameter to determine what elements to display.
			//If filter.equals("all") then display all. If filter.equals("pending") then display all pending.
			//If filter.equals("employee") then grab user_id from the session and display only that user's requests.
			//If filter.equals("epending") then grab user_id and display only pending requests for that user.
			
			if(filter.equals("epending")) {
				System.out.println("Employee ID: " + ses.getAttribute("user_id")); //TODO remove this line
			}
			
			//This is example code, feel free to delete it.
			requestList.add(new RRequest(1, 1, 500.00, "Lodging", "Pending", "Needed a hotel."));
			requestList.add(new RRequest(2, 2, 30.00, "Food", "Approved", "Needed a meal."));
			
			//Writes some Javascript that takes an RRequest object array and populates a table.
			//TODO change the getUserID call to instead get employee's name.
			out.println("<script language='Javascript'>");
			for(RRequest r : requestList) {
				out.println("addRequest(" + r.getId() + ",'" + r.getUserId() + "'," + r.getAmount() + ",'" + r.getType() + "','" + r.getStatus() + "');");
			}
			out.println("populate()");
			out.println("</script>");
		} catch (Exception e) { //TODO change exception type
			e.printStackTrace();
		}
	}
}

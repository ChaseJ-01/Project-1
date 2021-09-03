package com.project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.project1.main.RRequest;

public class TableServlet extends HttpServlet{
	public List<RRequest> requests;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			List<RRequest> requestList = null; //Fill this list
			HttpSession ses = req.getSession();
			DAO dao = null; //DAO object, for grabbing entries from both tables
			
			out.print("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>Employees</title><link rel=\"stylesheet\" href=\"style.css\"></head><body>");
			
			out.print("<thead><tr><td>ID</td><td>Name</td><td>Email</td><td>Gender</td><td>Country</td><td></td><td></td></tr></thead><tbody>");
			if(requestList != null) {
				for(RRequest e : requestList) {
					out.print("<tr><td>" + e.getId() + "</td><td>" + dom.getUserByID(e.getUserId()).getName() + "</td><td>" + e.getAmount() + "</td><td>" + e.getType() + "</td><td>" + e.getStatus() + "</td>");
					out.print("<td><a href='details?id=" + e.getId() + "'>Details<a></td>");
					out.print("</tr>");
				}
			}
			
			out.print("</tbody></table></div></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

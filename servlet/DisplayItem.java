package webtest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.model.MenuItem;
import webtest.service.DbService;

@WebServlet("/DisplayItem")
public class DisplayItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayItem() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("item_id")); // grabs item_id from displayhomepage.jsp
		DbService dbService = new DbService();
		request.setAttribute("menuItem", dbService.getMenuItem(itemId));
		dbService.close();
		request.getRequestDispatcher("/WEB-INF/DisplayItem.jsp").forward(request, response); // display the home page
																								// view
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
	}
		
	}


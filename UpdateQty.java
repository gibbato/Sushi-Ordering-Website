package webtest.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webtest.model.MenuItem;
import webtest.model.Order;
import webtest.model.SelectedItem;
import webtest.model.User;
import webtest.service.DbService;

@WebServlet("/UpdateQty")
public class UpdateQty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateQty() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int qty = Integer.parseInt(request.getParameter("qty"));

		DbService dbService = new DbService();

		HttpSession session = request.getSession();

		String userEmail = (String) session.getAttribute("loggedInUser");
		SelectedItem selectedItem = new SelectedItem(dbService.getMenuItem(id), qty);

		// IF DBSERVICE.GETUSERID < 1, OUTPUT ERROR. THIS WOULD MEAN THE ITEMS ARE NOT
		// BEING INPUT IN THE CORRECT CART

		int userId = dbService.getUserID(userEmail);

		dbService.addToCart(selectedItem, userId);

		dbService.close();

		response.sendRedirect("Menu");

	}
}
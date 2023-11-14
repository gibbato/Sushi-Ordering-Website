package webtest.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webtest.model.MenuItem;
import webtest.model.Order;
import webtest.model.SelectedItem;
import webtest.service.DbService;

@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShoppingCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService dbService = new DbService();
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUser");
		int userId = dbService.getUserID(userEmail);
		Order order = new Order(dbService.getCart(userId));
		request.setAttribute("selectedList", order.getSelectedItemList());
		request.setAttribute("order", order);
		dbService.close();
		request.getRequestDispatcher("/WEB-INF/ShoppingCart.jsp").forward(request, response);
	}

}

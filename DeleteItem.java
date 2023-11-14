package webtest.servlet;

import java.io.IOException;
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
import webtest.service.DbService;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteItem() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int menuId = Integer.parseInt(request.getParameter("id"));
		
		DbService dbService = new DbService();
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUser");
		int userId = dbService.getUserID(userEmail);
		Order order = new Order(dbService.getCart(userId));
		
		dbService.deleteItemFromCart(order, menuId, userId);
		dbService.close();
		response.sendRedirect("ShoppingCart");
	}

}
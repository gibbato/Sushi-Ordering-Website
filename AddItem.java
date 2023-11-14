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

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItem() {
        super();
    }
    
 
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int itemId = Integer.parseInt(request.getParameter("item_id"));
    	DbService dbService = new DbService();
    	request.setAttribute("menuItem", dbService.getMenuItem(itemId));
    	dbService.close();
		request.getRequestDispatcher("/WEB-INF/AddItem.jsp").forward(request, response);
    }
}
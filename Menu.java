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

import webtest.model.MenuItem;
import webtest.model.Order;
import webtest.model.SelectedItem;
import webtest.service.DbService;

@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Menu() {
        super();
    }
    
   
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DbService dbService = new DbService();
		request.setAttribute("menuList", dbService.getMenuItems());
		dbService.close();
    	request.getRequestDispatcher("/WEB-INF/Menu.jsp").forward(request, response);
    }
}
package webtest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.model.MenuItem;


@WebServlet("/SushiMenu")
public class SushiMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SushiMenu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<MenuItem> menuList = (List<MenuItem>) getServletContext().getAttribute("menuList");
		request.getRequestDispatcher("/WEB-INF/sushimenu.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

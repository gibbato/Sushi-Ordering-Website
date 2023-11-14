package webtest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.model.MenuItem;


@WebServlet("/SashimiMenu")
public class SashimiMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SashimiMenu() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<MenuItem> menuList = (List<MenuItem>) getServletContext().getAttribute("menuList");
		request.getRequestDispatcher("/WEB-INF/sashimimenu.jsp").forward(request, response);    //display the home page view
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

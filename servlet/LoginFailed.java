package webtest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginFailed")
public class LoginFailed extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginFailed() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	    // set the email address as an attribute in the request object
    	    // forward the request to the JSP page
    	    request.getRequestDispatcher("/WEB-INF/LoginFailed.jsp").forward(request, response);
    	  }

    	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	    // create new account using email address
    		String email = request.getParameter("email");
    	    response.sendRedirect("/WEB-INF/Register.jsp?email=" + email);
    	  }
}

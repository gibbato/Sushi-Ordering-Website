package webtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactConfirmed")
public class ContactConfirmed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactConfirmed() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the content type
		response.setContentType("text/html");
		// get the printwriter
		PrintWriter out = response.getWriter();
		
		//generate content
		out.println("<html><body>");
		out.println("Thank you " + request.getParameter("firstname")+ ", we will reach out to you soon.");
		
		out.println("<br></br><a  href=\"DisplayHomepage\" class=\"btn btn-primary\">Back to Homepage</a>");
		out.println("</body></html>");
	}
}
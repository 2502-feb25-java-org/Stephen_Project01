package expenseReimbursement.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet{
	private static Logger log = Logger.getLogger(LoadViewServlet.class);
	
	//Override the doGet method. (type doGet, then ctrl+space
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("REQUEST SENT TO URI: " + req.getRequestURI());	//to check which URI has been requested
		
		//parse the URI for the resource:
		String uri = req.getRequestURI();
		String name = uri.substring(22,(uri.length()-5));	//substring resource
		log.info("CHECKING THE URI "+ name);
		req.getRequestDispatcher("partials/"+name+".html").forward(req,resp);	
		//forwards the req/resp to another servlet
	}
	
}

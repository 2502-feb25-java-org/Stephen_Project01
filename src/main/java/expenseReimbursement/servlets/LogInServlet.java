package expenseReimbursement.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import expenseReimbursement.model.User;
import expenseReimbursement.service.UserService;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/Login")
public class LogInServlet extends HttpServlet {
	
    private static Logger log = Logger.getLogger(LogInServlet.class);	//logger
    static UserService service = new UserService();
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet Method called!");
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {	//user is logged in, redirect.
			log.info("user is logged in!");
			User u = (User) session.getAttribute("user");
			response.sendRedirect("landing");
			session.removeAttribute("user");
			session.invalidate();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		log.info("EXECUTING LOG IN SERVLET");
		ObjectMapper mapper = new ObjectMapper();
		
		User user = mapper.readValue(request.getInputStream(), User.class); //reading user
		
		log.info("Trying to log User: " + user.toString());
		User logged = service.login(user.getUsername(), user.getPassword());
		String isUser = "";
		
		if (logged == null) {
			log.info("login Failed");
			isUser = mapper.writeValueAsString(null);
			
		}
		else {
			log.info("Login Success!");
			isUser = mapper.writeValueAsString(logged);
			HttpSession session = request.getSession();
			session.setAttribute("user", logged);
			
		}
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(isUser);
	}
	
	

}

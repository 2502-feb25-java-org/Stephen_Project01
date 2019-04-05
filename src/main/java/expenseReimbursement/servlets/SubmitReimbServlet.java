package expenseReimbursement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import expenseReimbursement.model.Reimbursement;
import expenseReimbursement.model.User;
import expenseReimbursement.service.ReimburseService;

@WebServlet("/SubmitReimb")
public class SubmitReimbServlet extends HttpServlet{
	
	private static Logger log = Logger.getLogger(SubmitReimbServlet.class);
	static ReimburseService service = new ReimburseService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Submit Servlet Arrived");
		ObjectMapper mapper = new ObjectMapper();
		String reimbString = "";
		
		HttpSession session = req.getSession();					//get Session
		log.info("This is Session: "+ session.getId());
		User user = (User) session.getAttribute("user");			//get User
		log.info("User is: " + user);
		int userId = user.getId();
		
		List<Reimbursement> reimbursement = service.getReimbByUserId(userId);
		reimbString = mapper.writeValueAsString(reimbursement);
		log.info(reimbString);
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(reimbString);
		
	}
}

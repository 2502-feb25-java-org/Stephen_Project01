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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DO POST SUBMIT SERVLET");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		log.info("User is: " + user);

		ObjectMapper mapper = new ObjectMapper();
		Reimbursement gotReimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		log.info("The Reimb: "+ gotReimb.toString());
		int value = gotReimb.getReimb_amount();
		String desc = gotReimb.getReimb_description();
		String type = gotReimb.getType();
		int id = user.getId();
		int typeId;
		if(type == "lodging") {
			typeId = 1;	
		}
		else if(type == "food") {
			typeId = 2;
		}
		else if(type == "travel") {
			typeId = 3;
		}
		else if(type == "certification") {
			typeId = 4;
		}
		else if(type == "Medical"){
			typeId = 5;
		}
		else {typeId = 1;}
		
		
		service.createReimb(value, desc, typeId, id);
	}
}

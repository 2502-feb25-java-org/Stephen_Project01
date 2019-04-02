package expenseReimbursement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import expenseReimbursement.model.Reimbursement;
import expenseReimbursement.service.ReimburseService;

@WebServlet("/SubmitReimb")
public class SubmitReimbServlet extends HttpServlet{
	
	Logger log = Logger.getLogger(SubmitReimbServlet.class);
	ReimburseService service = new ReimburseService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Submit Servlet Arrived");
		List<Reimbursement> reimbursement = service.getReimbursements();
		log.info(reimbursement.toString());
		
		PrintWriter writer = resp.getWriter();
		
	}
}

package expenseReimbursement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import expenseReimbursement.data.ReimbDAO;
import expenseReimbursement.model.Reimbursement;

public class ReimburseService {

	private static Logger log = Logger.getLogger(ReimburseService.class);
	static ReimbDAO dao = new ReimbDAO();
	
	public List<Reimbursement> getReimbursements(){
		List<Reimbursement> r = dao.getReimbursements();
		log.info(r);
		return r;
	}
	public Reimbursement getSingleReimb() {
		Reimbursement r = dao.getReimb();
		log.info(r);
		return r;
	}
}

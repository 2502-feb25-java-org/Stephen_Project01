package expenseReimbursement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import expenseReimbursement.data.ReimbDAO;
import expenseReimbursement.model.Reimbursement;

public class ReimburseService {

	private static Logger log = Logger.getLogger(ReimburseService.class);
	static ReimbDAO dao = new ReimbDAO();
	
	
	public List<Reimbursement> getReimbByUserId(int uID){
		List<Reimbursement> r = dao.getReimbByUserId(uID);
		log.info(r);
		return r;
	}
	public List<Reimbursement> getAllReimb() {
		List<Reimbursement> r = dao.getAllReimb();
		log.info(r);
		return r;
	}
	public void createReimb(int value, String desc, int typeId, int id) {
		dao.createReimb(value, desc, typeId, id);
		log.info("ASKED DAO TO CREATE REIMB");
	}
}

package expenseReimbursement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import expenseReimbursement.model.Reimbursement;
import expenseReimbursement.util.ConnectionFactory;

public class ReimbDAO {
	Logger log = Logger.getLogger(ReimbDAO.class);
	
	public List<Reimbursement> getReimbursement(){
	List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
	
	
	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
		
		String query = "Select * from reimbursement";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			Reimbursement temp = new Reimbursement(
					rs.getInt("reimb_id"),
					rs.getInt("reimb_amount"),
					rs.getString("reimb_submitted"),
					rs.getString("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("ERS_users_id"),
					rs.getInt("reimb_resolver"),
					rs.getInt("reimb_status"),
					rs.getInt("reimb_type")
					);
					reimbursement.add(temp);
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return reimbursement;
	}
}

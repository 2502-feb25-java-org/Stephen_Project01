package expenseReimbursement.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import expenseReimbursement.model.Reimbursement;
import expenseReimbursement.util.ConnectionFactory;

public class ReimbDAO {
	private static Logger log = Logger.getLogger(ReimbDAO.class);

	public List<Reimbursement> getReimbByUserId(int userId) {
		
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
 
			String query = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, u.user_firstname,\r\n" + 
					"r.reimb_resolver, s.reimb_status, t.reimb_type \r\n" + 
					"from reimbursement r \r\n" + 
					"\r\n" + 
					"inner join Users u on u.ERS_users_id = r.ERS_users_id\r\n" + 
					"inner join reimbursement_status s on r.reimb_status = s.reimb_status_id\r\n" + 
					"inner join reimbursement_type t on r.reimb_type = t.reimb_type_id\r\n" + 
					"where u.ERS_users_id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			log.info(rs);

			while (rs.next()) {
				Reimbursement temp = new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"),
						rs.getString("reimb_description"), rs.getString("user_firstname"), rs.getString("reimb_resolver"),
						rs.getString("reimb_status"), rs.getString("reimb_type"));
				reimbursement.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(reimbursement);
		return reimbursement;
	}

	// try getting a single reimbursement
	public Reimbursement getAllReimb() {
		Reimbursement r = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, u.user_firstname,\r\n" + 
					"r.reimb_resolver, s.reimb_status, t.reimb_type \r\n" + 
					"from reimbursement r \r\n" + 
					"\r\n" + 
					"inner join Users u on u.ERS_users_id = r.ERS_users_id\r\n" + 
					"inner join reimbursement_status s on r.reimb_status = s.reimb_status_id\r\n" + 
					"inner join reimbursement_type t on r.reimb_type = t.reimb_type_id\r\n" + 
					"where r.reimb_id = 41";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			log.info(rs);
			if (rs.next()) {
				r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(r);
		return r;
	}

	public void addReimb() {
		
	}
}

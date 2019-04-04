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

	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String query = "select * from reimbursement";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			log.info(rs);

			while (rs.next()) {
				Reimbursement temp = new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("ERS_users_id"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status"), rs.getInt("reimb_type"));
				reimbursement.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(reimbursement);
		return reimbursement;
	}

	// try getting a single reimbursement
	public Reimbursement getReimb() {
		Reimbursement r = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from reimbursement where reimb_id = 41";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			log.info(rs);
			if (rs.next()) {
				r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(r);
		return r;
	}

}

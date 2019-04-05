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

	public List<Reimbursement> getAllReimb() {
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
 
			String query = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, u.user_firstname,\r\n" + 
					"r.reimb_resolver, s.reimb_status, t.reimb_type \r\n" + 
					"from reimbursement r \r\n" + 
					"\r\n" + 
					"inner join Users u on u.ERS_users_id = r.ERS_users_id\r\n" + 
					"inner join reimbursement_status s on r.reimb_status = s.reimb_status_id\r\n" + 
					"inner join reimbursement_type t on r.reimb_type = t.reimb_type_id\r\n";
			PreparedStatement statement = conn.prepareStatement(query);
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

	public void createReimb(int value, String desc, int type, int id) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into reimbursement(reimb_amount, reimb_submitted, reimb_description, reimb_type, reimb_status, ERS_users_id)\r\n" + 
					"values(?, current_timestamp, ?, ?, '1',?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, desc);
			statement.setInt(3, type);
			statement.setInt(4, id);
			statement.executeQuery();
			log.info("Executed Query");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void approve(int id, int userId) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "update reimbursement\r\n" + 
					"set reimb_resolved = CURRENT_TIMESTAMP,\r\n" + 
					"reimb_status = ?,\r\n" + 
					"reimb_resolver = ?\r\n" + 
					"where reimb_id = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, 2);
			s.setInt(2, userId);
			s.setInt(3, id);
			s.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deny(int id, int userId) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "update reimbursement\r\n" + 
					"set reimb_resolved = CURRENT_TIMESTAMP,\r\n" + 
					"reimb_status = ?,\r\n" + 
					"reimb_resolver = ?\r\n" + 
					"where reimb_id = ?";
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, 3);
			s.setInt(2, userId);
			s.setInt(3, id);
			s.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

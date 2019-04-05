package expenseReimbursement.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import expenseReimbursement.model.User;
import expenseReimbursement.util.ConnectionFactory;

public class UserDAO {

	static Logger log = Logger.getLogger(UserDAO.class);

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			// Try in SQL Manager before in JDBC

			String query = "select * from Users";

			// Statement interface
			Statement statement = conn.createStatement();

			// result set interface
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				User temp = new User(rs.getInt(1), rs.getString("ERS_username"), // These need to be from the DB
						rs.getString("ERS_password"), rs.getString("user_firstName"), rs.getString("user_lastName"),
						rs.getString("user_email"), rs.getInt("user_role_id"));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// Prepared statement to execute query
	public User getByUsername(String username) {
		User u = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from Users where lower(ERS_username) = ?  ";// set to ?. Can make multiple ? for
																				// indexes
			PreparedStatement ps = conn.prepareStatement(sql); // "compiles" code
			ps.setString(1, username.toLowerCase()); // Set the variable in the statement //(Index, Value)
			ResultSet rs = ps.executeQuery(); // Get user back
			System.out.println("From userDAO" + rs);
			if (rs.next()) {
				u = new User(rs.getInt(1), rs.getString("ERS_username"), // These need to be from the DB
						rs.getString("ERS_password"), rs.getString("user_firstName"), rs.getString("user_lastName"),
						rs.getString("user_email"), rs.getInt("user_role_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("from DAO" + u);
		return u;
	}

	// Persist data. execute update
	public User addUser(User u) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			// conn.setAutoCommit(); is set to true

			String sql = "insert into demo_user(username, password, firstName, lastName, email)"
					+ "values(?, ?, ?, ?, ? )";
			String[] keyNames = { "u_id" }; // We want these keynames to be sent back. Otherwise we only get how many
											// rows
											// were affected. Specify auto generated fields to get back

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName()); // (index number, key)
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());

			int numRowsAffected = ps.executeUpdate();
			// could also call this method without setting equal to anything

			System.out.println("Added " + numRowsAffected + " User(s) to DB");

			if (numRowsAffected == 1) {
				ResultSet pk = ps.getGeneratedKeys();
				pk.next();
				u.setId(pk.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}

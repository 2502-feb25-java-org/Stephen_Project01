package expenseReimbursement.service;

import org.apache.log4j.Logger;

import expenseReimbursement.data.UserDAO;
import expenseReimbursement.model.User;

public class UserService {
	
	private static Logger log = Logger.getLogger(UserService.class);
	static UserDAO dao = new UserDAO();
	
	public User login(String username, String password) {
		User u = dao.getByUsername(username);
		
		if(u == null) {
			log.info("No such user found");
			return null;
		}
		else {
			String userPassword = u.getPassword();	//cannot put this above; NullPointerException, since user doesn't exist
			if(userPassword.equals(password)) {		//Never use == for String comparisons in Java
				log.info("User successfully logged in!");
				return u;
			}
			else {
				//log.info("Incorrect credentials. Your Password: " + userPassword + "\n Correct Password: " + password);
				//log.info("\nAre they the same? " + (userPassword.equals(password)));
				return null;
			}
		}
	}
}

package expenseReimbursement.model;

public class Reimbursement {
	
	private int reimb_id;
	private int reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	private int ERS_Users_id;
	private int resolver;
	private int status;
	private int type;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimb_id, int reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, int eRS_Users_id, int resolver, int status, int type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		ERS_Users_id = eRS_Users_id;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public int getERS_Users_id() {
		return ERS_Users_id;
	}

	public void setERS_Users_id(int eRS_Users_id) {
		ERS_Users_id = eRS_Users_id;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", ERS_Users_id=" + ERS_Users_id + ", resolver=" + resolver + ", status=" + status + ", type=" + type
				+ "]";
	}
	
	
}

package expenseReimbursement.model;

public class ReimbTicket {
	private int decision;
	private int id;
	
	public ReimbTicket() {}
	
	public ReimbTicket(int decision, int id) {
		super();
		this.decision = decision;
		this.id = id;
	}

	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReimbTicket [decision=" + decision + ", id=" + id + "]";
	}
	

}

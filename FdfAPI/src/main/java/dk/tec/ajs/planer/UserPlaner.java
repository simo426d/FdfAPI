package dk.tec.ajs.planer;

public class UserPlaner {
	
	int PlanID;
	String PlanNavn;
	int UserID;
	String Description;
	
	// Constructor that is used to create an instance of the object
	public UserPlaner(int planID, String planNavn, int userID, String description) {
		super();
		PlanID = planID;
		PlanNavn = planNavn;
		UserID = userID;
		Description = description;
	}

	public UserPlaner() {}
	
	
	public int getPlanID() {
		return PlanID;
	}
	public void setPlanID(int planID) {
		PlanID = planID;
	}
	public String getPlanNavn() {
		return PlanNavn;
	}
	public void setPlanNavn(String planNavn) {
		PlanNavn = planNavn;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}

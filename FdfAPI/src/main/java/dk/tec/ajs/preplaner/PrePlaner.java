package dk.tec.ajs.preplaner;

public class PrePlaner {
	
	int PrePlanID;
	String PrePlanNavn;
	String Description;
	
	
	// Constructor that is used to create an instance of the object
	public PrePlaner(int prePlanID, String prePlanNavn, String description) {
		super();
		PrePlanID = prePlanID;
		PrePlanNavn = prePlanNavn;
		Description = description;
	}
	
	public PrePlaner() {}
	
	
	public int getPrePlanID() {
		return PrePlanID;
	}
	public void setPrePlanID(int prePlanID) {
		PrePlanID = prePlanID;
	}
	public String getPrePlanNavn() {
		return PrePlanNavn;
	}
	public void setPrePlanNavn(String prePlanNavn) {
		PrePlanNavn = prePlanNavn;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
}

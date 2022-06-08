package dk.tec.ajs.traeningsplan;

public class Traeningsplan {
	
	int TraeningsplanID;
	int PlanID;
	int ExerciseID;
	
	String PlanNavn;
	int UserID;

	String Navn;
	float NuvaerendeVaegt;
	int Repetitioner;
	float StartVaegt;
	int Saet;
	String OevelseDesc;
	
	// Constructor that is used to create an instance of the object
	public Traeningsplan(int traeningsplanID, int planID, int exerciseID, String planNavn, int userID, String navn,
			float nuvaerendeVaegt, int repetitioner, float startVaegt, int saet, String oevelseDesc) {
		super();
		TraeningsplanID = traeningsplanID;
		PlanID = planID;
		ExerciseID = exerciseID;
		PlanNavn = planNavn;
		UserID = userID;
		Navn = navn;
		NuvaerendeVaegt = nuvaerendeVaegt;
		Repetitioner = repetitioner;
		StartVaegt = startVaegt;
		Saet = saet;
		OevelseDesc = oevelseDesc;
	}
	
	public Traeningsplan() {}
	
	public int getTraeningsplanID() {
		return TraeningsplanID;
	}

	public void setTraeningsplanID(int traeningsplanID) {
		TraeningsplanID = traeningsplanID;
	}

	public int getPlanID() {
		return PlanID;
	}

	public void setPlanID(int planID) {
		PlanID = planID;
	}

	public int getExerciseID() {
		return ExerciseID;
	}

	public void setExerciseID(int exerciseID) {
		ExerciseID = exerciseID;
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

	public String getNavn() {
		return Navn;
	}

	public void setNavn(String navn) {
		Navn = navn;
	}

	public float getNuvaerendeVaegt() {
		return NuvaerendeVaegt;
	}

	public void setNuvaerendeVaegt(float nuvaerendeVaegt) {
		NuvaerendeVaegt = nuvaerendeVaegt;
	}

	public int getRepetitioner() {
		return Repetitioner;
	}

	public void setRepetitioner(int repetitioner) {
		Repetitioner = repetitioner;
	}

	public float getStartVaegt() {
		return StartVaegt;
	}

	public void setStartVaegt(float startVaegt) {
		StartVaegt = startVaegt;
	}

	public int getSaet() {
		return Saet;
	}

	public void setSaet(int saet) {
		Saet = saet;
	}
	
	public String getOevelseDesc() {
		return OevelseDesc;
	}

	public void setOevelseDesc(String oevelseDesc) {
		OevelseDesc = oevelseDesc;
	}

}

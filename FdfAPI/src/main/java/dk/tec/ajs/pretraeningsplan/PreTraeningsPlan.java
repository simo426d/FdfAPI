package dk.tec.ajs.pretraeningsplan;

public class PreTraeningsPlan {
	
	int PreTraeningsplanID;	
	int PrePlanID;
	int ExerciseID;

	String PrePlanNavn;
	
	String Navn;
	int Repetitioner;
	int Saet;
	String OevelseDesc;
	
	// Constructor that is used to create an instance of the object
	public PreTraeningsPlan(int preTraeningsplanID, int prePlanID, int exerciseID, String prePlanNavn, String navn,
			int repetitioner, int saet, String oevelseDesc) {
		super();
		PreTraeningsplanID = preTraeningsplanID;
		PrePlanID = prePlanID;
		ExerciseID = exerciseID;
		PrePlanNavn = prePlanNavn;
		Navn = navn;
		Repetitioner = repetitioner;
		Saet = saet;
		OevelseDesc = oevelseDesc;
	}
	
	
	public PreTraeningsPlan( ) {}
	
	public int getPreTraeningsplanID() {
		return PreTraeningsplanID;
	}

	public void setPreTraeningsplanID(int preTraeningsplanID) {
		PreTraeningsplanID = preTraeningsplanID;
	}

	public int getPrePlanID() {
		return PrePlanID;
	}

	public void setPrePlanID(int prePlanID) {
		PrePlanID = prePlanID;
	}

	public int getExerciseID() {
		return ExerciseID;
	}

	public void setExerciseID(int exerciseID) {
		ExerciseID = exerciseID;
	}
	
	public String getPrePlanNavn() {
		return PrePlanNavn;
	}


	public void setPrePlanNavn(String prePlanNavn) {
		PrePlanNavn = prePlanNavn;
	}


	public String getNavn() {
		return Navn;
	}


	public void setNavn(String navn) {
		Navn = navn;
	}


	public int getRepetitioner() {
		return Repetitioner;
	}


	public void setRepetitioner(int repetitioner) {
		Repetitioner = repetitioner;
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

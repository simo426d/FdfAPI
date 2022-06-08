package dk.tec.ajs.oevelser;


public class Oevelser {
	
	
	int ExerciseID;
	String Navn;
	float NuvaerendeVaegt;
	int Repetitioner;
	float StartVaegt;
	int Saet;
	int UserID;
	String OevelseDesc;
	
	// Constructor that is used to create an instance of the object
	public Oevelser(int exerciseID, String navn, float nuvaerendeVaegt, int repetitioner, float startVaegt, int saet, int userID, String oevelseDesc) {
		super();
		ExerciseID = exerciseID;
		Navn = navn;
		NuvaerendeVaegt = nuvaerendeVaegt;
		Repetitioner = repetitioner;
		StartVaegt = startVaegt;
		Saet = saet;
		UserID = userID;
		OevelseDesc = oevelseDesc;
	}
	
	public Oevelser() {}
	
	public int getExerciseID() {
		return ExerciseID;
	}

	public void setExerciseID(int exerciseID) {
		ExerciseID = exerciseID;
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
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}
	
	public String getOevelseDesc() {
		return OevelseDesc;
	}

	public void setOevelseDesc(String oevelseDesc) {
		OevelseDesc = oevelseDesc;
	}

}

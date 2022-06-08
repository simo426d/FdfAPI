package dk.tec.ajs.traeningsplan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestTraeningsPlan {
	

	MatchTraeningsPlanEnum level;
	int TraeningsplanID;
	int UserID;
	int ExerciseID;
	int PlanID;
	
	public MatchTraeningsPlanEnum getLevel() {
		return level;
	}
	public int getTraeningsplanID() {
		return TraeningsplanID;
	}
	
	public int getUserID() {
		return UserID;
	}
	
	public int getExerciseID() {
		return ExerciseID;
	}
	
	public int getPlanID() {
		return PlanID;
	}
	
	public AnalyzeRequestTraeningsPlan(String pathInfo) {
		
		// http://localhost:8080/FdfAPI/api/traeningssession/Traeningsplan/1
		
		// Matcher udfører matching operation ud fra karakterer i form af et mønster
		Matcher matcher = Pattern.compile("(?i)/Traeningsplan/([0-9]+)").matcher(pathInfo);
		
		// Hvis den finder et match
		if(matcher.find())
		{
			level = MatchTraeningsPlanEnum.MatchTraeningsplanID;
			TraeningsplanID = Integer.parseInt(matcher.group(1)); // Sætter ID = med første gruppe i mønsteret ()
		}
		else {
			
			matcher = Pattern.compile("(?i)/Traeningsplan/User/([0-9]+)").matcher(pathInfo);
			if(matcher.find())
			{
							
				level = MatchTraeningsPlanEnum.MatchTraeningsplan;
				PlanID = Integer.parseInt(matcher.group(1)); // Sætter ID = med første gruppe i mønsteret ()
				//ExerciseID = Integer.parseInt(matcher.group(2));
				//UserID = Integer.parseInt(matcher.group(1));
			}
			else {
				
				level = MatchTraeningsPlanEnum.MatchNo;
			}
			
		}
	}
}

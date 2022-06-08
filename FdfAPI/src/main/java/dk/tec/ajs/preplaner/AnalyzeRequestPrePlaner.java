package dk.tec.ajs.preplaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestPrePlaner {
	
	MatchPrePlanerEnum level;
//	int PrePlanID;
	
	public MatchPrePlanerEnum getLevel() {
		return level;
	}
	
//	public int getPrePlanID() {
//		return PrePlanID;
//	}
	
	public AnalyzeRequestPrePlaner(String pathInfo) {
		
		// http://localhost:8080/FdfAPI/api/preplansession/Preplan/
		
		// Matcher udfører matching operation ud fra karakterer i form af et mønster
		Matcher matcher = Pattern.compile("(?i)/Preplan[/]??$").matcher(pathInfo);
		
		// Hvis finder et match
		if(matcher.find())
		{
			level = MatchPrePlanerEnum.MatchPrePlaner; // Sæt level til MatchPrePlaner
		}
		else {
			
			level = MatchPrePlanerEnum.MatchNo;
		}
	}

}

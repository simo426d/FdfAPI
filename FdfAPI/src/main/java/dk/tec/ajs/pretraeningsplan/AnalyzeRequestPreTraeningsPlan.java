package dk.tec.ajs.pretraeningsplan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestPreTraeningsPlan {
	
	
	MatchPreTraeningsPlanEnum level;
	 int PrePlanID;
	
	public MatchPreTraeningsPlanEnum getLevel() {
		return level;
	}
	public int getPrePlanID() {
		return PrePlanID;
	}
	
	public AnalyzeRequestPreTraeningsPlan(String pathInfo) {
		
		// http://localhost:8080/FdfAPI/api/pretraeningssession/Pretraeningsplan/0
		
		// Matcher udf�rer matching operation ud fra karakterer i form af et m�nster
		Matcher matcher = Pattern.compile("(?i)/Pretraeningsplan/([0-9]+)").matcher(pathInfo);
		// Hvis den finder et match
		if(matcher.find())
		{
			level = MatchPreTraeningsPlanEnum.MatchPreTraeningsPlanID;
			PrePlanID = Integer.parseInt(matcher.group(1)); // S�tter ID = med f�rste gruppe i m�nsteret ()
		}
		else {
			level = MatchPreTraeningsPlanEnum.MatchNo;
			
//			matcher = Pattern.compile("(?i)/Pretraeningsplan[/]??$").matcher(pathInfo);
//			if(matcher.find())
//			{
//				level = MatchPreTraeningsPlanEnum.MatchPreTraeningsPlan;
//			}
//				level = MatchPreTraeningsPlanEnum.MatchNo;
//			}
		}
	}
}		 

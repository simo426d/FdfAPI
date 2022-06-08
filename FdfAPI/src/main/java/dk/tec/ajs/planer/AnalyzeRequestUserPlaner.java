package dk.tec.ajs.planer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestUserPlaner {
	
	MatchUserPlanerEnum level;
	int PlanID;
	int UserID;
	
	public MatchUserPlanerEnum getLevel() {
		return level;
	}
	public int getPlanID() {
		return PlanID;
	}
	public int getUserID() {
		return UserID;
	}
	
	public AnalyzeRequestUserPlaner(String pathInfo)
	{
		// http://localhost:8080/FdfAPI/api/plansession/Userplanid/0
		// http://localhost:8080/FdfAPI/api/plansession/Userplan/User/0
		
		// Matcher udfører matching operation ud fra karakterer i form af et mønster
		Matcher matcher = Pattern.compile("(?i)/Userplanid/([0-9]+)").matcher(pathInfo);
		
		// Matcher efter om det er UserPlanID
		if(matcher.find())
		{
			level = MatchUserPlanerEnum.MatchUserPlanerID;
			PlanID = Integer.parseInt(matcher.group(1)); // Sætter ID = med første gruppe i mønsteret ()
		}
		else
		{
			// Matcher efter hvilken userplan det er ud fra UserID
			matcher = Pattern.compile("(?i)/Userplan/User/([0-9]+)").matcher(pathInfo);
			if(matcher.find())
			{
				level = MatchUserPlanerEnum.MatchUserPlaner;
				UserID = Integer.parseInt(matcher.group(1)); // Sætter ID = med første gruppe i mønsteret ()
			}
			else
			{
				level = MatchUserPlanerEnum.MatchNo;
			}
			
		}
	}
}

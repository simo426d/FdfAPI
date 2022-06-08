package dk.tec.ajs.oevelser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AnalyzeRequestOevelser {
	
	MatchOevelserEnum level;
	int ExerciseID;
	int UserID;
	
	public MatchOevelserEnum getLevel() {
		return level;
	}
	
	public int getExerciseID() {
		return ExerciseID;
	}
	
	public int getUserID() {
		return UserID;
	}
	
	public AnalyzeRequestOevelser(String pathInfo) {
		
		// http://localhost:8080/FdfAPI/api/trainingsession/Oevelser/0
		
		// Matcher udfører matching operation ud fra karakterer i form af et mønster
		Matcher matcher = Pattern.compile("(?i)/Oevelser/([0-9]+)").matcher(pathInfo);
		
		// Hvis den finder et match
		if(matcher.find()) {
			level = MatchOevelserEnum.MatchOevelseId; // Sæt level til MatchOevelseId
			ExerciseID = Integer.parseInt(matcher.group(1)); // Sæt ExercideID ud fra den første gruppe
		}
		else
		{
			matcher = Pattern.compile("(?i)/Oevelser/user/([0-9]+)").matcher(pathInfo);
			if(matcher.find())
			{
				level = MatchOevelserEnum.MatchOevelse;
				UserID = Integer.parseInt(matcher.group(1));
			}
			
			else
			{
				level = MatchOevelserEnum.MatchNo;
			}
		}
			
	}

}

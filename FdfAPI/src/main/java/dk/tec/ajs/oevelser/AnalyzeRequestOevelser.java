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
		
		// Matcher udf�rer matching operation ud fra karakterer i form af et m�nster
		Matcher matcher = Pattern.compile("(?i)/Oevelser/([0-9]+)").matcher(pathInfo);
		
		// Hvis den finder et match
		if(matcher.find()) {
			level = MatchOevelserEnum.MatchOevelseId; // S�t level til MatchOevelseId
			ExerciseID = Integer.parseInt(matcher.group(1)); // S�t ExercideID ud fra den f�rste gruppe
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

package dk.tec.ajs.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestAuthenticateUser {
	
	MatchUserAuthenticateEnum level;
	String Email;
	String Password;
	
	public MatchUserAuthenticateEnum getLevel() {
		return level;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public AnalyzeRequestAuthenticateUser(String pathInfo) {
		
		// http://localhost:8080/FdfAPI/api/extract/User/authenticate/email/password
		
		// Laver et match pattern så vi kan afgører om brugeren vil logge ind eller oprette sig som bruger
		Matcher matcher = Pattern.compile("(?i)/User/authenticate/([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+)/([a-zA-Z0-9]+)").matcher(pathInfo);
		
		// Log ind
		if(matcher.find()) {
			// Sætter niveau
			level = MatchUserAuthenticateEnum.MatchAuthenticateUser;
			Email = matcher.group(1);
			Password = matcher.group(2);
		}
		else
		{
			// Opret user
			matcher = Pattern.compile("(?i)/User/create[/]??$").matcher(pathInfo);
			if(matcher.find())
			{
				// Sætter niveau
				level = MatchUserAuthenticateEnum.MatchCreateUser;
			}
			else
			{
				level = MatchUserAuthenticateEnum.MatchNo;
			}
		}
			
	}
}

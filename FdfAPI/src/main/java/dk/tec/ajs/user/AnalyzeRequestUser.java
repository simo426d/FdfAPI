package dk.tec.ajs.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestUser {
	
	MatchUserEnum level;
	String Email;
	String Password;
	//int UserID;
	
	public MatchUserEnum getLevel() {
		return level;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public String getPassword() {
		return Password;
	}
	
//	public int getUserID() {
//		return UserID;
//	}
	
	public AnalyzeRequestUser(String pathInfo) {
		
		// BRUGES IKKE I DEPLOYMENT. KUN FOR TEST AF API ANALYZE. 
		
		// http://localhost:8080/FdfAPI/api/extract/User/email/password <-- Første login forsøg
				
		Matcher matcher = Pattern.compile("(?i)/User/([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+)/([a-zA-Z0-9]+)").matcher(pathInfo);
				
		if(matcher.find()) {
			level = MatchUserEnum.MatchUserId;
			//UserID = Integer.parseInt(matcher.group(1));
			Email = matcher.group(1);
			Password = matcher.group(2);
		}
		else
		{
			matcher = Pattern.compile("(?i)/User[/]??$").matcher(pathInfo);
			if(matcher.find())
			{
				level = MatchUserEnum.MatchUser;
			}
			else
			{
				level = MatchUserEnum.MatchNo;
			}
		}
			
	}
}

package dk.tec.ajs.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequestUserUpdate {
	
	MatchUserUpdateEnum level;
	//int UserID;
	//String Password;
	//String currentPassword;
	
	
//	public String getOldPassword() {
//		return currentPassword;
//	}
//
//	public void setOldPassword(String currentPassword) {
//		this.currentPassword = currentPassword;
//	}
//
//	public int getUserID() {
//		return UserID;
//	}
//
//	public String getPassword() {
//		return Password;
//	}

	public MatchUserUpdateEnum getLevel() {
		return level;
	}
	
	
	
	public AnalyzeRequestUserUpdate(String pathInfo)
	{
		
		// Laver et match pattern så vi kan afgører om brugeren vil opdatere email eller password
		Matcher matcher = Pattern.compile("(?i)/User/updateemail[/]??$").matcher(pathInfo); // (?i)/User/updateemail[/]??$
		
		if(matcher.find())
		{
			// Sætter niveau
			level = MatchUserUpdateEnum.MatchUpdateEmail;
		}
		else
		{
			matcher = Pattern.compile("(?i)/User/updatepassword[/]??$").matcher(pathInfo);
			if(matcher.find())
			{
				// Sætter niveau
				level = MatchUserUpdateEnum.MatchUpdatePassword;
//				UserID = Integer.parseInt(matcher.group(1));
//				Password = matcher.group(2);
//				currentPassword = matcher.group(3);
			}
			else
			{
				level = MatchUserUpdateEnum.MatchNo;
			}
		}
	}
}

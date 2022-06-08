package dk.tec.ajs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.ajs.user.AnalyzeRequestUser;
import dk.tec.ajs.user.AnalyzeRequestUserUpdate;
import dk.tec.ajs.user.DBToolsUser;
import dk.tec.ajs.user.User;
import dk.tec.ajs.user.UserUpdate;
import dk.tec.ajs.user.AnalyzeRequestAuthenticateUser;


/**
 * Servlet implementation class APIServlet
 */
@WebServlet("/APIServlet")
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public APIServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BRUGES IKKE - Kun til test af API kald
		
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyzeRequestUser analyze = new AnalyzeRequestUser(request.getPathInfo());
		
		DBToolsUser dbu = new DBToolsUser();
		
		switch(analyze.getLevel())
		{
		
		case MatchUserId:
			
					
			User u = dbu.getUserByInfo(analyze.getEmail(), analyze.getPassword());
			
			out.println(mapper.writeValueAsString(u));
			
			break;
			
		case MatchUser:
			
			out.println("Match på alle Users: <br />");
			
			break;
		
		case MatchNo: 
			out.println("No match");
			break;
		
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		PrintWriter out = response.getWriter();
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter og deserialize JSON string til Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestAuthenticateUser analyze = new AnalyzeRequestAuthenticateUser(request.getPathInfo());
		
		// Alle vores database funktioner
		DBToolsUser dbu = new DBToolsUser();
		
		// Sætter user objektet til null
		UserUpdate u = null;
		
		// Analysere URL om vi skal logge ind eller oprette ny user
		switch(analyze.getLevel())
		{
		
		case MatchAuthenticateUser:
			
			// Logger ind
			try {
				// Analysere URL for email og password og derved logger ind hvis det er korrekt med DB
				User au = dbu.getUserByInfo(analyze.getEmail(), analyze.getPassword());
				
				out.println(mapper.writeValueAsString(au));			
			} catch (Exception e) {
				response.setStatus(405);
				e.printStackTrace();
			}
			
			break;
			
		case MatchCreateUser:
			
			// Laver ny user
			
			// BufferedReader giver os muliged for at aflæse text fra et karakter input stream fx fra en readLine
			BufferedReader reader = request.getReader();
			
			// Vi angiver inputtet som jsonStr fra vores Http request.
			String jsonStr = reader.readLine();						
			
			try {

				// Angiver vores user objekt u til at have værdierne fra vores json objekt som er fra json string og vores klasse
				u = mapper.readValue(jsonStr, UserUpdate.class);
				
				// brug addNewUser funktionen
				dbu.addNewUser(u);
	
			} catch (Exception e) {
				response.setStatus(405);
				e.printStackTrace();
			}
			
			break;
		
		case MatchNo: 
			response.setStatus(400);
			out.println("No match");
			break;
		
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// Instantiere vores klasser/library så vi kan benytte dem
		
		PrintWriter out = response.getWriter();
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		BufferedReader reader = request.getReader();		

		// Vi angiver inputtet som jsonStr fra vores Http request.
		String jsonStr = reader.readLine();
		
		// Sætter UserUpdate objektet u til null
		UserUpdate u = null;
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestUserUpdate analyze = new AnalyzeRequestUserUpdate(request.getPathInfo());
		
		// Alle vores database funktioner
		DBToolsUser dbu = new DBToolsUser();
		
		switch(analyze.getLevel())
		{
		case MatchUpdateEmail:
			// Update email
			try {				
				u = mapper.readValue(jsonStr, UserUpdate.class);
				dbu.updateUserEmail(u);				
				
			} catch (Exception e) {
				response.setStatus(405);
				e.printStackTrace();
			}
			break;
			// Update password
		case MatchUpdatePassword:
			try {
				u = mapper.readValue(jsonStr, UserUpdate.class);
				dbu.updateUserPassword(u);
			
			} catch (Exception e) {
				response.setStatus(405);
				out.println("Fejl");
				e.printStackTrace();
			}
			break;
		case MatchNo:
			response.setStatus(400);
			out.println("No match");
			break;
		}
	}
	
}

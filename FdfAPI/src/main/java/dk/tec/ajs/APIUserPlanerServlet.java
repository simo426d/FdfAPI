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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.ajs.planer.UserPlaner;
import dk.tec.ajs.planer.AnalyzeRequestUserPlaner;
import dk.tec.ajs.planer.DBToolsUserPlaner;

/**
 * Servlet implementation class APIUserPlanerServlet
 */
@WebServlet("/APIUserPlanerServlet")
public class APIUserPlanerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APIUserPlanerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		PrintWriter out = response.getWriter();
		

		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsUserPlaner dbu = new DBToolsUserPlaner();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestUserPlaner analyze = new AnalyzeRequestUserPlaner(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchUserPlanerID:
			response.setStatus(405);
			
			//UserPlaner up = dbu.getUserPlanerByID(analyze.getPlanID());			
			//out.println(mapper.writeValueAsString(up));
			break;
		case MatchUserPlaner:
			
			// Displayer liste af userplaner ud fra UserID
			ArrayList<UserPlaner> userplaner = (ArrayList<UserPlaner>) dbu.getAllUserPlaner(analyze.getUserID()); // Have to be UserID
			out.println(mapper.writeValueAsString(userplaner));
			break;
		case MatchNo:
			response.setStatus(400);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		BufferedReader reader = request.getReader();
		
		// Vi angiver inputtet som jsonStr fra vores Http request.
		String jsonStr = reader.readLine();
		
		// Sætter objektet UserPlaner up null
		UserPlaner up = null;
		

		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsUserPlaner dbu = new DBToolsUserPlaner();
		
		try {
			
			// Angiver vores UserPlan objekt up til at have værdierne fra vores json objekt som er fra json string og vores klasse
			up = mapper.readValue(jsonStr, UserPlaner.class);
			
			// Bruger funktionen addNewUserPlan med objektet up
			dbu.addNewUserPlan(up);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		// BufferedReader giver os muliged for at aflæse text fra et karakter input stream fx fra en readLine
		BufferedReader reader = request.getReader();
		
		// Vi angiver inputtet som jsonStr fra vores Http request.
		String jsonStr = reader.readLine();
		
		UserPlaner up = null;
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsUserPlaner dbu = new DBToolsUserPlaner();
		
		try {

			// Angiver vores UserPlan objekt up til at have værdierne fra vores json objekt som er fra json string og vores klasse
			up = mapper.readValue(jsonStr, UserPlaner.class);
			
			// Bruger funktionen updateUserPlan med objektet up
			dbu.updateUserPlan(up);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Alle vores database funktioner
		DBToolsUserPlaner dbu = new DBToolsUserPlaner();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestUserPlaner analyze = new AnalyzeRequestUserPlaner(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchUserPlanerID:
			// Sletter UserPlanen ud fra PlanID
			UserPlaner up = dbu.getUserPlanerByID(analyze.getPlanID());
			
			try {
				// Bruger funktionen deleteUserPlan til at slette
				dbu.deleteUserPlan(up);	
				
			} catch(Exception e) {
				response.setStatus(405);
				e.printStackTrace();
			}
			break;
		case MatchNo:
			response.setStatus(400);
			break;
		default:
			break;
		}
		
	}

}

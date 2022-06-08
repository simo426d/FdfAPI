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

import dk.tec.ajs.traeningsplan.Traeningsplan;
import dk.tec.ajs.traeningsplan.AnalyzeRequestTraeningsPlan;
import dk.tec.ajs.traeningsplan.DBToolsTraeningsplan;

/**
 * Servlet implementation class APITraeningsPlanServlet
 */
@WebServlet("/APITraeningsPlanServlet")
public class APITraeningsPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APITraeningsPlanServlet() {
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
		DBToolsTraeningsplan dbt = new DBToolsTraeningsplan();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestTraeningsPlan analyze = new AnalyzeRequestTraeningsPlan(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		// Display user træningsplaner
		case MatchTraeningsplan:
			
			// Har en arrayliste traeningsplaner som skal udfyldes ved brug af funktion AllTraeningsPlaner ud fra UserID
			ArrayList<Traeningsplan> traeningsplaner = (ArrayList<Traeningsplan>) dbt.getAllTraeningsPlaner(analyze.getPlanID());
			out.println(mapper.writeValueAsString(traeningsplaner));
			break;
			
		case MatchNo: 
			response.setStatus(400);
			break;
		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		// BufferedReader giver os muliged for at aflæse text fra et karakter input stream fx fra en readLine
		BufferedReader reader = request.getReader();
		
		// Vi angiver inputtet som jsonStr fra vores Http request.
		String jsonStr = reader.readLine();
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Sætter objektet Traeningsplan tp = null
		Traeningsplan tp = null;
		
		// Alle vores database funktioner 
		DBToolsTraeningsplan dbt = new DBToolsTraeningsplan();
		
		try {
			// Angiver vores Traeningsplan objekt tp til at have værdierne fra vores json objekt som er fra json string og vores klasse
			tp = mapper.readValue(jsonStr, Traeningsplan.class);
			dbt.addNewTraeningsplan(tp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Alle vores database funktioner
		DBToolsTraeningsplan dbt = new DBToolsTraeningsplan();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestTraeningsPlan analyze = new AnalyzeRequestTraeningsPlan(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		// Sletter træningsplan ud fra TraeningsplansID
		case MatchTraeningsplanID:
			
			Traeningsplan tp = dbt.getTraeningsPlanByID(analyze.getTraeningsplanID());
			
			try {
				
				dbt.deleteOevelseFromPlan(tp);	
				
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

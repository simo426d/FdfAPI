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

import dk.tec.ajs.preplaner.AnalyzeRequestPrePlaner;
import dk.tec.ajs.preplaner.DBToolsPrePlaner;
import dk.tec.ajs.preplaner.PrePlaner;

/**
 * Servlet implementation class APIPrePlanerServlet
 */
@WebServlet("/APIPrePlanerServlet")
public class APIPrePlanerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APIPrePlanerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Instantiere vores klasser så vi kan benytte dem
		PrintWriter out = response.getWriter();
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsPrePlaner dbo = new DBToolsPrePlaner();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestPrePlaner analyze = new AnalyzeRequestPrePlaner(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchPrePlaner:
			// Henter liste ud fra funktionen AllPrePlaner
			ArrayList<PrePlaner> preplaner = (ArrayList<PrePlaner>) dbo.getAllPrePlaner();
			
			// Udskriver listen på kaldet så man kan se om det virker
			out.println(mapper.writeValueAsString(preplaner));
			break;
		case MatchNo:
			response.setStatus(400);
			break;
		}
	}

}

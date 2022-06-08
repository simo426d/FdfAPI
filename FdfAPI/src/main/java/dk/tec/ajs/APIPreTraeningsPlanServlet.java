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

import dk.tec.ajs.pretraeningsplan.AnalyzeRequestPreTraeningsPlan;
import dk.tec.ajs.pretraeningsplan.DBToolsPreTraeningsPlan;
import dk.tec.ajs.pretraeningsplan.PreTraeningsPlan;

/**
 * Servlet implementation class APIPreTraeningsPlanServlet
 */
@WebServlet("/APIPreTraeningsPlanServlet")
public class APIPreTraeningsPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APIPreTraeningsPlanServlet() {
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
		DBToolsPreTraeningsPlan dbo = new DBToolsPreTraeningsPlan();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestPreTraeningsPlan analyze = new AnalyzeRequestPreTraeningsPlan(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchPreTraeningsPlanID:
			
			// Henter træningsplans info: øvelser etc. med funktionen AllPreTraeningsPlaner ud fra PrePlanID
			ArrayList<PreTraeningsPlan> preplanerid = (ArrayList<PreTraeningsPlan>) dbo.getAllPreTraeningsPlaner(analyze.getPrePlanID());
			out.println(mapper.writeValueAsString(preplanerid));
			break;
//		case MatchPreTraeningsPlan:
//			ArrayList<PreTraeningsPlan> preplanerall = (ArrayList<PreTraeningsPlan>) dbo.getAllPreTraeningsPlaner();
//			out.println(mapper.writeValueAsString(preplanerall));
//			break;
		case MatchNo:
			response.setStatus(400);
			break;
		}
	}

}

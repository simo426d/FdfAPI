package dk.tec.ajs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.tec.ajs.oevelser.AnalyzeRequestOevelser;
import dk.tec.ajs.oevelser.DBToolsOevelser;
import dk.tec.ajs.oevelser.Oevelser;

/**
 * Servlet implementation class APIOevelseServlet
 */
@WebServlet("/APIOevelseServlet")
public class APIOevelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APIOevelseServlet() {
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
		DBToolsOevelser dbo = new DBToolsOevelser();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestOevelser analyze = new AnalyzeRequestOevelser(request.getPathInfo());
		
		// Analysere URL om vi skal hente listen af øvelser.
		switch(analyze.getLevel())
		{
		case MatchOevelseId:
			response.setStatus(405);
			
			//Oevelser o = dbo.getOevelseById(analyze.getExerciseID());			
			//out.println(mapper.writeValueAsString(o));			
			break;
		case MatchOevelse:
			
			// Hvis url passer henter vi øvelserne
			
			ArrayList<Oevelser> oevelser = (ArrayList<Oevelser>) dbo.getAllOevelser(analyze.getUserID());
			out.println(mapper.writeValueAsString(oevelser));
			break;
		case MatchNo:
			response.setStatus(400);
			break;
		}
				
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Giver bruger mulighed for at ændre på sin øvelse der er tilføjet.
		
		// BufferedReader giver os muliged for at aflæse text fra et karakter input stream fx fra en readLine
		BufferedReader reader = request.getReader();
		
		// Vi angiver inputtet som jsonStr fra vores Http request.
		String jsonStr = reader.readLine();
		
		// Sætter objektet øvelser null så den står tom
		Oevelser o = null;
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsOevelser dbo = new DBToolsOevelser();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestOevelser analyze = new AnalyzeRequestOevelser(request.getPathInfo());
		
		try {
			System.out.println("Udfører API kald");
			o = mapper.readValue(jsonStr, Oevelser.class);
			dbo.updateOevelse(o);
					
		}
		catch(Exception e)
		{
			System.out.println("Fejl");
			e.printStackTrace();
			response.setStatus(404);
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BufferedReader giver os muliged for at aflæse text fra et karakter input stream fx fra en readLine
		BufferedReader reader = request.getReader();
		String jsonStr = reader.readLine();
		
		Oevelser o = null;
		
		// Objectmapper for at skrive json
		ObjectMapper mapper = new ObjectMapper();
		
		// Henter vores database funktioner
		DBToolsOevelser dbo = new DBToolsOevelser();
		
		try {
			// Angiver vores øvelse objekt o til at have værdierne fra vores json objekt som er fra json string og vores klasse
			o = mapper.readValue(jsonStr, Oevelser.class);
			
			// Brug funktion addNewOevele
			dbo.addNewOevelse(o);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(400);
		}
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DBToolsOevelser dbo = new DBToolsOevelser();
		
		AnalyzeRequestOevelser analyze = new AnalyzeRequestOevelser(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchOevelseId:
			
			Oevelser o = dbo.getOevelseById(analyze.getExerciseID());
			
			try {
				
				dbo.deleteOevelse(o);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			break;
		case MatchNo:
			response.setStatus(400);
		default:
			break;
		}
	}

}

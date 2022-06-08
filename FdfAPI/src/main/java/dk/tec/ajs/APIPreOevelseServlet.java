package dk.tec.ajs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.ajs.oevelser.AnalyzeRequestOevelser;
import dk.tec.ajs.oevelser.DBToolsOevelser;
import dk.tec.ajs.oevelser.Oevelser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class APIPreOevelseServlet
 */
@WebServlet("/APIPreOevelseServlet")
public class APIPreOevelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public APIPreOevelseServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// http://localhost:8080/FdfAPI/api/pretrainingsession/Oevelser/1
		
		// Instantiere vores klasser/library så vi kan benytte dem
		
		PrintWriter out = response.getWriter();
		
		// Objectmapper giver os mulighed for at skrive JSON fra Java objekter
		ObjectMapper mapper = new ObjectMapper();
		
		// Alle vores database funktioner
		DBToolsOevelser dbo = new DBToolsOevelser();
		
		// Vores anaylyze der benyttes til at se på vores URL til at afgøre hvad for funktion skal benyttes
		AnalyzeRequestOevelser analyze = new AnalyzeRequestOevelser(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchOevelseId:
			response.setStatus(405);
			
			//Oevelser o = dbo.getOevelseById(analyze.getExerciseID());			
			//out.println(mapper.writeValueAsString(o));			
			break;
		case MatchOevelse:
			ArrayList<Oevelser> oevelser = (ArrayList<Oevelser>) dbo.getAllPreOevelser();
			out.println(mapper.writeValueAsString(oevelser));
			break;
		case MatchNo:
			response.setStatus(400);
			break;
		}
			
	}

}

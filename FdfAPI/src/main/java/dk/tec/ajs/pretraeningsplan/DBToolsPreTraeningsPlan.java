package dk.tec.ajs.pretraeningsplan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DBToolsPreTraeningsPlan {
	
	private String connectionStr = "jdbc:sqlserver://142.93.238.170:1433;databaseName=fdfDB";
	
	private Connection con;
	private Statement stmt;
	
	private void connect() {
		try {
			// Skal bruge denne driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Credentiels
			con = DriverManager.getConnection(connectionStr, "sa", "p5FdFgrp10");
			
			stmt = con.createStatement();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Inner join til at display planer og øvelser der hænger sammen
	public List<PreTraeningsPlan> getAllPreTraeningsPlaner(int PrePlanID)
	{
		connect();
		
		PreTraeningsPlan PrePlanerAll = null;
		
		// Innerjoin query
		String selectStr = "SELECT PreTraeningsplanID, pdp.PrePlanID, pdp.PrePlanNavn, o.ExerciseID, o.Navn, o.Repetitioner, o.Saet, o.OevelseDesc\r\n"
				+ "FROM PreDefineretPlan pdp\r\n"
				+ "INNER JOIN PreTraeningsplan pt ON  pdp.PrePlanID = pt.PrePlanID\r\n"
				+ "INNER JOIN Oevelser o ON pt.ExerciseID = o.ExerciseID\r\n"
				+ "WHERE o.NuvaerendeVaegt IS NULL AND pdp.PrePlanID = " + PrePlanID;
		
		
		List<PreTraeningsPlan> list = new ArrayList<PreTraeningsPlan>();
		
		try {
			//PreparedStatement prep = con.prepareStatement(selectStr);
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Så længe der er et resultat udfyld liste
			while(result.next())
			{
				PrePlanerAll = new PreTraeningsPlan();
				
				PrePlanerAll.setPreTraeningsplanID(result.getInt("PreTraeningsplanID"));
				PrePlanerAll.setPrePlanID(result.getInt("PrePlanID"));
				PrePlanerAll.setPrePlanNavn(result.getString("PrePlanNavn"));
				PrePlanerAll.setExerciseID(result.getInt("ExerciseID"));
				PrePlanerAll.setNavn(result.getString("Navn"));
				PrePlanerAll.setRepetitioner(result.getInt("Repetitioner"));
				PrePlanerAll.setSaet(result.getInt("Saet"));
				PrePlanerAll.setOevelseDesc(result.getString("OevelseDesc"));
				
								
				list.add(PrePlanerAll);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
}


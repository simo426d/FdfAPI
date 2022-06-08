package dk.tec.ajs.preplaner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBToolsPrePlaner {
	
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
	
	// Henter liste over alle PrePlaner
	public List<PrePlaner> getAllPrePlaner() 
	{
		connect();
		
		// Sætter objekt null
		PrePlaner PrePlanerAll = null;
		
		String selectStr = "SELECT * FROM PreDefineretPlan";
		List<PrePlaner> list = new ArrayList<PrePlaner>();
		
		try {
			
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Så længe der der et resultat forsæt med at udfyld listen
			while(result.next())
			{
				PrePlanerAll = new PrePlaner();
				
				PrePlanerAll.setPrePlanID(result.getInt("PrePlanID"));
				PrePlanerAll.setPrePlanNavn(result.getString("PrePlanNavn"));
				PrePlanerAll.setDescription(result.getString("Description"));
				
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

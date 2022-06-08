package dk.tec.ajs.traeningsplan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBToolsTraeningsplan {
	
	private String connectionStr = "jdbc:sqlserver://142.93.238.170:1433;databaseName=fdfDB";
	
	private Connection con;
	private Statement stmt;
	
	private void connect() {
		try {
			// Skal bruge denne driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con = DriverManager.getConnection(connectionStr, "sa", "p5FdFgrp10");
			
			stmt = con.createStatement();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Finder traeningsplan ud fra ID
	public Traeningsplan getTraeningsPlanByID(int TraeningsplanID) {
		
		connect();
		
		Traeningsplan tp = null;
		
		String selectStr = "SELECT * FROM Traeningsplan WHERE TraeningsplanID = " + TraeningsplanID;
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Hvis der er resultat
			if(result.next())
			{
				tp = new Traeningsplan();
				
				tp.setTraeningsplanID(result.getInt("TraeningsplanID"));
				tp.setPlanID(result.getInt("PlanID"));
				tp.setExerciseID(result.getInt("ExerciseID"));
				
			}
			con.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return tp;		
	}
	
	// Inner join til at display user planer og øvelser der hænger sammen
	public List<Traeningsplan> getAllTraeningsPlaner(int PlanID) {
		
		connect();
		
		Traeningsplan TraeningsplanAll = null;
		
		
		String selectStr = "SELECT TraeningsplanID, up.PlanID, up.PlanNavn, up.UserID, o.ExerciseID, o.Navn, o.NuvaerendeVaegt, o.Repetitioner, o.StartVaegt, o.Saet, o.OevelseDesc\r\n"
				+ "FROM Planer up\r\n"
				+ "INNER JOIN Traeningsplan tp ON  up.PlanID = tp.PlanID\r\n"
				+ "INNER JOIN Oevelser o ON tp.ExerciseID = o.ExerciseID\r\n"
				+ "WHERE o.NuvaerendeVaegt IS NOT NULL AND up.PlanID = " + PlanID;
		
		List<Traeningsplan> list = new ArrayList<Traeningsplan>();
		
		try {
			//PreparedStatement prep = con.prepareStatement(selectStr);
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Så længe der er et resultat udfyld liste
			while(result.next())
			{
				TraeningsplanAll = new Traeningsplan();
				
				TraeningsplanAll.setTraeningsplanID(result.getInt("TraeningsplanID"));
				TraeningsplanAll.setPlanID(result.getInt("PlanID"));
				TraeningsplanAll.setPlanNavn(result.getString("PlanNavn"));
				TraeningsplanAll.setUserID(result.getInt("UserID"));
				TraeningsplanAll.setExerciseID(result.getInt("ExerciseID"));
				TraeningsplanAll.setNavn(result.getString("Navn"));
				TraeningsplanAll.setNuvaerendeVaegt(result.getFloat("NuvaerendeVaegt"));
				TraeningsplanAll.setRepetitioner(result.getInt("Repetitioner"));
				TraeningsplanAll.setStartVaegt(result.getFloat("StartVaegt"));
				TraeningsplanAll.setSaet(result.getInt("Saet"));
				TraeningsplanAll.setOevelseDesc(result.getString("OevelseDesc"));
								
				list.add(TraeningsplanAll);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	// Laver ny træningsplan
	public void addNewTraeningsplan(Traeningsplan tp) {
		
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO Traeningsplan(PlanID, ExerciseID) VALUES(?,?)");
			prep.setInt(1, tp.getPlanID());
			prep.setInt(2, tp.getExerciseID());
			
			prep.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
//	public void updateTraeningsplan(Traeningsplan tp) {
//		
//		connect();
//		
//		try {
//			PreparedStatement prep = con.prepareStatement("UPDATE Traeningsplan SET PlanID = ?, ExerciseID = ?");
//			prep.setInt(1, tp.getPlanID());
//			prep.setInt(2, tp.getExerciseID());
//			
//			prep.executeUpdate();
//			
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	// delete traeningsplan ud fra ID 
	public void deleteOevelseFromPlan(Traeningsplan tp) {
		
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("DELETE FROM Traeningsplan WHERE TraeningsplanID = ?");
			prep.setInt(1, tp.getTraeningsplanID());
			
			prep.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}

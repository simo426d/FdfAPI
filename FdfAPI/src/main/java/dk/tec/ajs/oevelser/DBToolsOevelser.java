package dk.tec.ajs.oevelser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBToolsOevelser {
	
	
	private String connectionStr = "jdbc:sqlserver://142.93.238.170:1433;databaseName=fdfDB";
	
	private Connection con;
	private Statement stmt;
	
	// Bruges til at få connection til databasen
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
	
	// Find øvelse ud fra ID
	public Oevelser getOevelseById(int ExerciseID)
	{
		connect();
		Oevelser o = null;
		
		//String selectStr = "SELECT * FROM Oevelser WHERE ExerciseID = " + ExerciseID;
		
		try {
			PreparedStatement prep = con.prepareStatement("SELECT * FROM Oevelser WHERE ExerciseID = " + ExerciseID);
			//ResultSet result = con.executeQuery(selectStr);
			ResultSet result = prep.executeQuery();
			
			// Hvis der er et resultat udfør næste
			if(result.next())
			{
				o = new Oevelser();
				
				o.setExerciseID(result.getInt("ExerciseID"));
				o.setNavn(result.getString("Navn"));
				o.setNuvaerendeVaegt(result.getFloat("NuvaerendeVaegt"));
				o.setRepetitioner(result.getInt("Repetitioner"));
				o.setStartVaegt(result.getFloat("StartVaegt"));
				o.setSaet(result.getInt("Saet"));
				o.setOevelseDesc(result.getString("OevelseDesc"));
			}
			con.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	public List<Oevelser> getAllOevelser(int UserID) 
	{
		connect();
		
		Oevelser OevelseAll = null;
		
		String selectStr = "SELECT ExerciseID, Navn, NuvaerendeVaegt, Repetitioner, StartVaegt, Saet, UserID, OevelseDesc FROM Oevelser WHERE UserID =" + UserID;
		List<Oevelser> list = new ArrayList<Oevelser>();
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Så længe der der et resultat forsæt med at udfyld listen
			while(result.next())
			{
				OevelseAll = new Oevelser();
				
				OevelseAll.setExerciseID(result.getInt("ExerciseID"));
				OevelseAll.setNavn(result.getString("Navn"));
				OevelseAll.setNuvaerendeVaegt(result.getFloat("NuvaerendeVaegt"));
				OevelseAll.setRepetitioner(result.getInt("Repetitioner"));
				OevelseAll.setStartVaegt(result.getFloat("StartVaegt"));
				OevelseAll.setSaet(result.getInt("Saet"));
				OevelseAll.setUserID(result.getInt("UserID"));
				OevelseAll.setOevelseDesc(result.getString("OevelseDesc"));
				
				list.add(OevelseAll);
			}
			
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;		
	}
	
	// Opretter ny øvelse
	public void addNewOevelse(Oevelser o) 
	{
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO Oevelser(Navn, NuvaerendeVaegt, Repetitioner, StartVaegt, Saet, UserID, OevelseDesc) VALUES(?,?,?,?,?,?,?)");
			prep.setString(1, o.getNavn());
			prep.setFloat(2, o.getNuvaerendeVaegt());
			prep.setInt(3, o.getRepetitioner());
			prep.setFloat(4, o.getStartVaegt());
			prep.setInt(5, o.getSaet());
			prep.setInt(6, o.getUserID());
			prep.setString(7, o.getOevelseDesc());
			
			prep.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Funktion til at brugeren kan opdatere sin øvelse. 
	public void updateOevelse(Oevelser o)
	{
		System.out.println("Startet");
		connect();
		
		try {
			System.out.println("Køre query");
			// Opdatere øvelsen.
			PreparedStatement prep = con.prepareStatement("UPDATE Oevelser SET Navn = ?, NuvaerendeVaegt = ?, Repetitioner = ?, StartVaegt = ?, Saet = ?, OevelseDesc = ? WHERE ExerciseID = ?"); //
			prep.setString(1, o.getNavn());
			prep.setFloat(2, o.getNuvaerendeVaegt());
			prep.setInt(3, o.getRepetitioner());
			prep.setFloat(4, o.getStartVaegt());
			prep.setInt(5, o.getSaet());
			prep.setString(6, o.getOevelseDesc());
			prep.setInt(7, o.getExerciseID());
			
			
			prep.executeUpdate();
			con.close();
			System.out.println("alt kørt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Denne funktion deleter specifik øvelse ud fra ExerciseID
	public void deleteOevelse(Oevelser o) 
	{
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("DELETE FROM Oevelser WHERE ExerciseID = ?");
			
			prep.setInt(1, o.getExerciseID());
			
			prep.executeUpdate();
			con.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// USER ØVELSER
	//////////////////////////////////////////////////////////////////////
	// PRE ØVELSER
	
	// Ikke i brug. Men brugt til test af API kald.
	public List<Oevelser> getAllPreOevelser()
	{
		connect();
		
		Oevelser OevelseAll = null;
		
		String selectStr = "SELECT * FROM Oevelser WHERE NuvaerendeVaegt IS NULL";
		List<Oevelser> list = new ArrayList<Oevelser>();
		
		try {
			
			ResultSet result = stmt.executeQuery(selectStr);
			
			while(result.next())
			{
				OevelseAll = new Oevelser();
				
				OevelseAll.setNavn(result.getString("Navn"));
				OevelseAll.setRepetitioner(result.getInt("Repetitioner"));
				OevelseAll.setSaet(result.getInt("Saet"));
				OevelseAll.setOevelseDesc(result.getString("OevelseDesc"));
				
				list.add(OevelseAll);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
//	public void addNewPreOevelse(Oevelser o) 
//	{
//		connect();
//		
//		try {
//			PreparedStatement prep = con.prepareStatement("INSERT INTO Oevelser(Navn, Repetitioner, Saet) VALUES(?,?,?)");
//			prep.setString(1, o.getNavn());
//			prep.setInt(2, o.getRepetitioner());
//			prep.setInt(3, o.getSaet());
//			
//			prep.executeUpdate();
//			con.close();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

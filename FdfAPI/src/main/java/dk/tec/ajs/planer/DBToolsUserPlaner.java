package dk.tec.ajs.planer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBToolsUserPlaner {
	
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
	
	// Hent user plan gennem PlanID
	public UserPlaner getUserPlanerByID(int PlanID)
	{
		connect();
		
		UserPlaner p = null;
		
		String selectStr = "SELECT * FROM Planer WHERE PlanID = " + PlanID;
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			// Hvis der er et resultat udfør næste
			if(result.next())
			{
				p = new UserPlaner();
				
				p.setPlanID(result.getInt("PlanID"));
				p.setPlanNavn(result.getString("PlanNavn"));
				p.setUserID(result.getInt("UserID"));
				p.setDescription(result.getString("Description"));
				
			}
			con.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	// Henter alle User Planer
	public List<UserPlaner> getAllUserPlaner(int UserID)
	{
		connect();
		
		UserPlaner PlanerAll = null;
		
		String selectStr = "SELECT * FROM Planer WHERE UserID = " + UserID;
		List<UserPlaner> list = new ArrayList<UserPlaner>();
		
		try {
			
			ResultSet result = stmt.executeQuery(selectStr);
			
			// // Så længe der der et resultat forsæt med at udfyld listen
			while(result.next())
			{
				PlanerAll = new UserPlaner();
				
				PlanerAll.setPlanID(result.getInt("PlanID"));
				PlanerAll.setPlanNavn(result.getString("PlanNavn"));
				PlanerAll.setUserID(result.getInt("UserID"));
				PlanerAll.setDescription(result.getString("Description"));
				
				
				list.add(PlanerAll);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	// Tilføj ny user plan
	public void addNewUserPlan(UserPlaner up) 
	{
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO Planer(PlanNavn, UserID, Description) VALUES(?,?,?)");
			prep.setString(1, up.getPlanNavn());
			prep.setInt(2, up.getUserID());
			prep.setString(3, up.getDescription());
			
			prep.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Opdatere Userplan
	public void updateUserPlan(UserPlaner up) 
	{
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("UPDATE Planer SET PlanNavn = ?, Description = ? WHERE PlanID = ?");
			prep.setString(1, up.getPlanNavn());
			prep.setString(2, up.getDescription());
			prep.setInt(3, up.getPlanID());
			
			
			prep.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Delete userplan
	public void deleteUserPlan(UserPlaner up)
	{
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("DELETE FROM Planer WHERE PlanID = ?");
			prep.setInt(1, up.getPlanID());
			
			prep.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package dk.tec.ajs.user;

import java.io.Console;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.mindrot.jbcrypt.BCrypt;


public class DBToolsUser {
	
	private String connectionStr = "jdbc:sqlserver://142.93.238.170:1433;databaseName=fdfDB";
	
	private Connection con;
	//private Statement stmt;
	
	private void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con = DriverManager.getConnection(connectionStr, "sa", "p5FdFgrp10");
			
			//stmt = con.createStatement();
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	
	 // Login med Email og Password
		public User getUserByInfo(String Email, String Password) 
		{
						
			connect();
			User u = null;
			
			//String selectStr = "Select * from Bruger where Email = " + Email + "and Password = " + Password;
			//String selectStr = "Select * from Bruger where Email = '" + Email + "' and Password = '" + Password + "'";
			
			//System.out.println("Start");
			
			// Vi selecter alt i table Bruger og sammenligner med emailen først om den eksistere og har rolle 1.
			try {
				PreparedStatement prep = con.prepareStatement("SELECT * FROM Bruger Where Email = '" + Email + "' AND Rolle = 1");
				//ResultSet result = stmt.executeQuery(selectStr);
				ResultSet result = prep.executeQuery();
				
				// Hvis der er resultat. Udfør næste.
				if(result.next())
				{
					//System.out.println("Før reading hasehd value");
					
					// Vi hentede brugerens hashede password. Sætter den som en variabel.
					var hashedvalue = result.getString("Password");
					
					// Sammenligner med det brugeren har tastet, og den hashede value. Hvis de matcher logger bruger ind.
					if(BCrypt.checkpw(Password, hashedvalue))
					{
						//System.out.println("aflæst reading hasehd value");
						
						u = new User();
						
						u.setUserID(result.getInt("UserID"));
						u.setFornavn(result.getString("Fornavn"));
						u.setEfternavn(result.getString("Efternavn"));
						u.setEmail(result.getString("Email"));
						u.setPassword(result.getString("Password"));
						u.setRefreshToken(result.getString("RefreshToken"));
						u.setExpiredToken(result.getString("ExpiredToken"));
						u.setRolle(result.getInt("Rolle"));
					}
																		
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return u;
		}
		
		// Opretter ny bruger
		public void addNewUser(UserUpdate u)
		{
			connect();
			
			// Kontrollere om email eksister i forvejen
			try {
				PreparedStatement prepcheck = con.prepareStatement("SELECT Email FROM Bruger WHERE Email = ?");
				prepcheck.setString(1, u.getNewEmail());
				
				ResultSet result = prepcheck.executeQuery();
				
				// Hvis der er ikke noget resultat. Udfør næste
				if(!result.next())
				{
					try {
						
						// Hasher brugerens valgte password
						String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
						
						// Indsætter bruger information og bruger er oprettet. 
						PreparedStatement prep = con.prepareStatement("INSERT INTO Bruger(Fornavn, Efternavn, Email, [Password], Rolle) VALUES(?,?,?,?,?)");
						prep.setString(1, u.getFornavn());
						prep.setString(2, u.getEfternavn());
						prep.setString(3, u.getEmail());
						prep.setString(4, hashed);
						prep.setInt(5, u.getRolle());
												
						
						prep.executeUpdate();
						
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
		}
		
		 //Opdatere brugers email
		public void updateUserEmail(UserUpdate u)
		{
			connect();
			
			
			// Checker om nuværende email og userID matcher. 
			try {
				PreparedStatement prepemail = con.prepareStatement("SELECT UserID, Email FROM Bruger WHERE UserID = ? AND Email = ?");
				prepemail.setInt(1, u.getUserID());
				prepemail.setString(2, u.getEmail());
				
				ResultSet result = prepemail.executeQuery();
				
				// Hvis der er match så udfør næste
				if(result.next())
				{
					
					// Vi opdatere email til den nye email bruger har indtastet.
					try {
						PreparedStatement prep = con.prepareStatement("UPDATE Bruger SET Email = ? WHERE UserID = ?");
						prep.setString(1, u.getNewEmail());
						prep.setInt(2, u.getUserID());
						
						prep.executeUpdate();
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Fejl");
					
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		}
		
		
		
		// Opdatere brugers password.
		public void updateUserPassword(UserUpdate u) throws SQLException
		{
			connect();
					
			try{	
				
				// Vi selecter UserID og det nuværende password som er hashet fra Bruger tabellen hvor det er brugerens ID.
				PreparedStatement preppass = con.prepareStatement("SELECT UserID, [Password] FROM Bruger WHERE UserID = ?");
				preppass.setInt(1, u.getUserID());
				//preppass.setString(2, u.getCurrentPassword());
				
				
				ResultSet result = preppass.executeQuery();
				// Hvis der er match. udfør næste.
				if(result.next())
				{
					
					//henter nuværende password fra resultatet og sammenligner med det brugeren har indtastet.
					var hashedvalue = result.getString("Password");
					if(BCrypt.checkpw(u.getCurrentPassword(), hashedvalue))
					//if(true)
					{
						try {
							// Hasher det nye password og opdatere brugerens nye password.
							String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
							
							PreparedStatement prep = con.prepareStatement("UPDATE Bruger SET [Password] = ? WHERE UserID = ?");
							prep.setString(1, hashed);
							prep.setInt(2, u.getUserID());
							
							prep.executeUpdate();
							
							
						} catch (SQLException e) {
							System.out.println("Fejl");
							e.printStackTrace();
						}
					}
					else
					{
						System.out.println("Fejl ved result");
					}

				}
				else
				{
					System.out.println("Fejl ved first try");
				}
				
				con.close();

			} catch(SQLException e){
				System.out.println("Fejl");
				e.printStackTrace();
			}
									
		}
		
//		String newCandidate = u.getCurrentPassword();
//		  //hashed		//hashed
//if(BCrypt.checkpw(newCandidate, u.getCurrentPassword())) //checkpw for "string" value ?
//{
//PreparedStatement preppass = con.prepareStatement("SELECT UserID, [Password] FROM Bruger WHERE UserID = ? AND [Password] = ?");
//preppass.setInt(1, u.getUserID());
//preppass.setString(2, u.getCurrentPassword());
//
//ResultSet result = preppass.executeQuery();
//
//if(result.next())
//{
//try {
//	
//	String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
//	
//	PreparedStatement prep = con.prepareStatement("UPDATE Bruger SET [Password] = ? WHERE UserID = ?");
//	prep.setString(1, hashed);
//	prep.setInt(2, u.getUserID());
//	
//	prep.executeUpdate();
//	
//	
//} catch (SQLException e) {
//	e.printStackTrace();
//}
//}
//}	

}

package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Power {
	
	//A common method to connect to the DB
			private Connection connect()
			{
					Connection con = null;
					
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");

						// DBServer/DBName, username, password
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funds", "root", "WathmaJay@New");
					}
					catch (Exception e)
					{e.printStackTrace();}
					
					return con;
					
			}
			public String insertFund(String date , String time , String area , String duration)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
						
							if (con == null)
							{return "Error while connecting to the database for inserting."; }
							
							// create a prepared statement
							String query = " insert into powerdetails(`id`,`date`,`time`,`area`,`duration`)"
											+ " values (?, ?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);
							
							 
							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, date);
							preparedStmt.setString(3, time);
							preparedStmt.setString(4, area);
							preparedStmt.setString(5, duration);
							
							
							// execute the statement
		
							preparedStmt.execute();
							con.close();
							
							output = "Inserted successfully";
							
					}
					catch (Exception e)
					{
							output = "Error while inserting the power.";
							System.err.println(e.getMessage());
					}
					
					return output;
		 }
			
		 public String readFunds()
		 {
			 	String output = "";
		 
			 		try
			 		{
			 				Connection con = connect();
			 				if (con == null)
			 				{return "Error while connecting to the database for reading."; }
			 				
			 			// Prepare the html table to be displayed
			 				output = "<table border='1'><tr><th>Date</th><th>Time</th>" + "<th>Area</th>" 
			 						 + "<th>Durationl</th></tr>";

			 				String query = "select * from powerdetails";
			 				java.sql.Statement stmt = con.createStatement();
			 				ResultSet rs = stmt.executeQuery(query);
		 
			 				// iterate through the rows in the result set
			 				
			 				while (rs.next())
			 				{
			 						String id = Integer.toString(rs.getInt("id"));
			 						String name = rs.getString("date");
			 						String address = rs.getString("time");
			 						String amount = rs.getString("area");
			 						String email = rs.getString("duration");
			 						
		 
			 						// Add into the html table
			 						output += "<tr><td>" + date + "</td>";
			 						output += "<td>" + time + "</td>";
			 						output += "<td>" + area + "</td>";
			 						output += "<td>" + duration + "</td>";
			 						
			 					// buttons
			 						output += "<td><input name='btnUpdate' type='button' value='Update' "
			 						+ "class='btnUpdate btn btn-secondary' data-powerid='" + id + "'></td>"
			 						+ "<td><input name='btnRemove' type='button' value='Remove' "
			 						+ "class='btnRemove btn btn-danger' data-powerid='" + id + "'></td></tr>"; 
			 						 
			 				}
			 				con.close();
			 				 
			 				// Complete the html table
			 				output += "</table>";
			 		}
			 		catch (Exception e)
			 		{
			 				output = "Error while reading the power.";
			 				System.err.println(e.getMessage());
			 		}
			 	
			 		return output;
		 }
		 
		 public String updateFund(String ID, String date, String time, String area, String duration)
			
		 {

			 String output = "";
			 
		 		try
		 		{
		 				Connection con = connect();
		 				
		 				if (con == null)
		 				{return "Error while connecting to the database for updating."; }
	 
		 				// create a prepared statement
		 				String query = "UPDATE powerdetails SET date=?,time=?,area=?,duration=? WHERE id=?";
		 				
		 				PreparedStatement preparedStmt = con.prepareStatement(query);
		 				
		 			// binding values
		 				preparedStmt.setString(1, date);
		 				preparedStmt.setString(2, time);
		 				preparedStmt.setString(3, area);
		 				preparedStmt.setString(4, duration);
		 				preparedStmt.setInt(7, Integer.parseInt(ID));
		 				
		 			// execute the statement
		 				preparedStmt.execute();
		 				con.close();
		 				
		 				String newPower = readIndex(); 
		 				output = "{\"status\":\"success\", \"data\": \"" + newPower + "\"}"; 
		 				
		 		}
		 		catch (Exception e)
		 		{
		 				output = "{\"status\":\"error\", \"data\": \"Error while updating the power.\"}"; 
		 				System.err.println(e.getMessage()); 
		 		}
		 		return output;
		 }
		 
		 public String deletePower(String id)
		 {
			 	String output = "";
			 	
			 		try
			 		{
			 				Connection con = connect();
			 				
			 				if (con == null)
			 				{return "Error while connecting to the database for deleting."; }
			 				
			 			// create a prepared statement
			 				String query = "delete from powerdetails where id=?";
			 				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 				// binding values
			 				preparedStmt.setInt(1, Integer.parseInt(id));
		 
			 				// execute the statement
			 				preparedStmt.execute();
			 				con.close();
			 				
			 				String newPower = readIndex(); 
			 				output = "{\"status\":\"success\", \"data\": \"" + 
			 				newPower + "\"}"; 
			 		}
			 		catch (Exception e)
			 		{
			 				output = "Error while deleting the power.";
			 				System.err.println(e.getMessage());
			 		}
			 	
			 		return output;
		 }
		
		
	}




			 				
			 				
		 	
		 				
	
			 						
		 
		 
	



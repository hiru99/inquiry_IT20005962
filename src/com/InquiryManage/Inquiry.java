package com.InquiryManage;
import java.sql.*;

public class Inquiry {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafelectricity", "root", "sqlhiru123");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertInquiry(String PersonName, String Area, String Date, String Reason)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into inquiry1 (`inqID`,`PersonName`,`Area`,`Date`,`Reason`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PersonName);
			preparedStmt.setString(3, Area);
			preparedStmt.setString(4, Date);
			preparedStmt.setString(5, Reason);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" +newInquiry + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the inquiry1.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readInquiry()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Person Name</th>"
							+ "<th> Inquiry Date</th>"
							+ "<th> Area</th>"
							+ "<th>Inquiry Reason</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from inquiry1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String inqID  = Integer.toString(rs.getInt("inqID"));
						String PersonName  = rs.getString("PersonName");
						String Area =rs.getString("Area");
						String Date  = rs.getString("Date");
						String Reason = rs.getString("Reason");



						// Add a row into the html table
						output += "<tr><td><input id='hidInquiryIDUpdate'name='hidInquiryIDUpdate'type='hidden' value='" + inqID  + "'>"+ PersonName  + "</td>";
						output += "<td>" + Area + "</td>";
						output += "<td>" + Date + "</td>";
						output += "<td>" + Reason + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-inquiryid='" + inqID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-inquiryid='" + inqID + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the inquiry1.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deleteInquiry(String inqID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from inquiry1 where inqID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inqID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" +newInquiry + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the inquiry1.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updateInquiry(String inqID, String PersonName,String Area,String Date,String Reason)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE inquiry1 SET PersonName=?,Area=?,Date=?,Reason=? WHERE inqID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, PersonName);
			preparedStmt.setString(2, Area);
			preparedStmt.setString(3, Date);
			preparedStmt.setString(4, Reason);
			preparedStmt.setInt(5, Integer.parseInt(inqID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated Inquiry details successfully";
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" +newInquiry + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Customer Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the inquiry1.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}


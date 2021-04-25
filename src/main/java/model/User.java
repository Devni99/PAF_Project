package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.google.gson.JsonElement;


public class User {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdatabase","root", "");
	 //For testing

	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertUser(String username,String address, String age, String sex, String email)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into userdatabase.user(`userId`,`username`,`address`,`age`,`sex`,`email`)"
	 + " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, username);
	 preparedStmt.setString(3, address);
	 preparedStmt.setString(4,age);
	 preparedStmt.setString(5, sex); 
	 preparedStmt.setString(6, email);

	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while inserting";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}

	
	public String readuser()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the  table to be displayed
	 
    // output = "<table border='1'><tr><th>userId</th>"
//	 +"<th>user name</th><th>  age</th>"
//	 + "<th> email</th><th> sex</th>"
//	 + "<th>Update</th><th>Remove</th></tr>";
	 
	 output = "<table style=\"border: 1px solid black\">\r\n"
	 		+ "  <thead>\r\n"
	 		+ "    <tr style=\"border: 1px solid black\">\r\n"
	 		+ "      <th style=\\\"border: 1px solid black\\\" scope=\"col\">userID</th>\r\n"
	 		+ "      <th style=\\\"border: 1px solid black\\\" scope=\"col\">user name</th>\r\n"
	 		+ "      <th style=\\\"border: 1px solid black\\\" scope=\"col\">address</th>\r\n"
	 		+ "      <th style=\\\"border: 1px solid black\\\" scope=\"col\">age</th>\r\n"
	 		+"        <th  style=\\\"border: 1px solid black\\\"scope=\"col\">email</th>\r\n"
	 		+"         <th style=\\\"border: 1px solid black\\\" scope=\"col\">sex</th>\r\n"
	 		
	 		+ "      <th scope=\"col\" colspan=\"2\">Upadate/Delete</th>\r\n"
	 		+ "    </tr>\r\n"
	 		+ "  </thead>\r\n"
	 		+ "</table";
	 String query = "select * from user";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String userId = Integer.toString(rs.getInt("userID"));
	 String username = rs.getString("username");
	 String  address= rs.getString("address");
	 String age = rs.getString("age");
	 String email = rs.getString("email");
	 String sex = rs.getString("sex");

	 // Add a row into the html table
	 output += "<tr style=\\\"border: 1px solid black\\\"><td style=\\\"border: 1px solid black\\\">" + username+ "</td>";
	 output += "<td style=\\\"border: 1px solid black\\\">" + address + "</td>";
	 output += "<td style=\\\"border: 1px solid black\\\">" + age + "</td>"; 
	 output += "<td style=\\\"border: 1px solid black\\\">" + email + "</td>";
	 output += "<td style=\\\"border: 1px solid black\\\">" + sex + "</td>";
	
	
	
	 // buttons
	 output += "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnUpdate' "
     + " type='button' value='Update'></td>"
	 + "<input name='userId' type='hidden' "
	 + " value='" + userId+ "'>" + "</form></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' "
	 + " type='submit' value='Remove'>"
	 + "<input name='userId' type='hidden' "
	 + " value='" + userId + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the user.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	public String removeuser(String ID)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	
	 Statement st=con.createStatement();
	 st.executeUpdate("DELETE FROM user WHERE UserId="+ID);
	 con.close();
	 output = "deleted successfully";

	 
	 }
	catch (Exception e)
	 {
	 output = "Error while deleting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	public String updateUser(String userId, String username,String address, String age, String sex, String email)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = "UPDATE userdatabase.user SET  username=?,  address=?, age=?, sex=?, email=? WHERE userId= '"+userId+"' "
	 + " values (?, ?, ?, ?, ? )";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values

	 preparedStmt.setString(1, username);
	 preparedStmt.setString(2, address);
	 preparedStmt.setString(3,age);
	 preparedStmt.setString(4, sex); 
	 preparedStmt.setString(5, email);
//	 preparedStmt.setString(6, userId);
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	catch (Exception e)
	 {
	 output = "Updated successfully";
//	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}

	


}

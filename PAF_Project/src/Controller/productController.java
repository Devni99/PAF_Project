package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnection;
import Model.Product;

public class productController {

	//Db connection object
	DBConnection db = new DBConnection();
	
	//View Product Details
	
	public String ViewProducts() {
		
		String output = "";
		
		Product product = new Product();
		try
		{
			Connection con = db.connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><th>Product Code</th>"
					+ "<th>Product Name</th>"
					+ "<th>Product Description</th>"
					+ "<th>Inventor Name</th>"
					+ "<th>Price</th>"
					+ "<th>Quantity</th></tr>";

			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				product.setpID(rs.getInt("pID"));
			    product.setpCode(rs.getString("pCode"));
			    product.setpName(rs.getString("pName"));
			    product.setDescription(rs.getString("description"));
			    product.setInventor(rs.getString("inventor"));
			    product.setPrice(rs.getDouble("price"));
			    product.setQuantity(rs.getInt("quantity"));
				
				// Add a row into the html table
				output += "<tr><td>" + product.getpCode() + "</td>";
				output += "<td>" + product.getpName() + "</td>";
				output += "<td>" + product.getDescription() + "</td>"; 
				output += "<td>" + product.getInventor() + "</td>";
				output += "<td>" + product.getPrice() + "</td>";
				output += "<td>" + product.getQuantity() + "</td></tr>";
	
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while viewing products details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
}

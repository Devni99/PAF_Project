package com;


import Model.Product;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import Controller.productController;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/products")
public class productService {

	productController products  = new productController();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ViewProducts()
	 {
	   return products.ViewProducts();
	 } 
	
	// Add Products
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
		public String insertproduct(String ProductData) {
			// Convert the input string to a JSON object
			
			JsonObject djosnObj = new JsonParser().parse(ProductData).getAsJsonObject();
		
			Product product = new Product();
			
			product.setpCode(djosnObj.get("pCode").getAsString());
			product.setpName(djosnObj.get("pName").getAsString());
			product.setDescription(djosnObj.get("description").getAsString());
			product.setInventor(djosnObj.get("inventor").getAsString());
			product.setPrice(djosnObj.get("price").getAsDouble());
			product.setQuantity(djosnObj.get("quantity").getAsInt());
			
			
			// Read the values from the JSON object
		
			String output = products.insertProducts(product);
			return output;

		}
		
		//Update Products
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProducts(String ProductData) {

			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(ProductData).getAsJsonObject();
			Product product = new Product();

			product.setpID(djosnObj.get("pID").getAsInt());
			product.setpCode(djosnObj.get("pCode").getAsString());
			product.setpName(djosnObj.get("pName").getAsString());
			product.setDescription(djosnObj.get("description").getAsString());
			product.setInventor(djosnObj.get("inventor").getAsString());
			product.setPrice(djosnObj.get("price").getAsDouble());
			product.setQuantity(djosnObj.get("quantity").getAsInt());
			
			String output = products.updateProducts(product);
			return output;
		}
		
		
		//Delete Product

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProduct(String ProductData) {
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(ProductData).getAsJsonObject();
			Product product = new Product();

			// Read the value from the element <ID>
			product.setpID(doc.get("pID").getAsInt());
			String output = products.deleteProduct(product);
			return output;
		}
	}

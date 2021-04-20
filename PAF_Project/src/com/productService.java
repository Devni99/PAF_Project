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
	
	
	
}

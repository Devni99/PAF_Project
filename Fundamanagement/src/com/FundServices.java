package com;

import model.Fund;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fund")
public class FundServices {
	Fund itemObj = new Fund();

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readfunds() 
	 { 
	 return itemObj.readItems(); 
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertfunds(@FormParam("fname") String fname, 
	 @FormParam("lname") String lname, 
	 @FormParam("fund_amount") String fund_amount, 
	 @FormParam("itemQuantity") itemQuantity,
	@FormParam("city") String city,
	@FormParam("date") String date,
	
			) 
	{ 
	 String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc , itemQuantity); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fund_id = Object.get("fund_id").getAsString(); 
	 String fname = itemObject.get("fname").getAsString(); 
	 String lname = itemObject.get("lname").getAsString(); 
	 String fund_amount = itemObject.get("fund_amount").getAsString(); 
	 String phone_nu = itemObject.get("phone_nu").getAsString();
	 String city = itemObject.get("city ").getAsString(); 
	 String date = itemObject.get("date ").getAsString(); 
	 
	 String output = itemObj.updateItem(fund_id,fname,lname,fund_amount,phone_nu,city,date); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletefunds(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String itemID = doc.select("fund_id").text(); 
	 String output = itemObj.deleteItem(itemID); 
	return output; 
	}

}